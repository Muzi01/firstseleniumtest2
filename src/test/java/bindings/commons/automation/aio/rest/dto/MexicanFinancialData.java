package bindings.commons.automation.aio.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MexicanFinancialData implements FinancialData {

  private static final int CONSTANT = 200;
  private static final int REVOLVING_ACCOUNTS_90D = 5;
  private static final int WIDTH_SCREEN = 6;
  private static final int AMOUNT_OF_MORTGAGE = -2;
  private static final int OVERDUE_BALANCE = -3;
  private static final int NO_OF_CLOSED_ACCOUNTS = 2;
  private static final int NO_OF_REVOLVING_ACCOUNTS = -10;
  private static final int MF_SCORE = 2;
  private static final int DEFAULT_RATIO = 3;
  private static final int INQUIRIES_14D = 7;
  private static final int BDS_SCORE_MAX = 20;
  private static final int TRIGRAPH_TIME = 12;
  public static final int FIXED_SCORE =
      CONSTANT + REVOLVING_ACCOUNTS_90D + WIDTH_SCREEN + AMOUNT_OF_MORTGAGE
          + OVERDUE_BALANCE + NO_OF_CLOSED_ACCOUNTS + NO_OF_REVOLVING_ACCOUNTS + MF_SCORE
          + DEFAULT_RATIO
          + INQUIRIES_14D + BDS_SCORE_MAX + TRIGRAPH_TIME;

  public Boolean getMarketingPermissionCreditBureaus() {
    return this.marketingPermissionCreditBureaus;
  }

  public void setMarketingPermissionCreditBureaus(final Boolean marketingPermissionCreditBureaus) {
    this.marketingPermissionCreditBureaus = marketingPermissionCreditBureaus;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  /**
   * based on AIO
   */
  public enum Loan {
    HOME_LOAN,
    CREDIT_CARD
  }

  public enum Occupation {
    FULL_TIME,
    FIXED_TERM,
    ENTREPRENEUR,
    RETIRED,
    STUDENT,
    UNEMPLOYED,
    HOUSE_WIFE,
    OTHER
  }

  public enum MaritalStatus {
    SINGLE,
    MARRIED,
    DIVORCED,
    WIDOWED
  }


  public enum Education {
    PRIMARY(-12),
    SECONDARY(-12),
    UNIVERSITY(8),
    POST_GRADUATE(11),
    POLYTECHNIC(3),
    OTHER_MISSING(0);

    protected Integer scoreValue;

    Education(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum LearnedAboutUsFromType {
    GOOGLE_AD,
    FACEBOOK_AD,
    FRIEND_RECOMMENDATION,
    EMAIL_AD,
    TV_AD,
    AD_IN_BUS,
    BUS_STOP_AD,
    FOOTBALL_STADIUM_AD,
    RADIO,
    OFFLINE_ADVERTISMENT,
    OTHER
  }

  public enum IncomeFrequency {
    DAILY,
    WEEKLY,
    BI_WEEKLY,
    MONTHLY
  }

  public enum Residence {
    RENT,
    OWN,
    WITH_PARENT,
    OTHER
  }

  public enum Age {
    AGE_RANGE1(22, 36, -4),
    AGE_RANGE2(37, 53, 5),
    AGE_RANGE3(54, 80, 20);

    protected Integer min;
    protected Integer max;
    protected Integer scoreValue;

    Age(final Integer min, final Integer max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }
  }

  public enum MunicipalityAnnualIncome {
    C1(-5),
    C2(0),
    C3(1),
    C4(10);

    protected Integer scoreValue;

    MunicipalityAnnualIncome(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum ProvinceGDP {
    D1(-1),
    D2(0),
    D3(4);

    protected Integer scoreValue;

    ProvinceGDP(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  private Boolean creditBureauQuestion1Answer;
  private Boolean marketingPermissionCreditBureaus;
  private Loan[] loans;
  private Integer netIncome;
  private Occupation occupation;
  private String householdChildren;
  private MaritalStatus maritalStatus;
  private Education education;
  private LoanPurpose loanPurpose;
  private Residence residence;
  private LearnedAboutUsFromType learnedAboutUsFrom;
  private IncomeFrequency incomeFrequency;
  private String company;

  public MexicanFinancialData() {
    // empty for chain-building purposes
  }

  public MexicanFinancialData withCreditBureauQuestion1Answer(
      final Boolean creditBureauQuestion1Answer) {
    this.creditBureauQuestion1Answer = creditBureauQuestion1Answer;
    return this;
  }

  public MexicanFinancialData withMarketingPermissionCreditBureaus(
      final Boolean marketingPermissionCreditBureaus) {
    this.setMarketingPermissionCreditBureaus(marketingPermissionCreditBureaus);
    return this;
  }

  public MexicanFinancialData withCompany(final String company) {
    this.setCompany(company);
    return this;
  }

  public MexicanFinancialData withLoans(final Loan[] loans) {
    this.loans = loans;
    return this;
  }

  public MexicanFinancialData withNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
    return this;
  }

  public MexicanFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public MexicanFinancialData withHouseholdChildren(final String householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public MexicanFinancialData withMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public MexicanFinancialData withEducation(final Education education) {
    this.education = education;
    return this;
  }

  public MexicanFinancialData withLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
    return this;
  }

  public MexicanFinancialData withResidence(final Residence residence) {
    this.residence = residence;
    return this;
  }

  public MexicanFinancialData withLearnedAboutUsFrom(
      final LearnedAboutUsFromType learnedAboutUsFrom) {
    this.learnedAboutUsFrom = learnedAboutUsFrom;
    return this;
  }

  public MexicanFinancialData withIncomeFrequency(final IncomeFrequency incomeFrequency) {
    this.incomeFrequency = incomeFrequency;
    return this;
  }

  public Boolean getCreditBureauQuestion1Answer() {
    return this.creditBureauQuestion1Answer;
  }

  public void setCreditBureauQuestion1Answer(final Boolean creditBureauQuestion1Answer) {
    this.creditBureauQuestion1Answer = creditBureauQuestion1Answer;
  }

  public Loan[] getLoans() {
    return this.loans;
  }

  public void setLoans(final Loan[] loans) {
    this.loans = loans;
  }

  public Integer getNetIncome() {
    return this.netIncome;
  }

  public void setNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
  }

  public Occupation getOccupation() {
    return this.occupation;
  }

  public void setOccupation(final Occupation occupation) {
    this.occupation = occupation;
  }

  public String getHouseholdChildren() {
    return this.householdChildren;
  }

  public void setHouseholdChildren(final String householdChildren) {
    this.householdChildren = householdChildren;
  }

  public MaritalStatus getMaritalStatus() {
    return this.maritalStatus;
  }

  public void setMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public Education getEducation() {
    return this.education;
  }

  public void setEducation(final Education education) {
    this.education = education;
  }

  public LoanPurpose getLoanPurpose() {
    return this.loanPurpose;
  }

  public void setLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
  }

  public Residence getResidence() {
    return this.residence;
  }

  public void setResidence(final Residence residence) {
    this.residence = residence;
  }

  public LearnedAboutUsFromType getLearnedAboutUsFrom() {
    return this.learnedAboutUsFrom;
  }

  public void setLearnedAboutUsFrom(final LearnedAboutUsFromType learnedAboutUsFrom) {
    this.learnedAboutUsFrom = learnedAboutUsFrom;
  }

  public IncomeFrequency getIncomeFrequency() {
    return this.incomeFrequency;
  }

  public void setIncomeFrequency(final IncomeFrequency incomeFrequency) {
    this.incomeFrequency = incomeFrequency;
  }

  public static Integer getEducationScoreValue(final String education) {

    return Education.valueOf(education).scoreValue;
  }

  public static Integer getAgeScoreValue(final Integer age) {
    final Age[] ranges = Age.values();
    for (final Age range : ranges) {
      if (age <= range.max && age >= range.min) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for age value = " + age);
  }

  public static Integer getProvinceGDPScoreValue(final String province) {

    return ProvinceGDP.valueOf(ProvinceMX.getProvinceGDPforProvince(province)).scoreValue;
  }

  public static Integer getMunicipalityAnnualIncomeScoreValue(final String municipalityName,
      final String province) {
    final MunicipalityDTO municipality = new MunicipalityDTO();
    municipality.name = municipalityName;
    municipality.province = province;
    return MunicipalityAnnualIncome.valueOf(MunicipalityMX
        .getCategoryOfMunicipalityAnnualIncome(municipality)).scoreValue;
  }

  // Scoring Map Values//
  private static Map<Integer, String> getScoreMapForAgeAndEducation() {
    final Map<Integer, String> out = new HashMap<>();
    for (final Age age : Age.values()) {
      for (final Education education : Education.values()) {
        final Integer ageValue = age.min + 1;
        out.put(age.scoreValue + education.scoreValue, ageValue + "," + education);
      }
    }
    return out;
  }

  private static Map<Integer, String> getScoreMapForProvinceAndMunicipality() {
    final Map<Integer, String> out = new HashMap<>();
    for (final MunicipalityAnnualIncome municipalityAnnualIncome : MunicipalityAnnualIncome
        .values()) {
      for (final ProvinceGDP provinceGDP : ProvinceGDP.values()) {
        final List<String> provinces = ProvinceMX.getProvinces(provinceGDP.name().toLowerCase());
        for (final String province : provinces) {
          final String municipality = MunicipalityMX
              .getMunicipality(municipalityAnnualIncome.name().toLowerCase(), province);
          if (municipality != null) {
            out.put(municipalityAnnualIncome.scoreValue + provinceGDP.scoreValue,
                province + "," + municipality);
          }
        }
      }
    }
    return out;
  }

  public static List<Map.Entry<Integer, String>> getScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryAE : getScoreMapForAgeAndEducation().entrySet()) {
      for (final Map.Entry<Integer, String> entryPM : getScoreMapForProvinceAndMunicipality()
          .entrySet()) {
        final Integer scoreValue = FIXED_SCORE + entryAE.getKey() + entryPM.getKey();
        scoreMap.put(scoreValue, entryAE.getValue() + "," + entryPM.getValue());
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for MX is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }
}
