package bindings.commons.automation.generator.portals;

import com.ipfdigital.automation.customer.Country;

import java.util.List;

public class Scoring {

  private static final String NOT_IMPLEMENTED = "Not Implemented for selected country";

  public void setScore(final Country country, final ScoringFactor scoringFactor,
      final String factorValue) {
    switch (country) {
      case ES:
        setScoreES(scoringFactor, factorValue);
        break;
      case FI:
        setScoreFI(scoringFactor, factorValue);
        break;
      case EE:
        setScoreEE(scoringFactor, factorValue);
        break;
      case LV:
        setScoreLV(scoringFactor, factorValue);
        break;
      case LT:
        setScoreLT(scoringFactor, factorValue);
        break;
      case PL:
        setScorePL(scoringFactor, factorValue);
        break;
      case MX:
        setScoreMX(scoringFactor, factorValue);
        break;
      default:
        setScoreCommon(country, scoringFactor, factorValue);
    }
  }

  private void setScoreCommon(final Country country, final ScoringFactor scoringFactor,
      final String factorValue) {
    switch (scoringFactor) {
      case AGE:
        setAge(factorValue);
        break;
      case SALARY:
        setSalary(factorValue);
        break;
      case OCCUPATION:
        setOccupation(factorValue);
        break;
      case EDUCATION:
        setEducation(factorValue);
        break;
      case MARITAL_STATUS:
        setMaritalStatus(factorValue);
        break;
      default:
        throw new IllegalStateException(
            String.format("Parameter %s not defined for country %s", scoringFactor, country));
    }
  }

  private void setScoreES(final ScoringFactor scoringFactor, final String factorValue) {
    switch (scoringFactor) {
      case NUM_INDICATOR:
        setNumIndicator(factorValue);
        break;
      case RISK_MICRO_SCORE:
        setRiskMicroScore(factorValue);
        break;
      case MOBILE:
        setMobile(factorValue);
        break;
      case TIME_FROM_PORTABILITY:
        setTimeFromPortability(factorValue);
        break;
      case PROBABILITY_OF_DEFAULT:
        setProbabilityDefault(factorValue);
        break;
      case SAVINGS_CAPABILITY:
        setSavingsCapability(factorValue);
        break;
      case COMMUNITY_INCOME:
        setPostCode(factorValue);
        break;
      default:
        setScoreCommon(Country.ES, scoringFactor, factorValue);
    }
  }

  private void setScoreFI(final ScoringFactor scoringFactor, final String factorValue) {
    switch (scoringFactor) {
      case OCCUPATION_TYPE:
        setOccupationType(factorValue);
        break;
      case RESIDENCE:
        setResidence(factorValue);
        break;
      case LIVING_COSTS:
        setLivingCosts(factorValue);
        break;
      case HOUSING_COSTS:
        setHousingCosts(factorValue);
        break;
      case OTHER_LOANS:
        setOtherLoans(factorValue);
        break;
      case HOUSEHOLD_CHILDREN:
        setHouseholdChildren(factorValue);
        break;
      case EMPLOYMENT_DURATION:
        setEmploymentDuration(factorValue);
        break;
      case LOANS:
        setLoan(factorValue);
        break;
      default:
        setScoreCommon(Country.FI, scoringFactor, factorValue);
    }
  }

  private void setScoreEE(final ScoringFactor scoringFactor, final String factorValue) {
    switch (scoringFactor) {
      case RESIDENCE:
        setResidence(factorValue);
        break;
      case DEBT:
        setDebt(factorValue);
        break;
      case LOANS:
        setLoan(factorValue);
        break;
      case LIVING_COSTS:
        setLivingCosts(factorValue);
        break;
      case WEEKDAY_HOUR_OF_APP:
        setApplicationWeekdayHour(factorValue);
        break;
      default:
        setScoreCommon(Country.EE, scoringFactor, factorValue);
    }
  }

  private void setScoreLV(final ScoringFactor scoringFactor, final String factorValue) {
    if (scoringFactor.equals(ScoringFactor.EMAIL_EXTENSION)) {
      setEmailExtension(factorValue);
    } else {
      setScoreCommon(Country.LV, scoringFactor, factorValue);
    }
  }

  private void setScorePL(final ScoringFactor scoringFactor, final String factorValue) {
    switch (scoringFactor) {
      case BANK_ACCOUNT_DURATION:
        setBankAccountDuration(factorValue);
        break;
      case EMPLOYMENT_DURATION:
        setEmploymentDuration(factorValue);
        break;
      case DELTA_VISTA:
        setDeltaVista(factorValue);
        break;
      default:
        setScoreCommon(Country.PL, scoringFactor, factorValue);
    }
  }

  private void setScoreLT(final ScoringFactor scoringFactor, final String factorValue) {
    if (scoringFactor.equals(ScoringFactor.RESIDENCE)) {
      setResidence(factorValue);
    } else {
      setScoreCommon(Country.LT, scoringFactor, factorValue);
    }
  }

  private void setScoreMX(final ScoringFactor scoringFactor, final String factorValue) {
    switch (scoringFactor) {
      case MUNICIPALITY:
        setMunicipality(factorValue);
        break;
      case PROVINCE:
        setProvince(factorValue);
        break;
      default:
        setScoreCommon(Country.MX, scoringFactor, factorValue);
    }
  }

  public Integer getCustomerTotalScore() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getCustomerTotalScore(final boolean parameter) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setCustomerWithScore(final Integer expectedCustomerScore) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setCustomerWithScore(final Integer expectedCustomerScore, final boolean parameter) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setCustomerWithScore(
      final Integer expectedCustomerScore, final String email, final String msisdn) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setResidence(final String residence) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getResidence() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setEducation(final String education) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getEducation() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setSalary(final String salary) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getSalary() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setOccupation(final String occupation) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getOccupation() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setOccupationType(final String occupationType) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getOccupationType() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setLivingCosts(final String livingCosts) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getLivingCosts() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setHousingCosts(final String housingCosts) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getHousingCosts() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setOtherLoans(final String otherLoans) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getOtherLoans() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setMaritalStatus(final String maritalStatus) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getMaritalStatus() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setHouseholdChildren(final String householdChildren) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getHouseholdChildrens() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setEmploymentDuration(final String employmentDuration) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getEmploymentDuration() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setAge(final String age) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getAge() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setLoan(final String loan) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public List<String> getLoan() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setMobile(final String mobile) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getMobile() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setTimeFromPortability(final String timeFromPortability) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Double getTimeFromPortability() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setProbabilityDefault(final String probabilityDefault) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Double getProbabilityDefault() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setSavingsCapability(final String savingsCapability) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getSavingsCapability() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setPostCode(final String postCode) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getPostCode() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setMaturityPeriods(final String aturityPeriods) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getMaturityPeriods() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setPrincipal(final String principal) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getPrincipal() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setBankAccountDuration(final String principal) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getBankAccountDuration() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setDebt(final String debt) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getDebt() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setOtherDebts(final String debt) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getOtherDebts() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setNumIndicator(final String numIndicator) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getNumIndicator() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setRiskMicroScore(final String riskMicroScore) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getRiskMicroScore() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setEmailExtension(final String emailExtension) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getEmailExtension() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public Integer getEmailExtensionScoreValue(final String emailExtension) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getMunicipality() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setMunicipality(final String municipality) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getProvince() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setProvince(final String province) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getApplicationWeekdayHour() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setApplicationWeekdayHour(final String applicationWeekdayHour) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public String getDeltaVista() {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

  public void setDeltaVista(final String deltaVistaValue) {
    throw new IllegalStateException(NOT_IMPLEMENTED);
  }

}
