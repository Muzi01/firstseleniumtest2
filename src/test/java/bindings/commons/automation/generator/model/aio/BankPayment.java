package bindings.commons.automation.generator.model.aio;

import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class BankPayment {

  private static final Logger LOG = LogManager.getLogger(BankPayment.class);

  private static final String SQL_GET_QUERY =
      "SELECT ID FROM BankPayment WHERE externalReference = ?";

  private final Long id;

  public BankPayment(final Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public static BankPayment fromAioByExternalReference(final String env,
      final String externalReference) {

    LOG.info("Checking AIO db on " + env + " for BankPayments with externalReference: "
        + externalReference);

    return DBServiceProvider.aioDBService()
        .callDB(env, SQL_GET_QUERY, statement -> {
          statement.setString(1, externalReference);
          try (final ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              return new BankPayment(resultSet.getLong("ID"));
            }
            throw new IllegalStateException(
                "No BankPayments found with externalReference: " + externalReference);
          }
        });
  }
}
