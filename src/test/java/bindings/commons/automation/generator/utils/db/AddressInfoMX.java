package bindings.commons.automation.generator.utils.db;

import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressInfoMX {

  private static final Logger LOGGER = LogManager.getLogger(AddressInfoMX.class);

  private AddressInfoMX() {
  }

  private static final String NEIGHBOURHOOD_BY_POSTCODE_QUERY =
      "SELECT neighbourhood FROM AddressInfo " +
          "WHERE country = 'MX' AND postcode = ?";

  private static final String MUNICIPALITY_BY_NEIGHBOURHOOD_QUERY =
      "SELECT municipality FROM AddressInfo " +
          "WHERE country = 'MX' AND neighbourhood = ?";

  public static List<String> neighbourhoodsByPostcode(final Environment environment,
      final String postcode) {

    LOGGER.info("Checking {} db for neighbourhoods by postcode: {}", environment, postcode);

    final List<String> results =
        DBServiceProvider.aioDBService()
            .getQueryResults(environment, NEIGHBOURHOOD_BY_POSTCODE_QUERY, "neighbourhood",
                postcode);
    LOGGER.info("Found neighbourhoods: {}", results);

    return results;
  }

  public static String municipalityByNeighbourhood(final Environment environment,
      final String neighbourhood) {

    LOGGER.info("Checking {} db for municipality for neighbourhood: {}", environment,
        neighbourhood);
    final String municipality = getMunicipality(neighbourhood, environment);
    LOGGER.info("Found municipality: {}", municipality);

    return municipality;
  }

  private static String getMunicipality(final String neighbourhood,
      final Environment environment) {

    return DBServiceProvider.aioDBService()
        .getQueryResult(environment, MUNICIPALITY_BY_NEIGHBOURHOOD_QUERY,
            "municipality", neighbourhood);
  }
}
