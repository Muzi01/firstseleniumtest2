package bindings.commons.automation.generator.utils.grt;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class to construct GRT response. Meaning of these fields can be found at:
 * https://confluence.ipfdigital.tech/display/MCB/GRT+WebService
 */
public class GrtResponse {
  private final String ssn;
  private final String firstName;
  private final String lastName;
  private final String country;
  public String placeName = "";
  public String placeNameType = "";
  public String province = "";
  public String houseNumber = "";
  public String blockNumber = "";
  public String apartmentNumber = "";
  public String documentNumber = "";
  public String streetWithoutType = "";
  public String streetType = "";
  public String street = "";
  public String officialAddress = "";
  public GrtMaritalStatus maritalStatus = GrtMaritalStatus.NA;
  public String placeNameIn = "";
  public String numberOfDependants = "";
  public String errorText = "";
  public String dateOfDeath = "";

  public GrtResponse(String ssn, String firstName, String lastName, String country) {
    this.ssn = ssn;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
  }

  public String generate() {
    return new Builder()
        .tag("asmKodas", ssn)
        .tag("asm_n_koda", ssn)
        .tag("Klaida", errorText)
        .tag("vardas", firstName)
        .tag("pavarde", lastName)
        .tag("mirtData", dateOfDeath)
        .tag("adresas", officialAddress)
        .tag("salis", country).tag("seim", maritalStatus.value)
        .tag("vaikai", numberOfDependants)
        .tag("dok_numeris", documentNumber)
        .tag("VIET", placeName)
        .tag("VIETKLM", placeNameIn)
        .tag("VTIPAS", placeNameType)
        .tag("GATVE", street)
        .tag("GATVEBETIPO", streetWithoutType)
        .tag("GATVTIPAS", streetType)
        .tag("NAMAS", houseNumber)
        .tag("KORPUSAS", blockNumber)
        .tag("BUTAS", apartmentNumber).build();
  }

  private static class Builder {

    private final StringBuilder buffer = new StringBuilder();

    public Builder() {
      buffer.append(
          "<GetGrt2Response xmlns=\"http://tempuri.org/\"><GetGrt2Result><ROWSET xmlns=\"\"><ROW num=\"1\"><GRT2>");
    }

    private Builder tag(String tag, String value) {
      if (StringUtils.isBlank(value)) {
        return tag(tag);
      }
      buffer.append(String.format("<%s>%s</%s>%n", tag, value, tag));
      return this;
    }

    private Builder tag(String tag) {
      buffer.append(String.format("<%s/>%n", tag));
      return this;
    }

    String build() {
      return buffer.append("</GRT2></ROW></ROWSET></GetGrt2Result></GetGrt2Response>").toString();
    }
  }
}
