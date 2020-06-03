package bindings.commons.automation.aio.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EstonianFinancialData implements FinancialData {

  private static final int CONSTANT = 200;
  private static final int NR_REJECTED_APPS = 13;
  private static final int PREV_LATE_EARLY = 1;
  private static final int KIN_MSLAST_CLOSED = 4;
  public static final int FIXED_SCORE =
      CONSTANT + NR_REJECTED_APPS + PREV_LATE_EARLY + KIN_MSLAST_CLOSED;

  public enum MaritalStatus {
    SINGLE,
    MARRIED,
    DIVORCED,
    COHABITATING,
    WIDOWED
  }

  public enum Education {
    UNIVERSITY(6),
    POLYTECHNIC(10),
    VOCATIONAL(3),
    SECONDARY(3),
    MIDDLE(-3),
    PRIMARY(-3);

    protected Integer value;

    Education(final Integer value) {
      this.value = value;
    }
  }

  public enum OccupationType {
    WORKER,
    SPECIALIST_OFFICE_WORKER,
    SENIOR_SPECIALIST,
    MIDDLE_MANAGER,
    TOP_MANAGER,
    OWNER_OF_COMPANY_SHAREHOLDER,
    WORKING_STUDENT,
  }

  public enum Occupation {
    FULL_TIME,
    FIXED_TERM,
    PART_TIME,
    ENTREPRENEUR,
    RETIRED,
    DISABILITY_RETIRED,
    STUDENT,
    UNEMPLOYED,
    OTHER
  }

  public enum EmploymentDuration {
    MISSING(2),
    MONTHS_1_4(-3),
    MONTHS_5_12(-3),
    YEARS_1_2(-3),
    YEARS_3_5(0),
    YEARS_6_10(4),
    YEARS_OVER_10(5);

    protected Integer value;

    EmploymentDuration(final Integer value) {
      this.value = value;
    }
  }

  public enum Loan {
    LEASING_HIRE_PURCHASE,
    FASTLOAN,
    CONSUMER_LOAN,
    CAR_LEASING,
    HOME_LOAN,
    STUDENT_LOAN,
    OVERDRAFT,
    CREDIT_CARD,
    GUARANTEE_SURETY,
    OTHER
  }

  public enum Residence {
    OWNER(8),
    RENT(0),
    WITH_PARENTS(1),
    DORM(1),
    OTHER(6);

    protected Integer value;

    Residence(final Integer value) {
      this.value = value;
    }
  }

  public enum DebtTypeMonthly {
    HOME_LOAN(19),
    LEASING_HIRE_PURCHASE(10),
    FASTLOAN(10),
    CONSUMER_LOAN(10),
    CAR_LEASING(10),
    STUDENT_LOAN(10),
    OVERDRAFT(10),
    CREDIT_CARD(10),
    GUARANTEE_SURETY(10),
    OTHER(10);

    protected Integer value;

    DebtTypeMonthly(final Integer value) {
      this.value = value;
    }
  }

  public enum AgeScore {
    AGE_RANGE1(18, 18, -5),
    AGE_RANGE2(19, 19, 0),
    AGE_RANGE3(20, 24, 2),
    AGE_RANGE4(25, 28, 7),
    AGE_RANGE5(29, 33, 10),
    AGE_RANGE6(44, 80, 12),
    AGE_RANGE7(40, 46, 16),
    AGE_RANGE8(47, 60, 23),
    AGE_RANGE9(61, 100, 32);

    protected Integer ageMin;
    protected Integer ageMax;
    protected Integer scoreValue;

    AgeScore(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
      this.ageMin = ageMin;
      this.ageMax = ageMax;
      this.scoreValue = scoreValue;
    }
  }

  public enum WeekdayHourOfApplication {
    WORKING("09", -2),
    AFTERNOON("14", 3),
    EVENING("20", 14);

    protected String hour;
    protected int scoreValue;

    WeekdayHourOfApplication(final String hour, final int scoreValue) {
      this.hour = hour;
      this.scoreValue = scoreValue;
    }

    public String getHour() {
      return this.hour;
    }
  }

  private MaritalStatus maritalStatus;
  private Education education;
  private Occupation occupation;
  private OccupationType occupationType;
  private EmploymentDuration employmentDuration;
  private String company;
  private Integer netIncome;
  private Integer livingCosts;
  private List<Loan> debtTypesExperienced;
  private Integer householdChildren;
  private Residence residence;
  private DebtTypeMonthly debtTypeMonthly;
  private LoanDTO[] otherDebts;

  public EstonianFinancialData withMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public EstonianFinancialData withEducation(final Education education) {
    this.education = education;
    return this;
  }

  public EstonianFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public EstonianFinancialData withOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
    return this;
  }

  public EstonianFinancialData withEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
    return this;
  }

  public EstonianFinancialData withCompany(final String company) {
    this.company = company;
    return this;
  }

  public EstonianFinancialData withNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
    return this;
  }

  public EstonianFinancialData withLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
    return this;
  }

  public EstonianFinancialData withDebtTypesExperienced(final List<Loan> debtTypesExperienced) {
    this.debtTypesExperienced = debtTypesExperienced;
    return this;
  }

  public EstonianFinancialData withResidence(final Residence residence) {
    this.residence = residence;
    return this;
  }

  public EstonianFinancialData withHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public EstonianFinancialData withOtherDebts(final LoanDTO[] otherDebts) {
    this.otherDebts = otherDebts;
    return this;
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

  public EmploymentDuration getEmploymentDuration() {
    return this.employmentDuration;
  }

  public void setEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public Integer getNetIncome() {
    return this.netIncome;
  }

  public void setNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
  }

  public Integer getLivingCosts() {
    return this.livingCosts;
  }

  public void setLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
  }

  public List<Loan> getDebtTypesExperienced() {
    return this.debtTypesExperienced;
  }

  public void setDebtTypesExperienced(final List<Loan> debtTypesExperienced) {
    this.debtTypesExperienced = debtTypesExperienced;
  }

  public Integer getHouseholdChildren() {
    return this.householdChildren;
  }

  public void setHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
  }

  public Residence getResidence() {
    return this.residence;
  }

  public void setResidence(final Residence residence) {
    this.residence = residence;
  }

  public void setDebtTypeMonthly(final DebtTypeMonthly debtTypeMonthly) {
    this.debtTypeMonthly = debtTypeMonthly;
  }

  public DebtTypeMonthly getDebtTypeMonthly() {
    return this.debtTypeMonthly;
  }

  public LoanDTO[] getOtherDebts() {
    return this.otherDebts;
  }

  public void setOtherDebts(final LoanDTO[] otherDebts) {
    this.otherDebts = otherDebts;
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

  public static Integer getEducationScoreValue(final String education) {

    return Education.valueOf(education).value;
  }

  public static Integer getResidenceScoreValue(final String residence) {

    return Residence.valueOf(residence).value;
  }

  public static Integer getWeekdayHourScoreValue(final String weekdayHour) {

    return WeekdayHourOfApplication.valueOf(weekdayHour).scoreValue;
  }

  public static Integer getDebtScoreValue(final String debt) {

    return DebtTypeMonthly.valueOf(debt).value;
  }

  // Scoring Map Values//
  private static Map<Integer, String> getScoreMapForAgeAndDebt() {
    final Map<Integer, String> ageAndDebtMap = new HashMap<>();
    for (final AgeScore ageItem : AgeScore.values()) {
      for (final DebtTypeMonthly debtTypeMonthly : DebtTypeMonthly.values()) {
        final Integer ageValue = ageItem.ageMin + 1;
        ageAndDebtMap.put(ageItem.scoreValue + debtTypeMonthly.value,
            ageValue + "," + debtTypeMonthly);
      }
    }
    return ageAndDebtMap;
  }

  private static Map<Integer, String> getScoreMapForEduAndResidenceAndWeekdayHour() {
    final Map<Integer, String> eduAndResidenceAndWeekdayMap = new HashMap<>();
    for (final Education education : Education.values()) {
      for (final Residence residence : Residence.values()) {
        for (final WeekdayHourOfApplication weekday : WeekdayHourOfApplication.values()) {
          eduAndResidenceAndWeekdayMap.put(education.value + residence.value + weekday.scoreValue,
              education + "," + residence + "," + weekday.name());
        }
      }
    }
    return eduAndResidenceAndWeekdayMap;
  }

  public static List<Map.Entry<Integer, String>> getScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryAD : getScoreMapForAgeAndDebt().entrySet()) {
      for (final Map.Entry<Integer, String> entryERW : getScoreMapForEduAndResidenceAndWeekdayHour()
          .entrySet()) {
        final Integer scoreValue = FIXED_SCORE + entryAD.getKey() + entryERW.getKey();
        scoreMap.put(scoreValue, entryAD.getValue() + "," + entryERW.getValue());
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for EE is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }
}
