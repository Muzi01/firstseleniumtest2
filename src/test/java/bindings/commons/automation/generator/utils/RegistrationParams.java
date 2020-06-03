package bindings.commons.automation.generator.utils;

import com.ipfdigital.automation.customer.Country;
import com.ipfdigital.automation.generator.exceptions.RequiredParameterException;
import com.ipfdigital.automation.generator.model.web.RegistrationParamsDTO;
import com.ipfdigital.automation.generator.portals.Brand;
import org.apache.commons.lang3.builder.ToStringStyle;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class RegistrationParams {

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
  private Integer principal;
  private Integer maturiyPeriods;
  private String productName;
  private String productGroupName;
  private Integer scoring;
  private Boolean skipInsertToMule;
  private Boolean customSsn;
  private String customSsnValue;
  private String communicationLanguage;
  private String customIncomeFrequency;
  private boolean equifaxCache = true;

  public RegistrationParams(final RegistrationParamsDTO dto) {
    fillParams(dto);
    verifyParams();
  }

  private void fillParams(final RegistrationParamsDTO dto) {
    environment = dto.getEnvironment();
    country = dto.getCountry();
    brand = dto.getBrand();
    productType = dto.getProductType();
    drawPercentage = dto.getDrawPercentage();
    productLevel = dto.getProductLevel();
    verification = dto.getVerification();
    foreignCustomer = dto.getForeignCustomer();
    debitCard = dto.getDebitCard();
    webRegistration = dto.isWebRegistration();
    scoring = dto.getScoring();
    customIncomeFrequency = dto.getCustomIncomeFrequency();
    maturiyPeriods = dto.getMaturiyPeriods();
    fillCustomParams(dto);
  }

  private void fillCustomParams(final RegistrationParamsDTO dto) {
    customEmail = dto.getCustomEmail();
    customEmailValue = dto.getCustomEmailValue();
    customSsn = dto.getCustomSsn();
    customSsnValue = dto.getCustomSsnValue();
    communicationLanguage = dto.getCommunicationLanguage();
  }

  private RegistrationParams(final Builder builder) {
    fillParamsConfiguration(builder);
    fillParamsCustomer(builder);
    verifyParams();
  }

  private void fillParamsCustomer(final Builder builder) {
    foreignCustomer = builder.foreignCustomer;
    debitCard = builder.debitCard;
    customEmail = builder.customEmail;
    customEmailValue = builder.customEmailValue;
    principal = builder.principal;
    maturiyPeriods = builder.maturiyPeriods;
    productName = builder.productName;
    productGroupName = builder.productGroupName;
    scoring = builder.scoring;
    customSsn = builder.customSsn;
    customSsnValue = builder.customSsnValue;
    customIncomeFrequency = builder.incomeFrequency;
  }

  private void fillParamsConfiguration(final Builder builder) {
    environment = builder.environment;
    country = builder.country;
    brand = builder.brand;
    productType = builder.productType;
    drawPercentage = builder.drawPercentage;
    productLevel = builder.productLevel;
    verification = builder.verification;
    webRegistration = builder.webRegistration;
    skipInsertToMule = builder.skipInsertToMule;
    communicationLanguage = builder.communicationLanguage;
  }

  /**
   * Set default values if params are null
   */
  private void verifyParams() {
    verifyEnvSettings();
    verifyProduct();
    verifyCustomerData();
    if (verification == null) {
      verification = true;
    }
    if (webRegistration == null) {
      webRegistration = true;
    }
    if (principal == null)
      principal = 0;
    if (maturiyPeriods == null)
      maturiyPeriods = 0;
    if (skipInsertToMule == null) {
      skipInsertToMule = false;
    }
    if (communicationLanguage == null || communicationLanguage.isEmpty()) {
      communicationLanguage = country.getLanguage();
    }
  }

  private void verifyEnvSettings() {
    if (environment == null) {
      throw new RequiredParameterException("Environment can't be null!");
    }
    if (country == null) {
      throw new RequiredParameterException("Country can't be null!");
    }
    if (brand == null) {
      brand = defaultBrand(country);
    }
  }

  private void verifyProduct() {
    if (productType == null) {
      productType = defaultProductType(country);
    }
    if (drawPercentage == null || drawPercentage > 100) {
      drawPercentage = 100;
    }
    if (productLevel == null) {
      productLevel = ProductLevel.TOP;
    }
  }

  private void verifyCustomerData() {
    if (foreignCustomer == null) {
      foreignCustomer = false;
    }
    if (debitCard == null) {
      debitCard = false;
    }
    if (customEmail == null)
      customEmail = false;
    if (customEmailValue == null)
      customEmailValue = "";
    if (customSsn == null) {
      customSsn = false;
    }
    if (customSsnValue == null) {
      customSsnValue = "";
    }
  }

  private Brand defaultBrand(final Country country) {
    switch (country) {
      case FI:
      case LT:
      case LV:
      case EE:
      case AU:
        return Brand.CREDIT24;
      case PL:
      case ES:
      case MX:
        return Brand.HAPI;
      default:
        throw new IllegalArgumentException("No default brand for country: " + country);
    }
  }

  private ProductType defaultProductType(final Country country) {
    if (country == Country.PL) {
      return ProductType.IL;

    } else {
      return ProductType.CL;
    }
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

  public Boolean isWebRegistration() {
    return webRegistration;
  }

  public void setWebRegistration(final Boolean webRegistration) {
    this.webRegistration = webRegistration;
  }

  @Override
  public String toString() {
    return reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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


  public void setPrincipal(final Integer principal) {
    this.principal = principal;
  }


  public Integer getPrincipal() {
    return principal;
  }

  public void setMaturiyPeriods(final Integer maturiyPeriods) {
    this.maturiyPeriods = maturiyPeriods;
  }

  public Integer getMaturiyPeriods() {
    return maturiyPeriods;
  }


  public void setProductName(final String productName) {
    this.productName = productName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductGroupName(final String productGroupName) {
    this.productGroupName = productGroupName;
  }


  public String getProductGroupName() {
    return productGroupName;
  }

  public Integer getScoring() {
    return scoring;
  }

  public void setScoring(final int scoring) {
    this.scoring = scoring;
  }

  public Boolean getSkipInsertToMule() {
    return skipInsertToMule;
  }

  public void setSkipInsertToMule(final Boolean skipInsertToMule) {
    this.skipInsertToMule = skipInsertToMule;
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

  public String getIncomeFrequency() {
    return customIncomeFrequency;
  }

  public void setIncomeFrequency(final String incomeFrequency) {
    customIncomeFrequency = incomeFrequency;
  }

  public boolean isEquifaxCache() {
    return equifaxCache;
  }

  public void setEquifaxCache(final boolean equifaxCache) {
    this.equifaxCache = equifaxCache;
  }

  public static class Builder {
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
    private Boolean customSsn;
    private String customSsnValue;
    private int principal;
    private int maturiyPeriods;
    private String productName;
    private String productGroupName;
    private Integer scoring;
    private Boolean skipInsertToMule;
    private String communicationLanguage;
    private String incomeFrequency;

    public Builder withEnvironment(final Environment environment) {
      this.environment = environment;
      return this;
    }

    public Builder withEnvironment(final String envName) {
      environment = Environment.forName(envName);
      return this;
    }

    public Builder withCountry(final Country country) {
      this.country = country;
      return this;
    }

    public Builder withBrand(final Brand brand) {
      this.brand = brand;
      return this;
    }

    public Builder withProductType(final ProductType productType) {
      this.productType = productType;
      return this;
    }

    public Builder withFullDraw() {
      drawPercentage = 100;
      return this;
    }

    public Builder withoutDraw() {
      drawPercentage = 0;
      return this;
    }

    public Builder withPartialDraw(final Integer drawPercentage) {
      this.drawPercentage = drawPercentage;
      return this;
    }

    public Builder withProductLevel(final ProductLevel productLevel) {
      this.productLevel = productLevel;
      return this;
    }

    public Builder withMaturity(final Integer maturity) {
      maturiyPeriods = maturity;
      return this;
    }

    public Builder withSpecificProduct(final int principal, final int maturityPeriods,
        final String name,
        final String groupName) {
      this.principal = principal;
      maturiyPeriods = maturityPeriods;
      productName = name;
      productGroupName = groupName;
      return this;
    }

    public Builder withVerification() {
      verification = true;
      return this;
    }

    public Builder withVerification(final Boolean verification) {
      this.verification = verification;
      return this;
    }

    public Builder withoutVerification() {
      verification = false;
      return this;
    }

    public Builder withForeignCustomer() {
      foreignCustomer = true;
      return this;
    }

    public Builder withForeignCustomer(final Boolean foreignCustomer) {
      this.foreignCustomer = foreignCustomer;
      return this;
    }

    public Builder withDebitCard() {
      debitCard = true;
      return this;
    }

    public Builder withDebitCard(final Boolean debitCard) {
      this.debitCard = debitCard;
      return this;
    }

    public Builder withWebRegistration(final Boolean webRegistration) {
      this.webRegistration = webRegistration;
      return this;
    }

    public Builder withCustoEmail(final Boolean customEmail) {
      this.customEmail = customEmail;
      return this;
    }

    public Builder withCustomEmailValue(final String customEmailValue) {
      this.customEmailValue = customEmailValue;
      return this;
    }

    public Builder withCustomSSNValue(final String customSsnValue) {
      this.customSsnValue = customSsnValue;
      return this;
    }

    public Builder withCustomSSN(final boolean customSsn) {
      this.customSsn = customSsn;
      return this;
    }

    public Builder withScoring(final Integer scoring) {
      this.scoring = scoring;
      return this;
    }

    public Builder withSkipInsertToMule(final Boolean skipInsertToMule) {
      this.skipInsertToMule = skipInsertToMule;
      return this;
    }

    public Builder withIncomeFrequency(final String incomeFrequency) {
      this.incomeFrequency = incomeFrequency;
      return this;
    }

    public RegistrationParams build() {
      return new RegistrationParams(this);
    }

    public int getPrincipal() {
      return principal;
    }

    public int getMaturiyPeriods() {
      return maturiyPeriods;
    }

    public Builder withCommunicationLanguage(final String communicationLanguage) {
      this.communicationLanguage = communicationLanguage;
      return this;
    }
  }
}
