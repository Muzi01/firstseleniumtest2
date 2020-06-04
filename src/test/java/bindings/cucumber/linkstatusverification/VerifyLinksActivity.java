package bindings.cucumber.linkstatusverification;


import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.GATEWAY_TIMEOUT;

@Service
public class VerifyLinksActivity {
  private static final Logger LOGGER = LogManager.getLogger(VerifyLinksActivity.class);
  private static final String ITUNES_PARAMETER = "itunes.apple.com";
  private final URLContext urlContext;
  private final List<Integer> expected = Arrays.asList(200, 301, 302);

  public VerifyLinksActivity(final URLContext urlContext) {
    this.urlContext = urlContext;
  }

  public void checkIfPageContainsActiveLinks(final String pageUrl)
      throws IOException, KeyManagementException, NoSuchAlgorithmException {
    final Map<String, Integer> mapOfUrlsAndRespCodes = new HashMap<>();
    for (final String url : takeAllLinks(pageUrl)) {
      LOGGER.info("Executing GET request for {}", url);
      final int responseCode = getUrlResponse(url);
      LOGGER.info("Response code is {}", responseCode);
      mapOfUrlsAndRespCodes.put(url, responseCode);
    }

    final String errorMsg = mapOfUrlsAndRespCodes.entrySet().stream()
        .filter(map -> !this.expected.contains(map.getValue()))
        .map(map -> String.format("Link %s - returned code: %d %n", map.getKey(),
            map.getValue()))
        .reduce((actualMessage, notActiveLinkMsg) -> actualMessage + notActiveLinkMsg).orElse("");
    Assert.assertTrue(errorMsg, StringUtils.isBlank(errorMsg));
  }

  private int getUrlResponse(final String givenUrl)
      throws IOException, NoSuchAlgorithmException, KeyManagementException {
    final URL url = new URL(givenUrl);
    try {
      return this.urlContext.getStrategy(url.getProtocol()).getUrlResponseCode(url);
    } catch (final UnknownHostException e) {
      return GATEWAY_TIMEOUT.getStatusCode();
    }
  }

  Set<String> takeAllLinks(final String pageUrl) throws IOException {
    final Document document = Jsoup.connect(pageUrl).get();
    final Elements elements = document.select("a");
    final Function<Element, String> getUrlAddressFromElement = e -> e.absUrl("href");
    final Predicate<String> mailTo = e -> e.startsWith("mailto");
    final Predicate<String> isEmpty = String::isEmpty;
    final Predicate<String> containsITunes = e -> e.contains(ITUNES_PARAMETER);
    final Set<String> urls = elements.stream()
        .map(getUrlAddressFromElement)
        .filter(mailTo.negate()
            .and(isEmpty.negate())
            .and(containsITunes.negate()))
        .collect(Collectors.toSet());
    urls.add(pageUrl);
    return urls;
  }

}
