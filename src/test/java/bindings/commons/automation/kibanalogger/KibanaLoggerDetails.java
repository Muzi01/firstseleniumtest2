package bindings.commons.automation.kibanalogger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class KibanaLoggerDetails {

  private static final Logger LOG = LogManager.getLogger(KibanaLoggerDetails.class);
  private static final String KIBANA_LOGS_FOLDER = "kibanalogs";

  private String message;
  private String processId;
  private String environment;
  private String country;
  @JsonProperty ("@timestamp")
  private String timestamp;
  private Integer processStep;
  private final String baseDir = getKibanaLogsFolder();

  public String getMessage() {
    return this.message;
  }

  public KibanaLoggerDetails() {

  }

  public KibanaLoggerDetails(final KibanaLogger kibanaLogger) {
    setProcessId(kibanaLogger.getProcessId());
    setProcessStep(kibanaLogger.getProcessStep());
    setCountry(kibanaLogger.getCountry());
    setEnvironment(kibanaLogger.getEnvironment());
    generateTimestamp();
  }

  public void addKibanaLoggerData(final KibanaLogger kibanaLogger) {
    this.setProcessId(kibanaLogger.getProcessId());
    this.setProcessStep(kibanaLogger.getProcessStep());
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String toJson() {
    try {
      final ObjectMapper mapper = new ObjectMapper ();
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    } catch (final JsonProcessingException e) {
      LOG.error("Can't convert KibanaLoggerDetails to JSON");
      return null;
    }
  }

  public void save() {
    if (isKibanaReportingEnabled()) {
      final ObjectMapper mapper = new ObjectMapper ();
      final ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter ());
      try {
        writer.writeValue(new File(prepareFileName()), this);
      } catch (final IOException e) {
        LOG.error("Can't write to file");
        throw new IllegalStateException(e.getMessage());
      }
    }
  }

  private String prepareFileName() {
    final StringBuilder kibanaLogFileName = new StringBuilder();
    kibanaLogFileName.append(this.getProcessId());
    kibanaLogFileName.append("_");
    kibanaLogFileName.append(this.getProcessStep());
    kibanaLogFileName.append(".json");
    final Path kibanaLogFilePath = Paths.get(this.baseDir, kibanaLogFileName.toString());
    Paths.get(this.baseDir).toFile().mkdirs();
    return kibanaLogFilePath.toString();
  }

  private boolean isKibanaReportingEnabled() {
    try {
      return System.getProperty("enableKibanaReport").equalsIgnoreCase("true");
    } catch (final Exception e) {
      return false;
    }
  }

  // Used by new version of generator to dynamically set kibana logs folder
  public static String getKibanaLogsFolder() {
    try {
      return Paths.get(System.getProperty("dir"), KIBANA_LOGS_FOLDER).toString();
    } catch (final Exception e) {
      LOG.error("Dir property is not set");
      return Paths.get(System.getProperty("user.dir"), "target", KIBANA_LOGS_FOLDER).toString();
    }
  }

  public String getProcessId() {
    return this.processId;
  }

  public void setProcessId(final String processId) {
    this.processId = processId;
  }

  public String getEnvironment() {
    return this.environment;
  }

  public void setEnvironment(final String environment) {
    this.environment = environment;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(final String timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getProcessStep() {
    return this.processStep;
  }

  public void setProcessStep(final Integer processStep) {
    this.processStep = processStep;
  }

  private void generateTimestamp() {
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    setTimestamp(simpleDateFormat.format(new Date()));
  }
}
