package bindings.commons.automation.generator.model.web;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.portals.Brand;
import com.ipfdigital.automation.generator.utils.Environment;
import com.ipfdigital.automation.generator.utils.ProductLevel;
import com.ipfdigital.automation.generator.utils.ProductType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RegistrationParamsDTO {

  private Environment environment;
  private Country country;
  private Brand brand;
  private ProductType productType;
  private Integer drawPercentage;
  private ProductLevel productLevel;
  private Boolean verification;
  private Boolean foreignCustomer;
  private Boolean debitCard;
  private Boolean webRegistration;
  private Boolean customEmail;
  private String customEmailValue;
  private String processId;
  private Integer scoring;
  private Boolean customSsn;
  private String customSsnValue;
  private String communicationLanguage;
  private String customIncomeFrequency;
  private Integer maturiyPeriods;

  public Boolean isWebRegistration() {
    return webRegistration;
  }

  public void setWebRegistration(final Boolean registeredByWeb) {
    webRegistration = registeredByWeb;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(final Environment environment) {
    this.environment = environment;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(final Brand brand) {
    this.brand = brand;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(final ProductType productType) {
    this.productType = productType;
  }

  public Integer getDrawPercentage() {
    return drawPercentage;
  }

  public void setDrawPercentage(final Integer drawPercentage) {
    this.drawPercentage = drawPercentage;
  }

  public ProductLevel getProductLevel() {
    return productLevel;
  }

  public void setProductLevel(final ProductLevel productLevel) {
    this.productLevel = productLevel;
  }

  public Boolean getVerification() {
    return verification;
  }

  public void setVerification(final Boolean verification) {
    this.verification = verification;
  }

  public Boolean getForeignCustomer() {
    return foreignCustomer;
  }

  public void setForeignCustomer(final Boolean foreignCustomer) {
    this.foreignCustomer = foreignCustomer;
  }

  public Boolean getDebitCard() {
    return debitCard;
  }

  public void setDebitCard(final Boolean debitCard) {
    this.debitCard = debitCard;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public Boolean getCustomEmail() {
    return customEmail;
  }

  public void setCustomEmail(final Boolean customEmail) {
    this.customEmail = customEmail;
  }

  public String getCustomEmailValue() {
    return customEmailValue;
  }

  public void setCustomEmailValue(final String customEmailValue) {
    this.customEmailValue = customEmailValue;
  }

  public String getProcessId() {
    return processId;
  }

  public void setProcessId(final String processId) {
    this.processId = processId;
  }

  public Integer getScoring() {
    return scoring;
  }

  public void setScoring(final Integer scoring) {
    this.scoring = scoring;
  }

  public Boolean getCustomSsn() {
    return customSsn;
  }

  public void setCustomSsn(final Boolean customSsn) {
    this.customSsn = customSsn;
  }

  public String getCustomSsnValue() {
    return customSsnValue;
  }

  public void setCustomSsnValue(final String customSsnValue) {
    this.customSsnValue = customSsnValue;
  }

  public String getCommunicationLanguage() {
    return communicationLanguage;
  }

  public void setCommunicationLanguage(final String communicationLanguage) {
    this.communicationLanguage = communicationLanguage;
  }

  public String getCustomIncomeFrequency() {
    return customIncomeFrequency;
  }

  public void setCustomIncomeFrequency(final String customIncomeFrequency) {
    this.customIncomeFrequency = customIncomeFrequency;
  }

  public Integer getMaturiyPeriods() {
    return maturiyPeriods;
  }

  public void setMaturiyPeriods(final Integer maturiyPeriods) {
    this.maturiyPeriods = maturiyPeriods;
  }
}
