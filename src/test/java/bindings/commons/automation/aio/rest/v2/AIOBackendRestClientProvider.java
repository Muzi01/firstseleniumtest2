package bindings.commons.automation.aio.rest.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ipfdigital.automation.config.PropertyProvider;
import com.ipfdigital.automation.config.PropertyProviderFactory;
import com.ipfdigital.automation.customer.Country;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Optional;

public class AIOBackendRestClientProvider {

  private static final String BASIC_ADDRESS_TEMPLATE = "endpoint.%s.%s.rest";
  private static final String CONNECTION_TIMEOUT_KEY = "rest.aio.timeout.connection";
  private static final String READ_TIMEOUT_KEY = "rest.aio.timeout.read";

  private final PropertyProvider propertyProvider;
  private final SessionIdRequestInterceptor sessionIdRequestInterceptor;
  private final SessionIdResponseInterceptor sessionIdResponseInterceptor;
  private final TraceIdRequestInterceptor traceIdRequestInterceptor;
  private final TraceIdResponseInterceptor traceIdResponseInterceptor;
  private final String brand;
  private final Country country;



  public AIOBackendRestClientProvider(final String env, final String brand,
      final Country country,
      final Optional<AIORestEventListener> listener) {
    this(env, brand, country, new SessionStore(), listener);
  }

  public AIOBackendRestClientProvider(final String env, final String brand,
      final Country country,
      final SessionStore sessionStore, final Optional<AIORestEventListener> listener) {
    this.brand = brand;
    this.country = country;
    propertyProvider = PropertyProviderFactory.propertyProvider(env);
    sessionIdRequestInterceptor =
        new SessionIdRequestInterceptor(brand, country, sessionStore);
    sessionIdResponseInterceptor = new SessionIdResponseInterceptor(sessionStore);
    traceIdRequestInterceptor = new TraceIdRequestInterceptor();
    traceIdResponseInterceptor = new TraceIdResponseInterceptor(listener);
  }

  public String getBrand() {
    return brand;
  }

  public Country getCountry() {
    return country;
  }

  public <T> T provide(final Class<T> tClass) {
    return Feign
        .builder()
        .client(buildApacheClient())
        .encoder(new FormEncoder(new JacksonEncoder(buildMapper())))
        .decoder(new JacksonDecoder(buildMapper()))
        .logger(new Slf4jLogger(AIOBackendRestClientProvider.class))
        .logLevel(Logger.Level.FULL)
        .target(tClass, getBasicAddress());
  }

  public String getBasicAddress() {
    return propertyProvider.getProperty(
        String.format(BASIC_ADDRESS_TEMPLATE, brand, country.toString().toLowerCase()));
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

  private ApacheHttpClient buildApacheClient() {
    final HttpClientBuilder builder = HttpClientBuilder
        .create()
        .setUserTokenHandler(new DefaultUserTokenHandler ())
        .disableRedirectHandling()
        .setDefaultRequestConfig(returnRequestConfig())
        .addInterceptorFirst(sessionIdRequestInterceptor)
        .addInterceptorFirst(traceIdRequestInterceptor)
        .addInterceptorLast(traceIdResponseInterceptor)
        .addInterceptorLast(sessionIdResponseInterceptor);


    return new ApacheHttpClient(builder.build());
  }

  private RequestConfig returnRequestConfig() {
    return RequestConfig.custom()
        .setConnectTimeout(Integer.parseInt(propertyProvider.getProperty(READ_TIMEOUT_KEY)))
        .setConnectionRequestTimeout(
            Integer.parseInt(propertyProvider.getProperty(CONNECTION_TIMEOUT_KEY)))
        .setSocketTimeout(
            Integer.parseInt(propertyProvider.getProperty(CONNECTION_TIMEOUT_KEY)))
        .build();
  }
}
