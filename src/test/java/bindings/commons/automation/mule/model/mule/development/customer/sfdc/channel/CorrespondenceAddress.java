
package bindings.commons.automation.mule.model.mule.development.customer.sfdc.channel;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude (JsonInclude.Include.NON_NULL)
@JsonPropertyOrder ({
    "postcode",
    "city",
    "country",
    "floor",
    "streetName",
    "streetNumber",
    "neighbourhood",
    "municipality",
    "province",
    "door",
    "yearsAtAddress"
})
public class CorrespondenceAddress {

  @JsonProperty ("postcode")
  private String postcode;
  @JsonProperty ("city")
  private String city;
  @JsonProperty ("country")
  private String country;
  @JsonProperty ("floor")
  private String floor;
  @JsonProperty ("streetName")
  private String streetName;
  @JsonProperty ("streetNumber")
  private String streetNumber;
  @JsonProperty ("neighbourhood")
  private String neighbourhood;
  @JsonProperty ("municipality")
  private String municipality;
  @JsonProperty ("province")
  private String province;
  @JsonProperty ("door")
  private String door;
  @JsonProperty ("yearsAtAddress")
  private String yearsAtAddress;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("postcode")
  public String getPostcode() {
    return this.postcode;
  }

  @JsonProperty ("postcode")
  public void setPostcode(final String postcode) {
    this.postcode = postcode;
  }

  @JsonProperty ("city")
  public String getCity() {
    return this.city;
  }

  @JsonProperty ("city")
  public void setCity(final String city) {
    this.city = city;
  }

  @JsonProperty ("country")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty ("country")
  public void setCountry(final String country) {
    this.country = country;
  }

  @JsonProperty ("floor")
  public String getFloor() {
    return this.floor;
  }

  @JsonProperty ("floor")
  public void setFloor(final String floor) {
    this.floor = floor;
  }

  @JsonProperty ("streetName")
  public String getStreetName() {
    return this.streetName;
  }

  @JsonProperty ("streetName")
  public void setStreetName(final String streetName) {
    this.streetName = streetName;
  }

  @JsonProperty ("streetNumber")
  public String getStreetNumber() {
    return this.streetNumber;
  }

  @JsonProperty ("streetNumber")
  public void setStreetNumber(final String streetNumber) {
    this.streetNumber = streetNumber;
  }

  @JsonProperty ("neighbourhood")
  public String getNeighbourhood() {
    return this.neighbourhood;
  }

  @JsonProperty ("neighbourhood")
  public void setNeighbourhood(final String neighbourhood) {
    this.neighbourhood = neighbourhood;
  }

  @JsonProperty ("municipality")
  public String getMunicipality() {
    return this.municipality;
  }

  @JsonProperty ("municipality")
  public void setMunicipality(final String municipality) {
    this.municipality = municipality;
  }

  @JsonProperty ("province")
  public String getProvince() {
    return this.province;
  }

  @JsonProperty ("province")
  public void setProvince(final String province) {
    this.province = province;
  }

  @JsonProperty ("door")
  public String getDoor() {
    return this.door;
  }

  @JsonProperty ("door")
  public void setDoor(final String door) {
    this.door = door;
  }

  @JsonProperty ("yearsAtAddress")
  public String getYearsAtAddress() {
    return this.yearsAtAddress;
  }

  @JsonProperty ("yearsAtAddress")
  public void setYearsAtAddress(final String yearsAtAddress) {
    this.yearsAtAddress = yearsAtAddress;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(final String name, final Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(this.postcode)
        .append(this.city)
        .append(this.country)
        .append(this.floor)
        .append(this.streetName)
        .append(this.streetNumber)
        .append(this.neighbourhood)
        .append(this.municipality)
        .append(this.province)
        .append(this.door)
        .append(this.yearsAtAddress)
        .append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof CorrespondenceAddress)) {
      return false;
    }
    final CorrespondenceAddress rhs = ((CorrespondenceAddress) other);
    return new EqualsBuilder()
        .append(this.postcode, rhs.postcode).append(this.city, rhs.city)
        .append(this.country, rhs.country)
        .append(this.floor, rhs.floor)
        .append(this.streetName, rhs.streetName).append(this.streetNumber, rhs.streetNumber)
        .append(this.neighbourhood, rhs.neighbourhood).append(this.municipality, rhs.municipality)
        .append(this.province, rhs.province).append(this.door, rhs.door)
        .append(this.yearsAtAddress, rhs.yearsAtAddress)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
