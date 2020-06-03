package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ConsentLegalPageDTO;
import com.ipfdigital.automation.aio.rest.dto.au.CustomerAUWrapperDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ProductCreditLineAUWrapperDTO;
import com.ipfdigital.automation.aio.rest.dto.au.ProductInstallmentAUWrapperDTO;
import feign.Param;
import feign.RequestLine;

public interface AUCreditApplication extends GenericCreditApplicationClient {
  @RequestLine("POST /credit-application-au/info")
  void updateCustomerInfo(CustomerAUWrapperDTO updateCustomer);

  @RequestLine("POST /credit-application-au/info-final")
  void updateCustomerInfoFinal(CustomerAUWrapperDTO updateCustomer);

  @RequestLine("POST /credit-application-au/legal-page")
  void updateLegalPage(ConsentLegalPageDTO updateCustomer);

  @RequestLine("GET /credit-application-au/product-offer/credit-line")
  ProductCreditLineAUWrapperDTO getAvailableCreditLineProductsAU();

  @RequestLine("GET /credit-application-au/product-offer/installment")
  ProductInstallmentAUWrapperDTO getAvailableInstallmentProductsAU();

  @Override
  @RequestLine("PUT credit-application-au/product-offer")
  void selectProduct(ProductSelectionDTO productSelection);

  @RequestLine("GET /credit-application-au/initialise-proviso?redirect_url={redirectUrl}")
  void initialiseProviso(@Param("redirectUrl") String redirectUrl);
}
