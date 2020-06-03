package bindings.commons.automation.aio.rest.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class MunicipalityMX {

  private static final Logger LOGGER = LogManager.getLogger(MunicipalityMX.class);

  private static final String MUNICIPALITIES_SOURCE = "municipalities.properties";
  private static final Map<Integer, String> municipalities = new HashMap<>();
  private static final Properties properties = new Properties();
  private static final String SEPARATOR = ";";

  private MunicipalityMX() {
  }

  public static String getCategoryOfMunicipalityAnnualIncome(final MunicipalityDTO municipality) {

    return municipalities.get(municipality.hashCode()).toUpperCase();
  }

  public static String getMunicipality(final String municipalityAnnualIncome,
      final String province) {
    final List<String> results = new ArrayList<>();
    for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
      if (entry.getValue().toString().split(SEPARATOR)[0].equals(province)
          && entry.getValue().toString().split(SEPARATOR)[2].equals(municipalityAnnualIncome)) {
        results.add(entry.getValue().toString().split(SEPARATOR)[1]);
      }
    }
    if (results.isEmpty()) {
      final String error = String.format(
          "No matching municipality for given municipalityAnnualIncome (%s) and province (%s)",
          municipalityAnnualIncome, province);
      LOGGER.info(error);
      return null;
    }
    return results.get(new Random().nextInt(results.size()));
  }

  static {
    try {
      properties
          .load(MunicipalityMX.class.getClassLoader().getResourceAsStream(MUNICIPALITIES_SOURCE));
      LOGGER.info("Loaded municipalities from file: {}", MUNICIPALITIES_SOURCE);
    } catch (final IOException | NullPointerException e) {
      LOGGER.error("Unable to read municipalities from file: {}", MUNICIPALITIES_SOURCE);
    }
    properties.forEach((key, value) -> addMunicipalityValue(value));
  }

  private static String addMunicipalityValue(final Object value) {

    final String province = value.toString().split(SEPARATOR)[0];
    final String name = value.toString().split(SEPARATOR)[1];
    final String category = value.toString().split(SEPARATOR)[2];
    final MunicipalityDTO dto = new MunicipalityDTO();
    dto.name = name;
    dto.province = province;
    return municipalities.put(
        dto.hashCode(), category);
  }
}
