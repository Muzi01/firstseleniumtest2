package bindings.commons.automation.aio.rest.finishfinancialdata;

import com.ipfdigital.automation.aio.rest.dto.FinancialData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

public class FinnishFinancialData implements FinancialData {

  private static final String REFERENCE_DATE_FOR_DAYS_AT_ADDRESS = "2000-01-01";
  private static final Integer CONSTANT = 205;
  private static final Integer BANK_OTHER = 3;
  private static final Integer BROWSER_OTHER = 0;
  private static final Integer MARITAL_STATUS = 4;
  private static final Integer NR_REJECTED_APPLICATION_6M = 2;
  private static final Integer EDUCATION_UNV = EducationFI.UNIVERSITY.value;
  public static final Integer FIXED_SCORE =
      CONSTANT + BANK_OTHER + BROWSER_OTHER + MARITAL_STATUS
          + NR_REJECTED_APPLICATION_6M + EDUCATION_UNV;

  private EducationFI education;
  private OccupationFI occupation;
  private OccupationTypeFI occupationType;
  private EmploymentDurationFI employmentDuration;
  private Integer salary;
  private Integer benefits;
  private Integer pension;
  private Integer otherIncome;
  private Integer housingCosts;
  private Integer livingCosts;
  private Integer otherLoans;
  private List<LoanFI> loans;
  private ResidenceFI residence;
  private Integer householdChildren;

  public FinnishFinancialData withEducation(final EducationFI education) {
    this.education = education;
    return this;
  }

  public FinnishFinancialData withOccupation(final OccupationFI occupation) {
    this.occupation = occupation;
    return this;
  }

  public FinnishFinancialData withOccupationType(final OccupationTypeFI occupationType) {
    this.occupationType = occupationType;
    return this;
  }

  public FinnishFinancialData withEmploymentDuration(
      final EmploymentDurationFI employmentDuration) {
    this.employmentDuration = employmentDuration;
    return this;
  }

  public FinnishFinancialData withSalary(final Integer salary) {
    this.salary = salary;
    return this;
  }

  public FinnishFinancialData withBenefits(final Integer benefits) {
    this.benefits = benefits;
    return this;
  }

  public FinnishFinancialData withPension(final Integer pension) {
    this.pension = pension;
    return this;
  }

  public FinnishFinancialData withOtherIncome(final Integer otherIncome) {
    this.otherIncome = otherIncome;
    return this;
  }

  public FinnishFinancialData withHousingCosts(final Integer housingCosts) {
    this.housingCosts = housingCosts;
    return this;
  }

