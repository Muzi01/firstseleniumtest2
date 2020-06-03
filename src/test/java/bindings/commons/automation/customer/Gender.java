package bindings.commons.automation.customer;

public enum Gender {
  MALE('M'),
  FEMALE('F');

  private final Character genderCharacter;

  Gender(final Character genderCharacter) {
    this.genderCharacter = genderCharacter;
  }

  public Character getGenderCharacter() {
    return genderCharacter;
  }
}
