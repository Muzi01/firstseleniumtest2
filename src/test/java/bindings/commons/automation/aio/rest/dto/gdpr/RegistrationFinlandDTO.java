package bindings.commons.automation.aio.rest.dto.gdpr;

import com.ipfdigital.automation.aio.rest.dto.CommunicationSettingsDTO;
import com.ipfdigital.automation.aio.rest.dto.EmailUpdateDTO;
import com.ipfdigital.automation.aio.rest.dto.MsisdnVerificationDTO;

public class RegistrationFinlandDTO {
  public CommunicationSettingsDTO communicationSettings;
  public EmailUpdateDTO email;
  public MsisdnVerificationDTO msisdnVerification;
  public PasswordGdprDTO password;
}