  public FinnishFinancialData withLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
    return this;
  }

  public FinnishFinancialData withOtherLoans(final Integer otherLoans) {
    this.otherLoans = otherLoans;
    return this;
  }

  public FinnishFinancialData withLoans(final List<LoanFI> loans) {
    this.loans = loans;
    return this;
  }

  public FinnishFinancialData withResidence(final ResidenceFI residence) {
    this.residence = residence;
    return this;
  }

  public FinnishFinancialData withHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
    return this;
  }

  public EducationFI getEducation() {
    return this.education;
  }

  public void setEducation(final EducationFI education) {
    this.education = education;
  }

  public OccupationFI getOccupation() {
    return this.occupation;
  }

  public void setOccupation(final OccupationFI occupation) {
    this.occupation = occupation;
  }

  public OccupationTypeFI getOccupationType() {
    return this.occupationType;
  }

  public void setOccupationType(final OccupationTypeFI occupationType) {
    this.occupationType = occupationType;
  }

  public EmploymentDurationFI getEmploymentDuration() {
    return this.employmentDuration;
  }

  public void setEmploymentDuration(final EmploymentDurationFI employmentDuration) {
    this.employmentDuration = employmentDuration;
  }

  public Integer getSalary() {
    return this.salary;
  }

  public void setSalary(final Integer salary) {
    this.salary = salary;
  }

  public Integer getBenefits() {
    return this.benefits;
  }

  public void setBenefits(final Integer benefits) {
    this.benefits = benefits;
  }

  public Integer getPension() {
    return this.pension;
  }

  public void setPension(final Integer pension) {
    this.pension = pension;
  }

  public Integer getOtherIncome() {
    return this.otherIncome;
  }

  public void setOtherIncome(final Integer otherIncome) {
    this.otherIncome = otherIncome;
  }

  public Integer getHousingCosts() {
    return this.housingCosts;
  }

  public void setHousingCosts(final Integer housingCosts) {
    this.housingCosts = housingCosts;
  }

  public Integer getLivingCosts() {
    return this.livingCosts;
  }

  public void setLivingCosts(final Integer livingCosts) {
    this.livingCosts = livingCosts;
  }

  public Integer getOtherLoans() {
    return this.otherLoans;
  }

  public void setOtherLoans(final Integer otherLoans) {
    this.otherLoans = otherLoans;
  }

  public List<LoanFI> getLoans() {
    return this.loans;
  }

  public void setLoans(final List<LoanFI> loans) {
    this.loans = loans;
  }

  public ResidenceFI getResidence() {
    return this.residence;
  }

  public void setResidence(final ResidenceFI residence) {
    this.residence = residence;
  }

  public Integer getHouseholdChildren() {
    return this.householdChildren;
  }

  public void setHouseholdChildren(final Integer householdChildren) {
    this.householdChildren = householdChildren;
  }


  // Scorings
  public static Integer getAgeScoreValue(final Integer age) {
    final AgeScoreFI[] ranges = AgeScoreFI.values();
    for (final AgeScoreFI range : ranges) {
      if (age <= range.ageMax && age >= range.ageMin) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException("Not found any range for age value = " + age);
  }

  public static Integer getOccupationScoreValue(final String occupation,
      final String occupationType) {
    switch (occupation) {
      case "FULL_TIME":
        return OccupationTypeFI.valueOf(occupationType).fullTimeValue;
      case "FIXED_TERM":
        return OccupationTypeFI.valueOf(occupationType).fixedTermValue;
      case "PART_TIME":
        return OccupationTypeFI.valueOf(occupationType).partTimeValue;
      default:
        return OccupationFI.valueOf(occupation).value;
    }
  }

  private static Integer getHomeLoanScoreValue(final List<String> loan) {
    if (loan.contains(LoanFI.HOUSE.name())) {
      return LoanStatusFI.TRUE.homeLoan;
    } else {
      return LoanStatusFI.MISSING.homeLoan;
    }
  }

  public static Integer getSmallLoanScoreValue(final List<String> loan) {
    if (loan.contains(LoanFI.FAST.name())) {
      return LoanStatusFI.TRUE.smallLoan;
    } else {
      return LoanStatusFI.MISSING.smallLoan;
    }
  }

  private static Integer getStudentlLoanScoreValue(final List<String> loan) {
    if (loan.contains(LoanFI.STUDY.name())) {
      return LoanStatusFI.TRUE.studentLoan;
    } else {
      return LoanStatusFI.MISSING.studentLoan;
    }
  }

  public static Integer getResidenceScoreValue(final String residence) {
    return ResidenceFI.valueOf(residence).value;
  }

  public static Integer getHousingCostScoreValue(final Integer housingCost) {
    final HousingCostScoreFI[] ranges = HousingCostScoreFI.values();
    for (final HousingCostScoreFI range : ranges) {
      if (housingCost <= range.costMax && housingCost >= range.costMin) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "No matching housing cost range has been found for given value.");
  }

  public static Integer getDebtToIncomeScoreValue(final Integer debt, final Integer income) {
    Double value = (double) debt / income;
    final double valueTimes100 = value * 100;
    value = ((valueTimes100) - (int) (valueTimes100)) > 0.5 ? Math.ceil(valueTimes100) / 100
        : Math.floor(valueTimes100) / 100;
    final DebtToIncomeScoreFI[] ranges = DebtToIncomeScoreFI.values();
    for (final DebtToIncomeScoreFI range : ranges) {
      if (value <= range.ratioMax && value > range.ratioMin) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "No matching debt to income range has been found for given value.");
  }

  public static Integer provideDaysAtAddressScoreValue() {
    final Long days =
        DAYS.between(LocalDate.parse(REFERENCE_DATE_FOR_DAYS_AT_ADDRESS), LocalDate.now());
    final DaysAtAddressScoreFI[] daysAtAddressScores = DaysAtAddressScoreFI.values();
    for (final DaysAtAddressScoreFI range : daysAtAddressScores) {
      if (range.ratioMax >= days && range.ratioMin <= days) {
        return range.scoreValue;
      }
    }
    throw new IllegalStateException(
        "No matching days at address range has been found for given value.");
  }

  // Scoring Map Values//
  private static Integer getRandomCommonValueFromTwoRanges(final Integer minForRange1,
      final Integer maxForRange1, final Integer minForRange2, final Integer maxForRange2) {
    if (minForRange1 < maxForRange2 && minForRange2 < maxForRange1) {
      final Integer min = (minForRange1 <= minForRange2) ? minForRange2 : minForRange1;
      final Integer max = (maxForRange1 <= maxForRange2) ? maxForRange1 : maxForRange2;
      return min + (new Random().nextInt((max - min) + 1));
    }
    return null;
  }

  private static Map<Integer, Integer> provideHousingAndDeptScores(final Integer salary) {
    final Map<Integer, Integer> housingCostsMap = new HashMap<>();
    for (final DebtToIncomeScoreFI debtToIncomeScore : DebtToIncomeScoreFI.values()) {
      final Integer debtToIncomeMin = (int) (salary * (debtToIncomeScore.ratioMin + 0.1));
      final Integer debtToIncomeMax = (int) (salary * (debtToIncomeScore.ratioMax - 0.1));
      for (final HousingCostScoreFI housingCostScore : HousingCostScoreFI.values()) {
        final Integer housingCostRandom = getRandomCommonValueFromTwoRanges(debtToIncomeMin,
            debtToIncomeMax, housingCostScore.costMin, housingCostScore.costMax);
        if (housingCostRandom != null) {
          housingCostsMap.put(housingCostScore.scoreValue + debtToIncomeScore.scoreValue,
              housingCostRandom);
        }
      }
    }
    return housingCostsMap;
  }

  private static Map<Integer, String> getScoreMapForLoan() {
    final Map<Integer, String> loanMap = new HashMap<>();
    final List<String> loanList =
        Stream.of(LoanFI.values()).map(Enum::name).collect(Collectors.toList());
    for (final String loan : loanList) {
      final Integer homeLoanScoreValue =
          getHomeLoanScoreValue(new ArrayList<>(Arrays.asList(loan)));
      final Integer smallLoanValue = getSmallLoanScoreValue(new ArrayList<>(Arrays.asList(loan)));
      final Integer studentLoanValue =
          getStudentlLoanScoreValue(new ArrayList<>(Arrays.asList(loan)));
      final Integer sum = homeLoanScoreValue + smallLoanValue + studentLoanValue;
      loanMap.put(sum, loan);
    }
    return loanMap;
  }


  private static Map<Integer, String> provideCombinedHousingAndDeptScores(final Integer salary) {
    return provideHousingAndDeptScores(salary).entrySet().stream()
        .collect(Collectors.toMap(Entry::getKey, map -> map.getValue().toString()));
  }

  private static Map<Integer, String> provideResidenceScores() {
    return Arrays.stream(ResidenceFI.values())
        .collect(Collectors.toMap(residence -> residence.value, Enum::name));
  }

  private static Map<Integer, String> getDeptAndResidenceHouseCost(
      final Integer salary) {
    final Map<Integer, String> residenceAndHousingCostAndDeptScoreMap = new HashMap<>();
    for (final Entry<Integer, String> entryRS : provideResidenceScores().entrySet()) {
      for (final Entry<Integer, String> entryHA : provideCombinedHousingAndDeptScores(salary)
          .entrySet()) {
        residenceAndHousingCostAndDeptScoreMap.put(entryRS.getKey() + entryHA.getKey(),
            entryRS.getValue() + "," + entryHA.getValue());
      }
    }
    return residenceAndHousingCostAndDeptScoreMap;
  }

  public static List<Entry<Integer, String>> getScoreMap(final Integer salary,
                                                         final String email,
                                                         final String msisdn) {
    final Map<Integer, String> scoreMap = new HashMap<>();
    for (final Entry<Integer, String> entryDeptAndResidenceHouseCost : getDeptAndResidenceHouseCost(
        salary).entrySet()) {
      for (final Entry<Integer, String> entryLoan : getScoreMapForLoan().entrySet()) {
        final Integer mailScore = provideScoreForDigitInEmail(email);
        final Integer phoneOperatorScore = provideScoreForPhoneOperator(msisdn);
        final Integer daysAtAddressScoreValue = provideDaysAtAddressScoreValue();
        final Integer deptAndResidenceHouseScore = entryDeptAndResidenceHouseCost.getKey();
        final Integer loanScore = entryLoan.getKey();
        final Integer scoreValue =
            FIXED_SCORE + mailScore + phoneOperatorScore
                + daysAtAddressScoreValue + deptAndResidenceHouseScore
                + loanScore;
        scoreMap.put(scoreValue,
            entryLoan.getValue() + "," + entryDeptAndResidenceHouseCost.getValue());
      }
    }
    if (scoreMap.isEmpty()) {
      throw new IllegalStateException(
          "Score Map for FI is empty. Please check all data permutation.");
    }
    return scoreMap.entrySet()
        .stream()
        .sorted(Entry.comparingByKey())
        .collect(Collectors.toList());
  }

  private static Integer provideScoreForPhoneOperator(final String msisdn) {
    final String msisdnWithoutZeroPrefix = msisdn.startsWith("0") ? msisdn.substring(1) : msisdn;
    return Stream.of(OriginalPhoneOperatorScoreFI.values())
        .filter(value -> msisdnWithoutZeroPrefix.startsWith(value.phoneOperatorDigits.toString()))
        .findFirst()
        .map(value -> value.scoreValue)
        .orElse(
            OriginalPhoneOperatorScoreFI.OTHER_MISSING.scoreValue);
  }

  private static Integer provideScoreForDigitInEmail(final String email) {
    if (email.matches(".*\\d.*")) {
      return EmailWithDigit.YES.value;
    }
    return EmailWithDigit.NO.value;
  }

}
