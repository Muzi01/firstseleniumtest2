package bindings.commons.automation.aio.rest.dto;

public class AustralianFinancialData implements FinancialData {

  public enum EducationLevel {
    SCHOOL,
    SCHOOL_HIGH,
    SCHOOL_TRADE,
    TAFE,
    SCHOOL_GRADUATE
  }

  public enum EmploymentType {
    FULL_TIME,
    PART_TIME,
    SELF_EMPLOYED,
    UNEMPLOYED,
    STUDENT,
    STAY_HOME_PARENT,
    RETIRED,
    OTHER
  }

  public enum MaritalStatus {
    SINGLE,
    MARRIED,
    PARTNER,
    DIVORCED,
    WIDOWED
  }

  public enum Residence {
    RENT,
    OWNER,
    WITH_PARENTS_BOARDING,
    MORTGAGE,
    OTHER
  }

  public enum ResidencyStatus {
    AU_CITIZEN,
    AU_PERMANENT_CITIZEN,
    NZ_CITIZEN,
    WORK_VISA,
    TRAVEL_VISA,
    STUDENT_VISA,
    SPONSORSHIP_VISA
  }

  public enum Gender {
    MALE,
    FEMALE,
    UNSPECIFIED
  }

  public enum Period {
    MONTHLY,
    WEEKLY
  }


  public enum AgeScore {
    AGE_RANGE1(18, 21, -1),
    AGE_RANGE2(22, 27, 4),
    AGE_RANGE3(28, 33, 6),
    AGE_RANGE4(34, 47, 7),
    AGE_RANGE5(48, 52, 11),
    AGE_RANGE6(53, 75, 15);

    protected Integer ageMin;
    protected Integer ageMax;
    protected Integer scoreValue;

    AgeScore(final Integer ageMin, final Integer ageMax, final Integer scoreValue) {
      this.ageMin = ageMin;
      this.ageMax = ageMax;
      this.scoreValue = scoreValue;
    }
  }
}
