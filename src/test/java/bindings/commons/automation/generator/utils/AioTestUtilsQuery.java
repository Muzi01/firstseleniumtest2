package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.utils.grt.GrtResponse;
import com.ipfdigital.database.connection.AioTestUtilsDbService;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AioTestUtilsQuery {
  private static final Logger LOG = LogManager.getLogger(AioTestUtilsDbService.class);
  private static final String EXTERNAL_COMPANY_RESULT_UPSERT =
      "INSERT INTO external_company_result " +
          "(company,country,identifier,data) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE " +
          "company=VALUES(company), country=VALUES(country), identifier=VALUES(identifier), data=VALUES(data)";

  private AioTestUtilsQuery() {
  }

  public static void updateGrtResult(final Environment environment,
      final Country country,
      final String identifier,
      final GrtResponse data) {

    DBServiceProvider.aioTestUtilsDbService().callDB(environment, EXTERNAL_COMPANY_RESULT_UPSERT,
        statement -> {
          statement.setString(1, "GRT");
          statement.setString(2, country.name());
          statement.setString(3, identifier);
          statement.setString(4, data.generate());
          if (statement.executeUpdate() != 1) {
            LOG.error("Upsert failed to aio test utils db [query={}]", statement.toString());
            throw new IllegalStateException("Upsert failed");
          }
          return null;
        });
  }

}
