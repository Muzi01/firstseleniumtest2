package bindings.commons.automation.generator.utils.scores;

import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData;
import com.ipfdigital.automation.aio.rest.dto.SpanishFinancialData.RiskMicroScore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public enum ScoreES {

  HOCELOT("Hocelot"),
  EQUIFAX_IDRSK2("Equifax_IDRSK2"),
  EQUIFAX_IPF("Equifax_IPF"),
  EQUIFAX_INC("Equifax_INC");

  protected String fullName;

  ScoreES(final String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return this.fullName;
  }

  private static final Logger LOG = LogManager.getLogger(ScoreES.class);
  private static final String SCORING_PATH = "scoringES.properties";
  private static final Properties scoringProperties = new Properties();


  public String getMessageForHocelot(final Integer savings, final Double defProbability,
      final Double timeFromPortability, final String mobile) {
    return scoringProperties.getProperty(HOCELOT.fullName)
        .replace("%SAVINGS_CAPABILITY%", savings.toString())
        .replace("%DEFAULT_PROBABILITY%", defProbability.toString())
        .replace("%PORTABILITY_DATE%",
            SpanishFinancialData.getDateForGivenTimeFromPortabilityFactor(timeFromPortability)
                .toString())
        .replace("%MOBILE_ORIGINAL_OPERATOR%", mobile);
  }

  public String getIpfMessageForEquifax(final String numberOfJudicalIncidents,
      final RiskMicroScore riskMicroScore) {
    return scoringProperties.getProperty(EQUIFAX_IPF.fullName)
        .replace("%TOTAL_JUDICAL_INCIDENTS%", numberOfJudicalIncidents)
        .replace("%MICRO_SCORE%", String.valueOf(riskMicroScore.getMicroScore()))
        .replace("%RISK_SCORE%", String.valueOf(riskMicroScore.getRiskScore()));
  }

  public String getIncMessageForEquifax() {
    return scoringProperties.getProperty(EQUIFAX_INC.fullName);
  }

  public String getIdrsk2MessageForEquifax() {
    return scoringProperties.getProperty(EQUIFAX_IDRSK2.fullName);
  }

  static {

    try {
      scoringProperties.load(ScoreES.class.getClassLoader().getResourceAsStream(SCORING_PATH));
      LOG.info("Loaded scoring from file: {}", SCORING_PATH);
    } catch (final IOException | NullPointerException e) {
      LOG.error("Unable to read scoring from file: {}", SCORING_PATH);
    }
  }
}
