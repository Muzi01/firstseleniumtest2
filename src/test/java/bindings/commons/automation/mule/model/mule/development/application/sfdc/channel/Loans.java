
package bindings.commons.automation.mule.model.mule.development.application.sfdc.channel;

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
    "consumer",
    "student",
    "leasing",
    "guaranteesurety",
    "fast",
    "other",
    "creditcard",
    "overdraft",
    "house"
})
public class Loans {

  @JsonProperty ("consumer")
  private Boolean consumer;
  @JsonProperty ("student")
  private Boolean student;
  @JsonProperty ("leasing")
  private Boolean leasing;
  @JsonProperty ("guaranteesurety")
  private Boolean guaranteesurety;
  @JsonProperty ("fast")
  private Boolean fast;
  @JsonProperty ("other")
  private Boolean other;
  @JsonProperty ("creditcard")
  private Boolean creditcard;
  @JsonProperty ("overdraft")
  private Boolean overdraft;
  @JsonProperty ("house")
  private Boolean house;
  @JsonIgnore
  private final Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty ("consumer")
  public Boolean getConsumer() {
    return this.consumer;
  }

  @JsonProperty ("consumer")
  public void setConsumer(final Boolean consumer) {
    this.consumer = consumer;
  }

  @JsonProperty ("student")
  public Boolean getStudent() {
    return this.student;
  }

  @JsonProperty ("student")
  public void setStudent(final Boolean student) {
    this.student = student;
  }

  @JsonProperty ("leasing")
  public Boolean getLeasing() {
    return this.leasing;
  }

  @JsonProperty ("leasing")
  public void setLeasing(final Boolean leasing) {
    this.leasing = leasing;
  }

  @JsonProperty ("guaranteesurety")
  public Boolean getGuaranteesurety() {
    return this.guaranteesurety;
  }

  @JsonProperty ("guaranteesurety")
  public void setGuaranteesurety(final Boolean guaranteesurety) {
    this.guaranteesurety = guaranteesurety;
  }

  @JsonProperty ("fast")
  public Boolean getFast() {
    return this.fast;
  }

  @JsonProperty ("fast")
  public void setFast(final Boolean fast) {
    this.fast = fast;
  }

  @JsonProperty ("other")
  public Boolean getOther() {
    return this.other;
  }

  @JsonProperty ("other")
  public void setOther(final Boolean other) {
    this.other = other;
  }

  @JsonProperty ("creditcard")
  public Boolean getCreditcard() {
    return this.creditcard;
  }

  @JsonProperty ("creditcard")
  public void setCreditcard(final Boolean creditcard) {
    this.creditcard = creditcard;
  }

  @JsonProperty ("overdraft")
  public Boolean getOverdraft() {
    return this.overdraft;
  }

  @JsonProperty ("overdraft")
  public void setOverdraft(final Boolean overdraft) {
    this.overdraft = overdraft;
  }

  @JsonProperty ("house")
  public Boolean getHouse() {
    return this.house;
  }

  @JsonProperty ("house")
  public void setHouse(final Boolean house) {
    this.house = house;
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
    return new HashCodeBuilder().append(this.consumer).append(this.student).append(this.leasing)
        .append(this.guaranteesurety).append(this.fast).append(this.other).append(this.creditcard)
        .append(
            this.overdraft)
        .append(this.house).append(this.additionalProperties).toHashCode();
  }

  @Override
  public boolean equals(final Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Loans)) {
      return false;
    }
    final Loans rhs = ((Loans) other);
    return new EqualsBuilder().append(this.consumer, rhs.consumer).append(this.student, rhs.student)
        .append(this.leasing, rhs.leasing).append(this.guaranteesurety, rhs.guaranteesurety)
        .append(this.fast, rhs.fast).append(other, rhs.other)
        .append(this.creditcard, rhs.creditcard)
        .append(this.overdraft, rhs.overdraft).append(this.house, rhs.house)
        .append(this.additionalProperties, rhs.additionalProperties).isEquals();
  }

}
