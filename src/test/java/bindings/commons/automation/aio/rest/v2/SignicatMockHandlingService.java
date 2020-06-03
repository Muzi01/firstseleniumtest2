package bindings.commons.automation.aio.rest.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ipfdigital.automation.aio.rest.dto.SignicatAuthorizeRequestDTO;
import com.ipfdigital.automation.aio.rest.v2.banks.SignicatMock;
import com.ipfdigital.automation.config.PropertyProvider;
import com.ipfdigital.automation.config.PropertyProviderFactory;
import com.ipfdigital.automation.generator.utils.Environment;
import feign.Feign;
import feign.Logger.Level;
import feign.Response;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SignicatMockHandlingService {

  private final SignicatMock provider;
  private static final String MOCK_URL = "signicat.mockUrl";
  private static final String REDIRECT_URI = "signicat.redirectUrl";
  private static final String NAME = "Taneli Tammisto";
  private static final String LOCALE = "FI";
  private static final String GNAME = "Taneli";
  private static final String FNAME = "Tammisto";
  private static final String BANK = "nordea";
  private final PropertyProvider propertyProvider;

  public SignicatMockHandlingService(final Environment env) {
    propertyProvider = PropertyProviderFactory.propertyProvider(env.envName);
    provider = getProvider(env);
  }

  public Response authorize(final String request) {
    return provider.authorize(request);
  }

  public Response accept(final String transactionId, final String ssn) {
    return provider.accept(prepareRequest(transactionId, ssn));
  }

  public String extractFromLocationHeader(final Response response, final String name)
      throws UnsupportedEncodingException {
    final MultiValueMap<String, String> locationParams = UriComponentsBuilder
        .fromUriString(URLDecoder
            .decode(response.headers().get(HttpHeaders.LOCATION).iterator().next(),
                String.valueOf(StandardCharsets.ISO_8859_1)))
        .build().getQueryParams();
    return locationParams.getFirst(name);
  }

  private ApacheHttpClient buildApacheClient() {
    final HttpClientBuilder builder = HttpClientBuilder
        .create()
        .setUserTokenHandler(new DefaultUserTokenHandler ())
        .disableRedirectHandling();
    return new ApacheHttpClient(builder.build());
  }

  private ObjectMapper buildMapper() {
    final ObjectMapper mapper = new ObjectMapper ()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(new JavaTimeModule ());
    mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    return mapper;
  }

  private SignicatMock getProvider(final Environment env) {
    return Feign
        .builder()
        .client(buildApacheClient())
        .encoder(new FormEncoder(new JacksonEncoder(buildMapper())))
        .decoder(new JacksonDecoder(buildMapper()))
        .logger(new Slf4jLogger(SignicatMockHandlingService.class))
        .logLevel(Level.FULL)
        .target(SignicatMock.class,
            propertyProvider.getProperty(MOCK_URL));
  }

  private SignicatAuthorizeRequestDTO prepareRequest(final String transactionId,
      final String ssn) {
    final SignicatAuthorizeRequestDTO request = new SignicatAuthorizeRequestDTO();
    request.setState(transactionId);
    request.setRedirectUri(propertyProvider.getProperty(REDIRECT_URI));
    request.setSsn(ssn);
    request.setName(NAME);
    request.setLocale(LOCALE);
    request.setGname(GNAME);
    request.setFname(FNAME);
    request.setBank(BANK);
    return request;
  }
}
