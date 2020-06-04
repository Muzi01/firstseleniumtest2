package bindings.cucumber.linkstatusverification;

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
    @JsonProperty("@timestamp")
    private String timestamp;
    private Integer processStep;
    private final String baseDir = getKibanaLogsFolder();

    public String getMessage() {
        return this.message;
    }

    public KibanaLoggerDetails() {
    }

    public KibanaLoggerDetails(KibanaLogger kibanaLogger) {
        this.setProcessId(kibanaLogger.getProcessId());
        this.setProcessStep(kibanaLogger.getProcessStep());
        this.setCountry(kibanaLogger.getCountry());
        this.setEnvironment(kibanaLogger.getEnvironment());
        this.generateTimestamp();
    }

    public void addKibanaLoggerData(KibanaLogger kibanaLogger) {
        this.setProcessId(kibanaLogger.getProcessId());
        this.setProcessStep(kibanaLogger.getProcessStep());
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException var2) {
            LOG.error("Can't convert KibanaLoggerDetails to JSON");
            return null;
        }
    }

    public void save() {
        if (this.isKibanaReportingEnabled()) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            try {
                writer.writeValue(new File(this.prepareFileName()), this);
            } catch (IOException var4) {
                LOG.error("Can't write to file");
                throw new IllegalStateException(var4.getMessage());
            }
        }

    }

    private String prepareFileName() {
        StringBuilder kibanaLogFileName = new StringBuilder();
        kibanaLogFileName.append(this.getProcessId());
        kibanaLogFileName.append("_");
        kibanaLogFileName.append(this.getProcessStep());
        kibanaLogFileName.append(".json");
        Path kibanaLogFilePath = Paths.get(this.baseDir, kibanaLogFileName.toString());
        Paths.get(this.baseDir).toFile().mkdirs();
        return kibanaLogFilePath.toString();
    }

    private boolean isKibanaReportingEnabled() {
        try {
            return System.getProperty("enableKibanaReport").equalsIgnoreCase("true");
        } catch (Exception var2) {
            return false;
        }
    }

    public static String getKibanaLogsFolder() {
        try {
            return Paths.get(System.getProperty("dir"), "kibanalogs").toString();
        } catch (Exception var1) {
            LOG.error("Dir property is not set");
            return Paths.get(System.getProperty("user.dir"), "target", "kibanalogs").toString();
        }
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getProcessStep() {
        return this.processStep;
    }

    public void setProcessStep(Integer processStep) {
        this.processStep = processStep;
    }

    private void generateTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.setTimestamp(simpleDateFormat.format(new Date()));
    }
}
