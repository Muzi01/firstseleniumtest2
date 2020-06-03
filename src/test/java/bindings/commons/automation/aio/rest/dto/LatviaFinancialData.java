package bindings.commons.automation.aio.rest.dto;

import org.apache.commons.lang3.EnumUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LatviaFinancialData implements FinancialData {

  private static final int CONSTANT = 200;
  private static final int MAX_LATEERALY_2V = -3;
  private static final int NR_REJECTED_APP_12M = 5;
  private static final int TIME_SINCE_PREV_LOAN = -5;
  private static final int CB_MAX_DEBTS_12M = 6;
  private static final int CI_EAI_M = 7;
  private static final int CI_TIME_W = 13;
  public static final int FIXED_SCORE =
      CONSTANT + MAX_LATEERALY_2V + NR_REJECTED_APP_12M + TIME_SINCE_PREV_LOAN
          + CB_MAX_DEBTS_12M + CI_EAI_M + CI_TIME_W;

  public enum Education {
    UNIVERSITY,
    POLYTECHNIC,
    HIGH_SECONDARY,
    VOCATIONAL,
    PRIMARY
  }

  public enum EmploymentDuration {
    MONTHS_1_4,
    MONTHS_5_12,
    YEARS_1_2,
    YEARS_3_5,
    YEARS_6_10,
    YEARS_OVER_10
  }

  public enum MaritalStatus {
    SINGLE,
    MARRIED,
    COHABITATING,
    DIVORCED,
    WIDOWED
  }

  public enum OccupationType {
    EMPLOYEE,
    MANAGER,
    EXECUTIVE,
    SPECIALIST,
    OFFICE_CLERK,
    OTHER
  }

  public enum Occupation {
    ENTREPRENEUR,
    FULL_TIME,
    PART_TIME,
    FIXED_TERM,
    STUDENT,
    RETIRED,
    DISABILITY_RETIRED,
    UNEMPLOYED,
    OTHER
  }

  public enum Loan {
    LEASING_HIRE_PURCHASE,
    FASTLOAN,
    CONSUMER_LOAN,
    HOME_LOAN,
    STUDENT_LOAN,
    OVERDRAFT,
    CREDIT_CARD,
    GUARANTEE_SURETY,
    OTHER
  }

  public enum LoanPurpose {
    FINANCING_BIG_PURCHASE,
    DAILY_EXPENDITURE,
    EDUCATION_TRAINING,
    TRAVELING_VACATION,
    FINANCING_OTHER_LOANS,
    HOME_REPAIR_CONSTRUCTION,
    CAR_REPAIR_OR_PURCHASE,
    MEDICAL_EXPENCES,
    OTHER
  }

  public enum Residence {
    RENT,
    OWN,
    OTHER
  }

  public enum AgeScore {
    AGE_RANGE1(18, 18, -22),
    AGE_RANGE2(19, 22, -8),
    AGE_RANGE3(23, 25, 2),
    AGE_RANGE4(26, 33, 5),
    AGE_RANGE5(34, 41, 12),
    AGE_RANGE6(42, 60, 17),
    AGE_RANGE7(61, 99, 15);

    protected Integer ageMin;
    protected Integer ageMax;
    protected Integer scoreValue;

    AgeScore(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
      this.ageMin = ageMin;
      this.ageMax = ageMax;
      this.scoreValue = scoreValue;
    }
  }

  public enum EmailExtension {
    LV(3),
    COM(8),
    RU(-11);

    protected Integer scoreValue;

    EmailExtension(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  private Education education;
  private EmploymentDuration employmentDuration;
  private MaritalStatus maritalStatus;
  private Integer livingCosts;
  private Integer netIncome;
  private String company;
  private Occupation occupation;
  private OccupationType occupationType;
  private Residence residence;
  private Integer householdChildren;
  private Integer totalMonthlyObligations;
  private Loan[] loans;
  private LoanPurpose loanPurpose;
  private String channel;
  private static final Integer EMAIL_EXTENSION_DEFAULT_SCORE = -17;

  public LatviaFinancialData withEducation(final Education education) {
    this.education = education;
    return this;
  }

  public LatviaFinancialData withEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
    return this;
  }

  public LatviaFinancialData withMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public LatviaFinancialData withLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
    return this;
  }

  public LatviaFinancialData withNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
    return this;
  }

  public LatviaFinancialData withCompany(final String company) {
    this.company = company;
    return this;
  }

  public LatviaFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public LatviaFinancialData withOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
    return this;
  }

  public LatviaFinancialData withResidence(final Residence residence) {
    this.residence = residence;
    return this;
  }

  public LatviaFinancialData withHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public LatviaFinancialData withTotalMonthlyObligations(final Integer totalMonthlyObligations) {
    this.totalMonthlyObligations = totalMonthlyObligations;
    return this;
  }

  public LatviaFinancialData withLoans(final Loan[] loans) {
    this.loans = loans;
    return this;
  }

  public LatviaFinancialData withLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
    return this;
  }

  public LatviaFinancialData withChannel(final String channel) {
    this.channel = channel;
    return this;
  }

  public Education getEducation() {
    return this.education;
  }

  public void setEducation(final Education education) {
    this.education = education;
  }

  public EmploymentDuration getEmploymentDuration() {
    return this.employmentDuration;
  }

  public void setEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
  }

  public MaritalStatus getMaritalStatus() {
    return this.maritalStatus;
  }

  public void setMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public Integer getLivingCosts() {
    return this.livingCosts;
  }

  public void setLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
  }

  public Integer getNetIncome() {
    return this.netIncome;
  }

  public void setNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public Occupation getOccupation() {
    return this.occupation;
  }

  public void setOccupation(final Occupation occupation) {
    this.occupation = occupation;
  }

  public OccupationType getOccupationType() {
    return this.occupationType;
  }

  public void setOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
  }

  public Residence getResidence() {
    return this.residence;
  }

  public void setResidence(final Residence residence) {
    this.residence = residence;
  }

  public Integer getHouseholdChildren() {
    return this.householdChildren;
  }

  public void setHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
  }

  public Integer getTotalMonthlyObligations() {
    return this.totalMonthlyObligations;
  }

  public void setTotalMonthlyObligations(final Integer totalMonthlyObligations) {
    this.totalMonthlyObligations = totalMonthlyObligations;
  }

  public Loan[] getLoans() {
    return this.loans;
  }

  public void setLoans(final Loan[] loans) {
    this.loans = loans;
  }

  public LoanPurpose getLoanPurpose() {
    return this.loanPurpose;
  }

  public void setLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
  }

  public String getChannel() {
    return this.channel;
  }

  public void setChannel(final String channel) {
    this.channel = channel;
  }

  public static Integer getAgeScoreValue(final Integer age) {
    final AgeScore[] ranges = AgeScore.values();
    for (final AgeScore range : ranges) {
      if (age <= range.ageMax && age >= range.ageMin) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for age value = " + age);
  }

  public static Integer getEmailExtensionScoreValue(final String emailExtension) {

    return (EnumUtils.isValidEnum(EmailExtension.class, emailExtension))
        ? EmailExtension.valueOf(emailExtension).scoreValue
        : EMAIL_EXTENSION_DEFAULT_SCORE;
  }

  // Scoring Map Values//
  public static List<Map.Entry<Integer, String>> getScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final AgeScore age : AgeScore.values()) {
      for (final EmailExtension emailExtension : EmailExtension.values()) {
        final Integer ageValue = age.ageMin + 1;
        scoreMap.put(FIXED_SCORE + age.scoreValue + emailExtension.scoreValue,
            ageValue + "," + emailExtension);
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for LV is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }
}
