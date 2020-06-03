package bindings.commons.automation.generator.utils.scores;

class ScoreParameters {

  /**
   * Variable code: amtMaxCredits
   */
  public enum MaximumCreditsAmount {
    /** Champion -6, Challenger -6 */
    NONE(0),
    /** Champion 1, Challenger 2 */
    LOW(1),
    /** Champion 1, Challenger 2 */
    HIGH(13789),
    /** Champion 3, Challenger 4 */
    MAX(13790);

    private final int amount;

    MaximumCreditsAmount(final int amount) {
      this.amount = amount;
    }

    public String scoreString() {
      return String.format("%09d", this.amount);
    }
  }

  /**
   * Variable code: residenceType
   */
  public enum ResidenceType {
    /** Champion 2, Challenger 2 */
    OWNER("1"),
    /** Champion 0, Challenger 0 */
    RENT("2"),
    /** Champion 2, Challenger 2 */
    RETIREMENT("3"),
    /** Champion 2, Challenger 2 */
    MISSING(" ");

    private final String value;

    ResidenceType(final String value) {
      this.value = value;
    }

    public String scoreString() {
      return this.value;
    }
  }

  /**
   * If value = 0, Champion 2, Challenger 2 else see {@link RS13}
   */
  static class RS09 {
    private RS09() {
    }

    static String scoreString(final int value) {
      return String.format("%04d", value);
    }
  }

  /**
   * If {@link RS09} is not 0, calculate ratio = RS13/RS09 ratio equals 0: Champion 10, Challenger
   * 10 ratio in (0; 0.39): Champion -2, Challenger -2 ratio over 0.39: Champion -10, Challenger -9
   */
  static class RS13 {
    private RS13() {
    }

    static String scoreString(final int value) {
      return String.format("%04d", value);
    }
  }

  /**
   * Variable code: nrClosedAccounts
   */
  public enum ClosedAccountsNumber {
    /** Champion -3, Challenger -3 */
    LOW(4),
    /** Champion 6, Challenger 6 */
    MEDIUM(5),
    /** Champion 6, Challenger 6 */
    HIGH(19);

    private final int number;

    ClosedAccountsNumber(final int number) {
      this.number = number;
    }

    public String scoreString() {
      return String.format("%04d", this.number);
    }
  }

  public enum HistoricAccountsNumber {
    /** Champion -1, Challenger 2 */
    NONE(0),
    /** Champion 5, Challenger 2 */
    LOW(1),
    /** Champion -1, Challenger -1 */
    MEDIUM(4),
    /** Champion -3, Challenger -5 */
    HIGH(7);

    private final int number;

    HistoricAccountsNumber(final int number) {
      this.number = number;
    }

    public String scoreString() {
      return String.format("%04d", this.number);
    }
  }

  public enum NewAddress {
    /** Champion -2, Challenger -2 */
    YES("Y"),
    /** Champion 9, Challenger 9 */
    NO("N"),
    /** Champion 0, Challenger 0 */
    MISSING(" ");

    private final String value;

    NewAddress(final String value) {
      this.value = value;
    }

    public String scoreString() {
      return this.value;
    }
  }

  public enum FixedMortgagePaymentsAmount {
    /** Champion 4, Challenger 4 */
    NONE(0),
    /** Champion 0, Challenger 0 */
    LOW(500),
    /** Champion 0, Challenger 0 */
    MEDIUM(4000),
    /** Champion -4, Challenger -4 */
    HIGH(4001);

    private final int amount;

    FixedMortgagePaymentsAmount(final int amount) {
      this.amount = amount;
    }

    public String scoreString() {
      return String.format("%09d", this.amount);
    }
  }

  static String zeros(final int count) {
    return String.format("%09d", 0).substring(0, count);
  }

}
