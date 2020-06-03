package bindings.commons.automation.aio.rest.dto;

import com.ipfdigital.automation.customer.Gender;

import java.time.LocalDate;

public class CustomerDTO {
  public String id;
  public String firstName;
  public String lastName;
  public String secondLastName;
  public LocalDate dateOfBirth;
  public Gender gender;
  public String countryOfBirth;
  public String msisdn2;
}
