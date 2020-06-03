package bindings.commons.automation.generator.model.aio.dao;

import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductType;

public interface ProductDAO extends GenericDAO<Product> {
  ProductType getProductTypeForCustomer(final Environment environment, final long customerId);
}
