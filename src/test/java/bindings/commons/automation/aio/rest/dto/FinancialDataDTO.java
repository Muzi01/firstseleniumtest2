package bindings.commons.automation.aio.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class FinancialDataDTO {
  public Education education;
  public EmploymentDuration employmentDuration;
  public MaritalStatus maritalStatus;
  public Occupation occupation;
  public OccupationType occupationType;
  public Loan[] loans;
  public LoanPurpose loanPurpose;
  public Residence residence;
  public Integer livingCosts;
  public Integer netIncome;
  public String company;
  public Integer householdChildren;
  public Integer totalMonthlyObligations;
  public String channel;
  public Integer monthlyObligations;
  public Boolean receiveRefinance;
  public List<Loan> debtTypesExperienced;
  public LoanDTO[] otherDebts;
  public DebtTypeMonthly debtTypeMonthly;
  public String personalIDNumber;
  public BankAccountDuration bankAccountDuration;
  public LocalDate employmentProvisionTermination;
  public Integer salary;
  public Integer benefits;
  public Integer pension;
  public Integer otherIncome;
  public Integer housingCosts;
  public Integer otherLoans;
  public LearnedAboutUsFromType learnedAboutUsFrom;
  public Boolean creditBureauQuestion1Answer;
  public IncomeFrequency incomeFrequency;
}
