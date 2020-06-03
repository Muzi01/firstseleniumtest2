package bindings.commons.automation.aio.rest.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class PolishFinancialData implements FinancialData {

  private static final int CONSTANT = 200;
  private static final int INITIAL_REQUEST = 16;
  public static final int FIXED_ASC_SCORE = CONSTANT + INITIAL_REQUEST;
  public static final int FIXED_BSC_SCORE = 220;

  public enum MaritalStatus {
    SINGLE(-2),
    MARRIED(9),
    COHABITATING(7),
    DIVORCED(7),
    WIDOWED(9),
    OTHER(9);

    protected Integer scoreValue;

    MaritalStatus(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum Occupation {
    FULL_TIME,
    FIXED_TERM,
    CIVIL_AGREEMENT,
    SELF_EMPLOYED,
    DISABILITY_RETIRED,
    RETIRED,
    UNEMPLOYED,
    STUDENT,
    CONTRACT
  }

  public enum LoanPurpose {
    FINANCING_OTHER_LOANS,
    DAILY_EXPENDITURE,
    TRAVELING_VACATION,
    FAMILY,
    EDUCATION_TRAINING,
    CAR_REPAIR_OR_PURCHASE,
    FINANCING_BIG_PURCHASE,
    HOME_REPAIR_CONSTRUCTION,
    BUSINESS,
    MEDICAL_EXPENCES,
    OTHER,
    THIRD_PARTY
  }

  public enum BankAccountDuration {
    YEARS_LESS_1(-26),
    YEARS_1_3(-4),
    YEARS_OVER_3(12);

    protected Integer scoreValue;

    BankAccountDuration(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum EmploymentDuration {
    MONTHS_LESS_3(0),
    MONTHS_3_12(3),
    YEARS_1_3(5),
    YEARS_OVER_3(9);

    protected Integer scoreValue;

    EmploymentDuration(final Integer scoreValue) {
      this.scoreValue = scoreValue;
    }
  }

  public enum Age {
    ASC1(18, 25, -12),
    ASC2(26, 29, 3),
    ASC3(30, 35, 5),
    ASC4(36, 42, 8),
    ASC5(43, 75, 10),
    BSC1(18, 22, -3),
    BSC2(23, 28, 0),
    BSC3(29, 31, 1),
    BSC4(32, 37, 2),
    BSC5(38, 43, 6),
    BSC6(44, 54, 7),
    BSC7(55, 75, 1);

    protected Integer min;
    protected Integer max;
    protected Integer scoreValue;

    Age(final Integer min, final Integer max, final Integer scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }
  }

  public enum DeltaVista {
    DV1(0, 416, -14),
    DV2(417, 440, 0),
    DV3(441, 455, 5),
    DV4(456, 466, 7),
    DV5(467, 483, 12),
    DV6(484, 500, 16),
    DV7(501, 511, 21),
    DV8(512, 999, 28);

    protected Integer min;
    protected Integer max;
    protected Integer scoreValue;

    DeltaVista(final int min, final int max, final int scoreValue) {
      this.min = min;
      this.max = max;
      this.scoreValue = scoreValue;
    }

    public int getRandomValue() {
      return new Random().nextInt((this.max - this.min) + 1) + this.min;
    }

    public int getScoreValue() {
      return this.scoreValue;
    }

    public static String getNameForGivenValue(final int deltaVistaValue) {
      for (final DeltaVista deltaVista : DeltaVista.values()) {
        if (deltaVistaValue <= deltaVista.max && deltaVistaValue >= deltaVista.min) {
          return deltaVista.name();
        }
      }
      final String mesg =
          String.format("Delta vista range - not found for value = %d. Value should be in <%d,%d>.",
              deltaVistaValue, DeltaVista.values()[0].min,
              DeltaVista.values()[DeltaVista.values().length - 1].max);
      throw new IllegalStateException(mesg);
    }
  }

  private MaritalStatus maritalStatus;
  private String personalIDNumber;
  private Occupation occupation;
  private BankAccountDuration bankAccountDuration;
  private static final String CHANNEL = "loan_app";
  private String company;
  private EmploymentDuration employmentDuration;
  private LoanPurpose loanPurpose;
  private LocalDate employmentProvisionTermination;
  private Integer netIncome;

  /**
   * Used for financial-data-step2
   */
  public PolishFinancialData(final MaritalStatus maritalStatus, final String personalIDNumber) {
    this.maritalStatus = maritalStatus;
    this.personalIDNumber = personalIDNumber;
  }

  public PolishFinancialData() {
    // empty for chain-building purposes
  }

  public PolishFinancialData withOccupation(final Occupation occupation) {
    this.occupation = occupation;
    return this;
  }

  public PolishFinancialData withBankAccountDuration(final BankAccountDuration duration) {
    this.bankAccountDuration = duration;
    return this;
  }

  public PolishFinancialData withCompany(final String company) {
    this.company = company;
    return this;
  }

  public PolishFinancialData withEmploymentDuration(final EmploymentDuration duration) {
    this.employmentDuration = duration;
    return this;
  }

  public PolishFinancialData withLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
    return this;
  }

  public PolishFinancialData withEmploymentProvisionTermination(final LocalDate date) {
    this.employmentProvisionTermination = date;
    return this;
  }

  public PolishFinancialData withNetIncome(final Integer income) {
    this.netIncome = income;
    return this;
  }

  public BankAccountDuration getBankAccountDuration() {
    return this.bankAccountDuration;
  }

  public void setBankAccountDuration(final BankAccountDuration bankAccountDuration) {
    this.bankAccountDuration = bankAccountDuration;
  }

  public String getChannel() {
    return CHANNEL;
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

  public LoanPurpose getLoanPurpose() {
    return this.loanPurpose;
  }

  public void setLoanPurpose(final LoanPurpose loanPurpose) {
    this.loanPurpose = loanPurpose;
  }

  public LocalDate getEmploymentProvisionTermination() {
    return this.employmentProvisionTermination;
  }

  public void setEmploymentProvisionTermination(final LocalDate employmentProvisionTermination) {
    this.employmentProvisionTermination = employmentProvisionTermination;
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

  public MaritalStatus getMaritalStatus() {
    return this.maritalStatus;
  }

  public void setMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public String getPersonalIDNumber() {
    return this.personalIDNumber;
  }

  public void setPersonalIDNumber(final String personalIDNumber) {
    this.personalIDNumber = personalIDNumber;
  }

  public static Integer getAgeScoreValue(final Integer age, final Boolean bsc) {
    final String expectedName = bsc.equals(true) ? "BSC" : "ASC";
    for (final Age range : Age.values()) {
      if (age <= range.max && age >= range.min && range.name().contains(expectedName)) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for age value = " + age);
  }

  public static Integer getMaritalStatusValue(final String maritalStatus) {

    return MaritalStatus.valueOf(maritalStatus).scoreValue;
  }

  public static Integer getBankAccountDurationValue(final String bankAccountDuration) {

    return BankAccountDuration.valueOf(bankAccountDuration).scoreValue;
  }

  public static Integer getEmploymentDurationValue(final String employmentDuration) {

    return EmploymentDuration.valueOf(employmentDuration).scoreValue;
  }

  public static Integer getDeltaVistaValue(final String deltaVista) {

    return DeltaVista.valueOf(deltaVista).scoreValue;
  }

  // Scoring Map Values//
  private static Map<Integer, String> getScoreMapForAgeAndMaritalStatus() {
    final Map<Integer, String> ageAndMaritalMap = new HashMap<>();
    for (final Age age : Age.values()) {
      if (age.name().contains("ASC")) {
        for (final MaritalStatus maritalStatus : MaritalStatus.values()) {
          final Integer ageValue = age.min + 1;
          ageAndMaritalMap.put(age.scoreValue + maritalStatus.scoreValue,
              ageValue + "," + maritalStatus);
        }
      }
    }
    return ageAndMaritalMap;
  }

  private static Map<Integer, String> getScoreMapForBankAndEmployment() {
    final Map<Integer, String> bankAndEmploymentMap = new HashMap<>();
    for (final BankAccountDuration bankAccountDuration : BankAccountDuration.values()) {
      for (final EmploymentDuration employmentDuration : EmploymentDuration.values()) {
        bankAndEmploymentMap.put(bankAccountDuration.scoreValue + employmentDuration.scoreValue,
            bankAccountDuration + "," + employmentDuration);
      }
    }
    return bankAndEmploymentMap;
  }

  public static List<Map.Entry<Integer, String>> getASCScoreMap() {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Map.Entry<Integer, String> entryAM : getScoreMapForAgeAndMaritalStatus()
        .entrySet()) {
      for (final Map.Entry<Integer, String> entryBE : getScoreMapForBankAndEmployment()
          .entrySet()) {
        final Integer scoreValue = FIXED_ASC_SCORE + entryAM.getKey() + entryBE.getKey();
        scoreMap.put(scoreValue, entryAM.getValue() + "," + entryBE.getValue());
      }
    }
    return validateMapAndSort(scoreMap, false);
  }

  public static List<Map.Entry<Integer, String>> getBSCScoreMap(
      final Map<String, Integer> scorePLmap) {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Age age : Age.values()) {
      if (age.name().contains("BSC")) {
        for (final Map.Entry<String, Integer> entry : scorePLmap.entrySet()) {
          for (final DeltaVista deltaVista : DeltaVista.values()) {
            scoreMap.put(
                FIXED_BSC_SCORE + age.scoreValue + entry.getValue() + deltaVista.scoreValue,
                age.min + "," + entry.getKey() + "," + deltaVista.name());
          }
        }
      }
    }
    return validateMapAndSort(scoreMap, true);
  }

  private static List<Map.Entry<Integer, String>> validateMapAndSort(
      final Map<Integer, String> scoreMap,
      final Boolean bsc) {
    if (scoreMap.isEmpty()) {
      final String message =
          String.format("%s Score Map for PL is empty. Please check all data permutation.",
              bsc.equals(true) ? "BSC" : "ASC");
      throw new IllegalStateException(message);
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toList());
  }
}
