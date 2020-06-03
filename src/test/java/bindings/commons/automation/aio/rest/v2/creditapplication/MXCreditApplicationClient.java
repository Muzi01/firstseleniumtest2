package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.BankAccountProvisionsDTO;
import com.ipfdigital.automation.aio.rest.dto.EducationAndWorkDTO;
import com.ipfdigital.automation.aio.rest.dto.FamilyDTO;
import com.ipfdigital.automation.aio.rest.dto.MexicanFinancialDataDTO;
import com.ipfdigital.automation.aio.rest.dto.ProductSelectionDTO;
import com.ipfdigital.automation.aio.rest.dto.ReferencePersonDTO;
import feign.RequestLine;

public interface MXCreditApplicationClient extends GenericCreditApplicationClient {
  @RequestLine("PUT /credit-application-mx")
  void putCreditApplication();

  @RequestLine("PUT /credit-application-mx/clabe-info")
  void putClabeInfo(BankAccountProvisionsDTO bankAccountProvision);

  @RequestLine("PUT /credit-application-mx/financialdata-mx")
  void storeFinancialData(MexicanFinancialDataDTO financialData);

  @RequestLine("PUT /credit-application-mx/referencePerson")
  void storeReferencePerson(ReferencePersonDTO referencePerson);

  @RequestLine("PUT /credit-application-mx/family")
  void putFamily(FamilyDTO familyDTO);

  @RequestLine("PUT /credit-application-mx/education-work")
  void putEducationAndWork(EducationAndWorkDTO educationAndWorkDTO);

  @RequestLine("PUT /credit-application-mx/product")
  void putProduct(ProductSelectionDTO productSelectionDTO);
}
