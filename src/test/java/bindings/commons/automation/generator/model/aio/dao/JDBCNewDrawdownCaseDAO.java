package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.model.aio.cases.NewDrawdownCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCNewDrawdownCaseDAO implements NewDrawdownCaseDAO {
  private static final Logger LOGGER = LogManager.getLogger(JDBCNewDrawdownCaseDAO.class);

  private static final String SQL_GET_QUERY =
      "SELECT ndc.* FROM NewDrawdownCase ndc "
          + "JOIN CreditApplication ca ON ca.ID = ndc.creditApplication_ID "
          + "JOIN Customer cus ON ca.customer_ID = cus.ID "
          + "WHERE cus.ID = ? ORDER BY ndc.ID DESC";

  @Override
  public NewDrawdownCase getByCustomerId(final Long customerId, final String env) {
    LOGGER.info("Searching for NewDrawdownCase on env: {} for customerId: {}", env, customerId);
    return new JDBCUtils<NewDrawdownCase>().getByCustomerId(
        SQL_GET_QUERY, env, customerId,
        new NewDrawdownCaseMapper());
  }
}
