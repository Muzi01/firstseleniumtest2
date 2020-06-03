package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.aio.rest.v2.AIOBackendRestClientProvider;
import com.ipfdigital.automation.generator.exceptions.NotImplementedException;
import com.ipfdigital.automation.generator.model.aio.Customer;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.RegistrationParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SvingLT extends Portal {

  private static final String NOT_IMPLEMENTED_MESSAGE =
      "This method is not implemented yet for Sving LT";

  private static final Logger LOG = LogManager.getLogger(SvingLT.class);

  private SvingLT() {
    LOG.info("Instantiated SvingLT portal.");
  }

  private static class SingletonHolder {
    private SingletonHolder() {
    }

    static final SvingLT instance = new SvingLT();
  }

  public static SvingLT getInstance() {
    return SingletonHolder.instance;
  }

  @Override
  public Customer createCustomerAccount(final RegistrationParams params,
      final AIOBackendRestClientProvider provider) {
    throw new UnsupportedOperationException(
        "LT market doesn't support only account creation for Customer");
  }

  @Override
  public Customer createCustomerWithApplication(final RegistrationParams params) {
    throw new NotImplementedException(NOT_IMPLEMENTED_MESSAGE);
  }

  @Override
  public void verifyCustomer(final Customer customer, final boolean isDraw,
      final Environment environment) {
    checkVerificationImplementation();
  }

  @Override
  public void verification(final RegistrationParams params, final Customer customer) {
    checkVerificationImplementation();
  }

  @Override
  public void verifyDrawCase(final Customer customer, final Environment environment) {
    checkVerificationImplementation();
  }

  @Override
  public void checkVerificationImplementation() {
    throw new NotImplementedException(NOT_IMPLEMENTED_MESSAGE);
  }

}
