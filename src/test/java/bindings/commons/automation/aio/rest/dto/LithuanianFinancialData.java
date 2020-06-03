package bindings.commons.automation.aio.rest.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LithuanianFinancialData implements FinancialData {

  private static final int CONSTANT = 200;
  private static final int NUM_OF_UNPAID_INSTALLMENT = 6;
  private static final int TERM_CONTRACTS = 21;
  private static final int CI_DEBT = 7;
  private static final int CI_EAI = 7;
  private static final int CI_TIME = 7;
  private static final int CIP_RISK_GRADE = 7;
  private static final int SALARY_SODRA = 9;
  private static final int SALARY_DIFF = 16;
  private static final int IP = 8;
  public static final int FIXED_SCORE =
      CONSTANT + NUM_OF_UNPAID_INSTALLMENT + TERM_CONTRACTS + CI_DEBT + CI_EAI + CI_TIME
          + CIP_RISK_GRADE + SALARY_SODRA + SALARY_DIFF + IP;

  public enum Education {
    MIDDLE(-4),
    PRELIMINARY(-4),
    HIGH(3),
    VOCATIONAL(5),
    UNIVERSITY(14),
    POLYTECHNIC(14),
    MISSING(3);

    protected Integer scoreValue;

    Education(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum EmploymentDuration {
    FROM_0_TO_3_MONTHS,
    MONTHS_1_4,
    FROM_4_TO_12_MONTHS,
    MONTHS_5_12,
    FROM_13_AND_MORE_MONTHS,
    YEARS_1_2,
    YEARS_3_5,
    YEARS_6_10,
    YEARS_OVER_10,
  }

  public enum MaritalStatus {
    SINGLE(1),
    MARRIED(12),
    DIVORCED(6),
    WIDOWED(6);

    protected Integer scoreValue;

    MaritalStatus(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum OccupationType {
    EMPLOYEE,
    MANAGER,
    EXECUTIVE,
    GOVERNMENT_OFFICIAL,
    SPECIALIST,
    OFFICE_CLERK,
    OTHER
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
    OTHER,
    GOVERNMENT
  }

  public enum Residence {
    WITHPARENTS(2),
    DORM(2),
    RENT(4),
    OTHER(4),
    OWNERHOUSE(5),
    OWNERAPARTMENT(6);

    protected Integer scoreValue;

    Residence(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum AgeScore {
    AGE_RANGE1(18, 18, -6),
    AGE_RANGE2(19, 21, -1),
    AGE_RANGE3(22, 23, 3),
    AGE_RANGE4(24, 54, 7),
    AGE_RANGE6(55, 80, 10);

    protected Integer ageMin;
    protected Integer ageMax;
    protected Integer scoreValue;

    AgeScore(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
      this.ageMin = ageMin;
      this.ageMax = ageMax;
      this.scoreValue = scoreValue;
    }
  }

  private Education education;
  private EmploymentDuration employmentDuration;
  private MaritalStatus maritalStatus;
  private Integer netIncome;
  private Integer monthlyObligations;
  private Boolean receiveRefinance;
  private Occupation occupation;
  private OccupationType occupationType;
  private Residence residence;
  private Integer householdChildren;
  private String channel;

  public LithuanianFinancialData withEducation(final Education education) {
    this.education = education;
    return this;
  }

  public LithuanianFinancialData withEmploymentDuration(
      final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
    return this;
  }

  public LithuanianFinancialData withMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public LithuanianFinancialData withNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
    return this;
  }

  public LithuanianFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public LithuanianFinancialData withOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
    return this;
  }

  public LithuanianFinancialData withResidence(final Residence residence) {
    this.residence = residence;
    return this;
  }

  public LithuanianFinancialData withHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public LithuanianFinancialData withChannel(final String channel) {
    this.channel = channel;
    return this;
  }

  public LithuanianFinancialData withMonthlyObligations(final Integer monthlyObligations) {
    this.monthlyObligations = monthlyObligations;
    return this;
  }

  public LithuanianFinancialData withReceiveRefinance() {
    this.receiveRefinance = true;
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

  public String getChannel() {
    return this.channel;
  }

  public void setChannel(final String channel) {
    this.channel = channel;
  }

  public Integer getMonthlyObligations() {
    return this.monthlyObligations;
  }

  public void setMonthlyObligations(final Integer monthlyObligations) {
    this.monthlyObligations = monthlyObligations;
  }

  public Boolean getReceiveRefinance() {
    return this.receiveRefinance;
  }

  public void setReceiveRefinance(final Boolean receiveRefinance) {
    this.receiveRefinance = receiveRefinance;
  }

  public static Integer getResidenceScoreValue(final String residence) {

    return Residence.valueOf(residence).scoreValue;
  }

  public static Integer getEducationScoreValue(final String education) {

    return Education.valueOf(education).scoreValue;
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

  public static Integer getMaritalStatusValue(final String maritalStatus) {

    return MaritalStatus.valueOf(maritalStatus).scoreValue;
  }

  // Scoring Map Values//
  private static Map<Integer, String> getScoreMapForAgeAndEducation() {
    final Map<Integer, String> ageAndMaritalMap = new HashMap<>();
    for (final AgeScore age : AgeScore.values()) {
      if (!age.equals(AgeScore.AGE_RANGE1)) {
        for (final Education education : Education.values()) {
          ageAndMaritalMap.put(age.scoreValue + education.scoreValue, age.ageMin + "," + education);
        }
      }
    }
    return ageAndMaritalMap;
  }

  private static Map<Integer, String> getScoreMapForResidenceAndMaritalStatus() {
    final Map<Integer, String> bankAndEmploymentMap = new HashMap<>();
    for (final Residence residence : Residence.values()) {
      for (final MaritalStatus maritalStatus : MaritalStatus.values()) {
        bankAndEmploymentMap.put(residence.scoreValue + maritalStatus.scoreValue,
            residence + "," + maritalStatus);
      }
    }
    return bankAndEmploymentMap;
  }

  public static List<Map.Entry<Integer, String>> getScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryAE : getScoreMapForAgeAndEducation().entrySet()) {
      for (final Map.Entry<Integer, String> entryRM : getScoreMapForResidenceAndMaritalStatus()
          .entrySet()) {
        final Integer scoreValue = FIXED_SCORE + entryAE.getKey() + entryRM.getKey();
        scoreMap.put(scoreValue, entryAE.getValue() + "," + entryRM.getValue());
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for LT is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }
}
