package bindings.commons.automation.mule.model.thirdpartyclient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public class CustomerPartnerBodyDTO {

  public String accountNumber;
  public String applicationChannel;
  @JsonDeserialize (using = LocalDateTimeDeserializer.class)
  public LocalDateTime applicationLastModifiedDate;
  public String applicationNumber;
  public String applicationStatus;
  public String caseType;
  public boolean hasAttachment;
  public String productAmountTaken;
  public String taskStatus;
}
