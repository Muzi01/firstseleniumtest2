package bindings.commons.automation.customer.ssn;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.customer.Gender;

import java.time.LocalDate;

public class GenerateSsnParamsDTO {
  private Gender gender;
  private LocalDate birthDate;
  private SsnType ssnType;
  private Country country;

  public GenerateSsnParamsDTO(final Gender gender, final LocalDate birthDate,
      final SsnType ssnType,
      final Country country) {
    this.gender = gender;
    this.birthDate = birthDate;
    this.ssnType = ssnType;
    this.country = country;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(final Gender gender) {
    this.gender = gender;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(final LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public SsnType getSsnType() {
    return ssnType;
  }

  public void setSsnType(final SsnType ssnType) {
    this.ssnType = ssnType;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }
}
