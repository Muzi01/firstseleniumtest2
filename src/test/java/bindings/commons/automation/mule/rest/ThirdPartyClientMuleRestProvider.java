package bindings.commons.automation.mule.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyClientMuleRestProvider {

  @Value ("${mule.internal}")
  public String address;

  @Value ("${mule.contesta.user}")
  public String contestaUser;

  @Value ("${mule.contesta.password}")
  public String constestaUserPasswd;


  public <T> T provide(final Class<T> tClass) {
    return Feign
        .builder()
        .client(buildApacheClient())
        .encoder(new FormEncoder(new JacksonEncoder(buildMapper())))
        .decoder(new JacksonDecoder(buildMapper()))
        .logger(new Slf4jLogger(ThirdPartyClientMuleRestProvider.class))
        .logLevel(Logger.Level.FULL)
        .target(tClass, address);
  }


  private ApacheHttpClient buildApacheClient() {
    final CredentialsProvider provider = new BasicCredentialsProvider ();
    final UsernamePasswordCredentials credentials =
        new UsernamePasswordCredentials (contestaUser, constestaUserPasswd);
    provider.setCredentials(AuthScope.ANY, credentials);
    final HttpClientBuilder builder = HttpClientBuilder
        .create()
        .setDefaultCredentialsProvider(provider)
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

}
