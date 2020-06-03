package bindings.commons.automation.aio.rest.v2.creditapplication;

import com.ipfdigital.automation.aio.rest.dto.PersonalInformationDTO;
import com.ipfdigital.automation.aio.rest.dto.gdpr.PermissionsDTO;
import feign.RequestLine;

public interface LTCreditApplicationClient extends GenericCreditApplicationClient {
  @RequestLine("POST /credit-application-lt/fetch-external-registries")
  void fetchExternalRegistries();

  @RequestLine("PUT /credit-application-lt/update-permissions")
  void updatePermissions(PermissionsDTO permissions);

  @RequestLine("PUT /credit-application-lt/personal-info")
  void updatePersonalInformation(PersonalInformationDTO personalInformation);
}
