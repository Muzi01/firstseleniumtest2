package bindings.commons.automation.generator.utils.scores;

import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.ClosedAccountsNumber;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.FixedMortgagePaymentsAmount;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.HistoricAccountsNumber;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.MaximumCreditsAmount;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.NewAddress;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.RS09;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.RS13;
import com.ipfdigital.automation.generator.utils.scores.ScoreParameters.ResidenceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static com.ipfdigital.automation.generator.utils.scores.ScoreParameters.zeros;

public enum ScoreMX {
  CHAMPION_S,
  /** Scoring - 262 */
  CHAMPION_A_PLUS,
  /** Scoring - 248 */
  CHAMPION_A,
  /** Scoring - 239 - using DEFAULT values */
  CHAMPION_B,
  /** Scoring - 237 */
  CHAMPION_C_PLUS,
  /** Scoring - 228 */
  CHAMPION_C,
  /** Scoring - 224 */
  CHAMPION_D,
  /** Scoring - 217 */
  CHAMPION_E,
  /** Scoring - 213 */
  CHAMPION_E_MINUS,
  /** Scoring - 205 */
  CHALLENGER_S,
  /** Scoring - 251 */
  CHALLENGER_A_PLUS,
  /** Scoring - 251 */
  CHALLENGER_A,
  /** Scoring - 239 */
  CHALLENGER_B,
  /** Scoring - 234 */
  CHALLENGER_C_PLUS,
  /** Scoring - 231 - using DEFAULT values */
  CHALLENGER_C,
  /** Scoring - 220 */
  CHALLENGER_D,
  /** Scoring - 217 */
  CHALLENGER_E,
  /** Scoring - 213 */
  CHALLENGER_E_MINUS;
  /** Scoring - 210 */
  private static final Logger LOG = LogManager.getLogger(ScoreMX.class);

  public static final String CHAMPION = "CHAMPION";
  public static final String CHALLENGER = "CHALLENGER";

  private static final String RS_06_1 = zeros(2);
  private static final String RS_11_1 = zeros(4);
  private static final String RS_22_1 = zeros(9);
  private static final String RS_25_1 = zeros(9);
  private static final String SCORE_007 = "700";
  private static final String SCORE_009 = "650";
  private static final String SCORING_PATH = "scoringMX.properties";
  public static final String DATE_PATTERN = "ddMMyyyy";
  private static final Properties scoringProperties = new Properties();

  private ResidenceType chooseResidenceType() {
    switch (this) {
      case CHAMPION_E_MINUS:
      case CHAMPION_D:
      case CHAMPION_E:
      case CHALLENGER_E:
        return ResidenceType.RENT;
      default:
        return ResidenceType.OWNER;
    }
  }

  // Find out if this is relevant or should be constant
  private long chooseDaysSinceIntegration() {
    if (this == CHALLENGER_A_PLUS)
      return 5768;
    else {
      return 417;
    }
  }

  private int chooseRs09() {
    switch (this) {
      case CHAMPION_S:
      case CHALLENGER_S:
      case CHALLENGER_A_PLUS:
      case CHALLENGER_A:
      case CHAMPION_E_MINUS:
        return 100;
      default:
        return 0;
    }
  }

  private int chooseRs13() {
    if (this == CHAMPION_E_MINUS)
      return 40;
    else {
      return 0;
    }
  }

  private ClosedAccountsNumber chooseCloseAccountsNumber() {
    switch (this) {
      case CHAMPION_S:
      case CHAMPION_A_PLUS:
      case CHALLENGER_S:
      case CHALLENGER_A_PLUS:
      case CHALLENGER_E:
        return ClosedAccountsNumber.HIGH;
      default:
        return ClosedAccountsNumber.LOW;
    }
  }

  private HistoricAccountsNumber chooseHistoricAccountsNumber() {
    switch (this) {
      case CHAMPION_S:
      case CHALLENGER_S:
      case CHALLENGER_A_PLUS:
      case CHALLENGER_B:
        return HistoricAccountsNumber.LOW;
      default:
        return HistoricAccountsNumber.MEDIUM;
    }
  }

  private NewAddress chooseNewAddress() {
    switch (this) {
      case CHAMPION_C:
      case CHAMPION_E_MINUS:
      case CHAMPION_C_PLUS:
      case CHAMPION_D:
      case CHAMPION_E:
      case CHALLENGER_E:
      case CHALLENGER_E_MINUS:
      case CHALLENGER_C:
        return NewAddress.YES;
      default:
        return NewAddress.NO;
    }
  }

