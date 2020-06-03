package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.AddressDTO;
import com.ipfdigital.automation.aio.rest.dto.ApplicationSubmitDTO;
import com.ipfdigital.automation.aio.rest.dto.FinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.FirstDrawAmountDTO;
import com.ipfdigital.automation.aio.rest.dto.LoanPurposeDTO;
import com.ipfdigital.automation.aio.rest.dto.MedicareDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.RegistrationDataDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.AuthpostofficeDTO;
import feign.RequestLine;

import java.util.List;

public interface GenericCreditApplicationClient {
  @RequestLine("PUT /credit-application/financialdata")
  void storeFinancialData(FinancialDataDTO financialData);

  @RequestLine("GET /credit-application/product/availability/INSTALLMENT")
  List<ProductDTO> getAvailableInstallmentProducts();

  @RequestLine("GET /credit-application/product/availability/CREDIT_LINE")
  List<ProductDTO> getAvailableCreditLineProducts();

  @RequestLine("PUT /credit-application/product")
  void selectProduct(ProductSelectionDTO productSelection);

  @RequestLine("PUT /credit-application/loanpurpose")
  void storeLoanPurpose(LoanPurposeDTO loanPurpose);

  @RequestLine("POST /credit-application/submit")
  void submitApplication(ApplicationSubmitDTO applicationSubmit);

  @RequestLine("PUT /credit-application/extra-services/MEDICARE")
  void putMedicare(MedicareDTO medicare);

  @RequestLine("PUT /credit-application")
  void startNewApplication(RegistrationDataDTO data);

  @RequestLine("PUT /credit-application")
  void startNewApplication();

  @RequestLine("PUT /credit-application/authpostoffice")
  void updateAuthPostOffice(AuthpostofficeDTO authpostoffice);

  @RequestLine("PUT /credit-application/updatelivingaddress")
  void updateLivingAddress(AddressDTO addressDTO);

  @RequestLine("PUT /credit-application/updateDrawAmount")
  void updateDrawAmount(FirstDrawAmountDTO firstDrawAmountDTO);

  @RequestLine("DELETE /credit-application/duedate")
  void deleteDueDate();
}
