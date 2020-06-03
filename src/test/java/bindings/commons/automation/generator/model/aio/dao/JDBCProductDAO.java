package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.model.aio.JDBCUtils;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;
import com.ipfdigital.database.connection.DBServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.ipfdigital.automation.generator.utils.ProductType.CL;
import static com.ipfdigital.automation.generator.utils.ProductType.IL;

public class JDBCProductDAO implements ProductDAO {

  private static final Logger LOGGER = LogManager.getLogger(JDBCProductDAO.class);

  private static final String SQL_GET_PRODUCT_QUERY =
      "SELECT * FROM Product p JOIN Contract co ON co.product_ID = p.ID JOIN Customer cu ON cu.ID = co.customer_ID WHERE cu.id = ? ORDER BY p.ID DESC";

  private static final String GET_PRODUCT_TYPE_BY_CUSTOMER_ID =
      "SELECT type FROM Product " +
          "JOIN Contract ON Contract.product_ID = Product.ID " +
          "WHERE customer_ID = ?";

  @Override
  public Product getByCustomerId(final Long customerId, final String env) {
    LOGGER.info("Searching for Product on env: {} for customerId: {}", env,
        customerId);
    return new JDBCUtils<Product>().getByCustomerId(
        SQL_GET_PRODUCT_QUERY, env, customerId, new ProductMapper());
  }

  @Override
  public ProductType getProductTypeForCustomer(final Environment environment,
      final long customerId) {
    return DBServiceProvider.aioDBService()
        .getQueryResult(environment, GET_PRODUCT_TYPE_BY_CUSTOMER_ID, "type", customerId)
        .equals("INSTALLMENT") ? IL : CL;
  }
}