  private MaximumCreditsAmount chooseMaximumCreditsAmount() {
    switch (this) {
      case CHAMPION_B:
        return MaximumCreditsAmount.HIGH;
      case CHAMPION_E_MINUS:
      case CHAMPION_D:
      case CHAMPION_E:
      case CHALLENGER_D:
      case CHALLENGER_E:
      case CHALLENGER_E_MINUS:
        return MaximumCreditsAmount.NONE;
      default:
        return MaximumCreditsAmount.MAX;
    }
  }

  private FixedMortgagePaymentsAmount chooseFixedMortgagePaymentsAmount() {
    switch (this) {
      case CHAMPION_C:
      case CHAMPION_E:
      case CHALLENGER_E:
      case CHALLENGER_D:
        return FixedMortgagePaymentsAmount.LOW;
      default:
        return FixedMortgagePaymentsAmount.NONE;
    }
  }

  public String getMicrofinanceMessage() {
    return String.format(getTemplateMicrofinanceMessage(), SCORE_009);
  }

  public static String getFullBDCMessage(final ScoreMXInsertRequest params) {
    final String rsRs1 =
        LocalDate.now().minusDays(417).format(DateTimeFormatter.ofPattern(DATE_PATTERN));

    return String.format(
        getTemplateBDCMessage(),
        params.residenceType.scoreString(),
        rsRs1,
        RS_06_1,
        RS09.scoreString(params.rs09),
        RS_11_1,
        params.closedAccountsNumber.scoreString(),
        RS13.scoreString(params.rs13),
        params.historicAccountsNumber.scoreString(),
        params.newAddress.scoreString(),
        params.maximumCreditsAmount.scoreString(),
        RS_22_1,
        RS_25_1,
        params.fixedMortgagePaymentsAmount.scoreString(),
        SCORE_007,
        SCORE_009);
  }

  public String getFullBDCMessage() {
    final String rsRs1 = LocalDate.now().minusDays(chooseDaysSinceIntegration())
        .format(DateTimeFormatter.ofPattern(DATE_PATTERN));

    return String.format(
        getTemplateBDCMessage(),
        chooseResidenceType().scoreString(),
        rsRs1,
        RS_06_1,
        RS09.scoreString(chooseRs09()),
        RS_11_1,
        chooseCloseAccountsNumber().scoreString(),
        RS13.scoreString(chooseRs13()),
        chooseHistoricAccountsNumber().scoreString(),
        chooseNewAddress().scoreString(),
        chooseMaximumCreditsAmount().scoreString(),
        RS_22_1,
        RS_25_1,
        chooseFixedMortgagePaymentsAmount().scoreString(),
        SCORE_007,
        SCORE_009);
  }

  public String getCreditReportMessage() {
    final String rsRs1 = LocalDate.now().minusDays(chooseDaysSinceIntegration())
        .format(DateTimeFormatter.ofPattern(DATE_PATTERN));

    return String.format(
        getTemplateCreditReportMessage(),
        chooseResidenceType().scoreString(),
        rsRs1,
        RS_06_1,
        RS09.scoreString(chooseRs09()),
        RS_11_1,
        chooseCloseAccountsNumber().scoreString(),
        RS13.scoreString(chooseRs13()),
        chooseHistoricAccountsNumber().scoreString(),
        chooseNewAddress().scoreString(),
        chooseMaximumCreditsAmount().scoreString(),
        RS_22_1,
        RS_25_1,
        chooseFixedMortgagePaymentsAmount().scoreString());
  }

  private static String getTemplateBDCMessage() {
    return scoringProperties.getProperty("scoring.mx.full");
  }

  public String getTemplateMicrofinanceMessage() {
    return scoringProperties.getProperty("scoring.mx.microfinance");
  }

  public String getTemplateCreditReportMessage() {
    return scoringProperties.getProperty("scoring.mx.creditreport");
  }

  static {
    try {
      scoringProperties.load(ScoreMX.class.getClassLoader().getResourceAsStream(SCORING_PATH));
      LOG.info("Loaded scoring from file: {}", SCORING_PATH);
    } catch (final IOException | NullPointerException e) {
      LOG.error("Unable to read scoring from file: {}", SCORING_PATH);
    }
  }
}
