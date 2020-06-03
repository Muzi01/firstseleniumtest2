package bindings.commons.automation.aio.rest.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class SpanishFinancialData implements FinancialData {

  private static final Integer CONSTANT = 216;
  private static final Integer NR_REJECTED_APP_3M = 2;
  private static final Integer EMAIL_FAKE = 5;
  private static final Integer WIDTH_RES_1920 = 0;
  private static final Integer POLICY_DEFAULT = -2;
  private static final Integer DEVICE_RESULT_NOT_FOUND = -1;
  public static final Integer FIXED_SCORE = CONSTANT + NR_REJECTED_APP_3M + EMAIL_FAKE
      + WIDTH_RES_1920 + POLICY_DEFAULT + DEVICE_RESULT_NOT_FOUND;

  public enum EmploymentDuration {
    MONTHS_1_6,
    MONTHS_7_12,
    YEARS_1_3,
    YEARS_4_10,
    YEARS_OVER_10
  }

  public enum OccupationType {
    EMPLOYEE,
    MANAGER,
    GOVERNMENT_OFFICIAL,
    SPECIALIST,
    OTHER
  }

  public enum Education {
    UNIVERSITY,
    POLYTECHNIC,
    SECONDARY,
    PRIMARY,
    OTHER_MISSING
  }

  public enum MaritalStatus {
    SINGLE,
    COHABITATING,
    MARRIED,
    DIVORCED,
    WIDOWED
  }

  public enum Occupation {
    FULL_TIME,
    FIXED_TERM,
    PART_TIME,
    ENTREPRENEUR,
    RETIRED,
    STUDENT,
    UNEMPLOYED,
    HOUSE_WIFE,
    OTHER
  }

  public enum Residence {
    RENT,
    OWN,
    WITH_PARENT,
    OTHER
  }

  public enum Citizenship {
    DNI(0),
    NIE(-10);

    protected Integer scoreValue;

    Citizenship(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum Mobile {
    MOVISTAR(6),
    ORANGE(0),
    VODAFONE(-1),
    OTHER(-12);

    protected Integer scoreValue;

    Mobile(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }

    public Integer getScoreValue() {
      return this.scoreValue;
    }
  }

  public enum CommunityMedianIncome {
    C1("89001", -7),
    C2("32001", -1),
    C3("08001", 2),
    C4("01001", 8);

    protected String postCode;
    protected Integer scoreValue;

    CommunityMedianIncome(final String postCode, final Integer scoreValue) {
      this.postCode = postCode;
      this.scoreValue = scoreValue;
    }

    public String getPostCode() {
      return this.postCode;
    }

    public Integer getScoreValue() {
      return this.scoreValue;
    }
  }

  public enum TimeFromPortability {
    TIME_LOWEST(0.0, 0.5, -2),
    TIME_LOW(0.5, 1.75, 6),
    TIME_MEDIUM(1.75, 7.0, 9),
    TIME_HIGH(7.0, 100.0, 13);

    protected Double min;
    protected Double max;
    protected Integer scoreValue;

    TimeFromPortability(final Double min, final Double max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }

    public Double getRandomValueForGivenRange() {
      final String random = String.format(Locale.ROOT, "%.1f",
          this.min + new Random().nextDouble() * (this.max - this.min + 0.1));
      return Double.parseDouble(random);
    }
  }

  public enum ProbabilityOfDefault {
    PROBABILITY1(0.0, 0.025, 4),
    PROBABILITY2(0.025, 0.04, 3),
    PROBABILITY3(0.04, 0.05, 1),
    PROBABILITY4(0.05, 0.063, 1),
    PROBABILITY5(0.063, 0.07, 0),
    PROBABILITY6(0.07, 0.09, -1),
    PROBABILITY7(0.09, 0.1, -3),
    PROBABILITY8(0.1, 100.00, -5);

    protected Double min;
    protected Double max;
    protected Integer scoreValue;

    ProbabilityOfDefault(final Double min, final Double max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }

    public Double getRandomValueFromGivenRange() {
      final String random = String.format(Locale.ROOT, "%.3f",
          this.min + new Random().nextDouble() * (this.max - 0.01 - this.min));
      return Double.parseDouble(random);
    }
  }

  public enum SavingsCapability {
    SAVINGS1(0, 89, -3),
    SAVINGS2(89, 160, 0),
    SAVINGS3(160, 235, 1),
    SAVINGS4(235, 1000, 2);

    protected Integer min;
    protected Integer max;
    protected Integer scoreValue;

    SavingsCapability(final Integer min, final Integer max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }

    public Integer getRandomValueFromGivenRange() {
      return this.min + (new Random().nextInt(((this.max - 1) - this.min) + 1));
    }
  }

  public enum Age {
    AGE_RANGE1(18, 26, -14),
    AGE_RANGE2(27, 32, -10),
    AGE_RANGE3(33, 35, -7),
    AGE_RANGE4(36, 39, -3),
    AGE_RANGE5(40, 44, 0),
    AGE_RANGE6(45, 49, 5),
    AGE_RANGE7(50, 55, 9),
    AGE_RANGE8(56, 61, 11),
    AGE_RANGE9(62, 75, 9);

    protected Integer ageMin;
    protected Integer ageMax;
    protected Integer scoreValue;

    Age(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
      this.ageMin = ageMin;
      this.ageMax = ageMax;
      this.scoreValue = scoreValue;
    }
  }

  public enum NetIncome {
    // NET_INCOME1(0, 80000, -5),
    NET_INCOME2(80001, 100000, -2),
    NET_INCOME3(100001, 140000, -1),
    NET_INCOME4(140001, 175000, -0),
    NET_INCOME5(175001, 240000, 5),
    NET_INCOME6(240001, 1000000, 11);

    protected Integer min;
    protected Integer max;
    protected Integer scoreValue;

    public int randomFromRange() {
      return this.min + 1 + new Random().nextInt(this.max - this.min - 2);
    }

    NetIncome(final Integer min, final Integer max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }
  }

  public enum NumIndicator {
    OTHER("-999", 7),
    ONE("1", -3),
    TWO("2", -9);

    protected String value;
    protected int scoreValue;

    NumIndicator(final String value, final int scoreValue) {
      this.value = value;
      this.scoreValue = scoreValue;
    }

    public String getValue() {
      return this.value;
    }

    public int getScoreValue() {
      return this.scoreValue;
    }
  }

  // Matrix descibed in ES - technical strategy .. Excel worksheet (Application Score Card NEW)
  public enum RiskMicroScore {
    C1(6, 1, 9),
    C2(5, 2, 6),
    C3(3, 1, 2),
    C4(4, 3, -1),
    C5(3, 2, -1),
    C6(2, 3, -11),
    C7(1, 6, -25);

    protected int riskScore;
    protected int microScore;
    protected int scoreValue;

    RiskMicroScore(final int riskScore, final int microScore, final Integer scoreValue) {
      this.riskScore = riskScore;
      this.microScore = microScore;
      this.scoreValue = scoreValue;
    }

    public int getRiskScore() {
      return this.riskScore;
    }

    public int getMicroScore() {
      return this.microScore;
    }

    public int getScoreValue() {
      return this.scoreValue;
    }
  }

  private String channel;
  private String company;
  private EmploymentDuration employmentDuration;
  private OccupationType occupationType;
  private Education education;
  private Integer householdChildren;
  private MaritalStatus maritalStatus;
  private Integer netIncome;
  private Occupation occupation;
  private Residence residence;

  public SpanishFinancialData withChannel(final String channel) {
    this.channel = channel;
    return this;
  }

  public SpanishFinancialData withCompany(final String company) {
    this.company = company;
    return this;
  }

  public SpanishFinancialData withEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
    return this;
  }

  public SpanishFinancialData withOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
    return this;
  }

  public SpanishFinancialData withEducation(final Education education) {
    this.education = education;
    return this;
  }

  public SpanishFinancialData withHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public SpanishFinancialData withMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
    return this;
  }

  public SpanishFinancialData withNetIncome(final Integer netIncome) {
    this.netIncome = netIncome;
    return this;
  }

  public SpanishFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public SpanishFinancialData withResidence(final Residence residence) {
    this.residence = residence;
    return this;
  }

  public String getChannel() {
    return this.channel;
  }

  public void setChannel(final String channel) {
    this.channel = channel;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public EmploymentDuration getEmploymentDuration() {
    return this.employmentDuration;
  }

  public void setEmploymentDuration(final EmploymentDuration employmentDuration) {
    this.employmentDuration = employmentDuration;
  }

  public OccupationType getOccupationType() {
    return this.occupationType;
  }

  public void setOccupationType(final OccupationType occupationType) {
    this.occupationType = occupationType;
  }

  public Education getEducation() {
    return this.education;
  }

  public void setEducation(final Education education) {
    this.education = education;
  }

  public Integer getHouseholdChildren() {
    return this.householdChildren;
  }

  public void setHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
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

  public Residence getResidence() {
    return this.residence;
  }

  public void setResidence(final Residence residence) {
    this.residence = residence;
  }


  // Scorings
  public static Integer getAgeScoreValue(final Integer age) {
    final Age[] ranges = Age.values();
    for (final Age range : ranges) {
      if (age <= range.ageMax && age >= range.ageMin) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for age value = " + age);
  }

  public static Integer getSalaryScoreValue(final Integer netIncome) {
    final NetIncome[] ranges = NetIncome.values();
    for (final NetIncome range : ranges) {
      if (netIncome <= range.max && netIncome >= range.min) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for netIncome value = " + netIncome);
  }

  public static Integer getSavingsCapabilityScoreValue(final Integer savings) {
    final SavingsCapability[] ranges = SavingsCapability.values();
    for (final SavingsCapability range : ranges) {
      if (savings <= range.max && savings >= range.min) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "Not found any range for Savings capability value = " + savings);
  }

  public static Integer getProbabilityOfDefaultScoreValue(final Double probabilityOfDefault) {
    final ProbabilityOfDefault[] ranges = ProbabilityOfDefault.values();
    for (final ProbabilityOfDefault range : ranges) {
      if (probabilityOfDefault <= range.max && probabilityOfDefault >= range.min) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "Not found any range for ProbabilityOfDefault value = " + probabilityOfDefault);
  }

  public static Integer getTimeFromPortabilityScoreValue(final Double timeFromPortability) {
    final TimeFromPortability[] ranges = TimeFromPortability.values();
    for (final TimeFromPortability range : ranges) {
      if (timeFromPortability <= range.max && timeFromPortability >= range.min) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "Not found any range for TimeFromPortability value = " + timeFromPortability);
  }

  public static Integer getPostCodeScoreValue(final String community) {
    return CommunityMedianIncome.valueOf(community).scoreValue;
  }

  public static Integer getMobileScoreValue(final String mobile) {
    return Mobile.valueOf(mobile).scoreValue;
  }

  public static Integer getIsNieScoreValue(final Boolean isNie) {
    return (isNie) ? Citizenship.NIE.scoreValue : Citizenship.DNI.scoreValue;
  }

  public static Integer getNumIndicatorScoreValue(final String numIndicator) {
    return NumIndicator.valueOf(numIndicator).scoreValue;
  }

  public static Integer getRiskMicroScoreValue(final String riskMicroScore) {
    return RiskMicroScore.valueOf(riskMicroScore).scoreValue;
  }


  // Scoring Map Values//
  private static Map<Integer, String> getScoreMapForSavingsAndProbability() {
    final Map<Integer, String> savingsAndProbabilityMap = new HashMap<>();
    for (final SavingsCapability savingItem : SavingsCapability.values()) {
      for (final ProbabilityOfDefault probabilityItem : ProbabilityOfDefault.values()) {
        final Integer randomSavingValue = savingItem.getRandomValueFromGivenRange();
        final Double randomProbabilityOfDefault = probabilityItem.getRandomValueFromGivenRange();
        savingsAndProbabilityMap.put(savingItem.scoreValue + probabilityItem.scoreValue,
            randomSavingValue + "," + randomProbabilityOfDefault);
      }
    }
    return savingsAndProbabilityMap;
  }

  private static Map<Integer, String> getScoreMapForPortabilityAndMobile() {
    final Map<Integer, String> portabilityAndMobileMap = new HashMap<>();
    for (final TimeFromPortability portabilityItem : TimeFromPortability.values()) {
      for (final Mobile mobile : Mobile.values()) {
        portabilityAndMobileMap.put(portabilityItem.scoreValue + mobile.scoreValue,
            portabilityItem.getRandomValueForGivenRange() + "," + mobile.name());
      }
    }
    return portabilityAndMobileMap;
  }

  private static Map<Integer, String> getScoreMapForAgeAndNetIncome() {
    final Map<Integer, String> ageAndIncomeMap = new HashMap<>();
    for (final Age ageItem : Age.values()) {
      for (final NetIncome netIncomeItem : NetIncome.values()) {
        final Integer netIncomeValue = netIncomeItem.randomFromRange();
        final Integer ageValue = ageItem.ageMin + 1;
        ageAndIncomeMap.put(ageItem.scoreValue + netIncomeItem.scoreValue,
            ageValue + "," + netIncomeValue);
      }
    }
    return ageAndIncomeMap;
  }

  private static Map<Integer, String> getScoreMapForHocelot() {
    final Map<Integer, String> hocelotMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entrySP : getScoreMapForSavingsAndProbability()
        .entrySet()) {
      for (final Map.Entry<Integer, String> entryPM : getScoreMapForPortabilityAndMobile()
          .entrySet()) {
        hocelotMap.put(entrySP.getKey() + entryPM.getKey(),
            entrySP.getValue() + "," + entryPM.getValue());
      }
    }
    return hocelotMap;
  }

  private static Map<Integer, String> getScoreMapForEquifax() {
    final Map<Integer, String> equifaxMap = new HashMap<>();
    for (final NumIndicator numIndicator : NumIndicator.values()) {
      for (final RiskMicroScore riskMicroScore : RiskMicroScore.values()) {
        equifaxMap.put(riskMicroScore.getScoreValue() + numIndicator.getScoreValue(),
            numIndicator.toString() + "," + riskMicroScore.toString());
      }
    }
    return equifaxMap;
  }

  private static Map<Integer, String> getScoreMapForEquifaxAndHocelot() {
    final Map<Integer, String> equifaxAndHocelotMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryHocelot : getScoreMapForHocelot().entrySet()) {
      for (final Map.Entry<Integer, String> entryEquifax : getScoreMapForEquifax().entrySet()) {
        final Integer scoreValue = entryHocelot.getKey() + entryEquifax.getKey();
        equifaxAndHocelotMap.put(scoreValue,
            entryEquifax.getValue() + "," + entryHocelot.getValue());
      }
    }
    return equifaxAndHocelotMap;
  }

  private static Map<Integer, String> getScoreMapForAIO() {
    final Map<Integer, String> aioMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entrySP : getScoreMapForAgeAndNetIncome().entrySet()) {
      for (final CommunityMedianIncome communityItem : CommunityMedianIncome.values()) {
        aioMap.put(entrySP.getKey() + communityItem.scoreValue,
            entrySP.getValue() + "," + communityItem.name());
      }
    }
    return aioMap;
  }

  public static List<Map.Entry<Integer, String>> getScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryEH : getScoreMapForEquifaxAndHocelot().entrySet()) {
      for (final Map.Entry<Integer, String> entryAIO : getScoreMapForAIO().entrySet()) {
        final Integer scoreValue =
            SpanishFinancialData.FIXED_SCORE + entryAIO.getKey() + entryEH.getKey();
        scoreMap.put(scoreValue, entryAIO.getValue() + "," + entryEH.getValue());
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for ES is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }

  public static LocalDate getDateForGivenTimeFromPortabilityFactor(final Double year) {
    final Integer numberOfDays = (int) (year * 365);
    return LocalDate.now().minusDays(numberOfDays);
  }
}
