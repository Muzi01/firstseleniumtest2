package bindings.commons;



import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import bindings.commons.Buffer;
import cucumber.api.Scenario;
import org.springframework.stereotype.Service;


@Service
public class CucumberAdditionalInfoService {
    private static final String LINE_SEPARATOR_SYSTEM_PROPERTY = "line.separator";
    private static final String LINE_SEPARATOR = System.getProperty(LINE_SEPARATOR_SYSTEM_PROPERTY);

    public void logDefaultValuesAdditionalInformation(final Scenario scenario,
                                                      final Map<String, String> map) {
        final String testParameters =
                prepareLogMessage(map);
        final byte[] bytes = testParameters.getBytes(StandardCharsets.UTF_8);
        scenario.embed(bytes, "text/plain");
    }

    public String prepareLogMessage(final Map<String, String> map) {
        if (map.isEmpty()) {
            return "Customer data was not received, please check logs!";
        }

        return prepareLogMessageFromMap(map);
    }

    private String prepareLogMessageFromMap(final Map<String, String> map) {
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public void logBufferValues(final Scenario scenario) {
        final String testParameters = Buffer.entriesToString();
        final byte[] bytes = testParameters.getBytes(StandardCharsets.UTF_8);
        scenario.embed(bytes, "text/plain");
    }

}
