package bindings.commons.automation.generator.utils.scores;

import com.ipfdigital.automation.generator.portals.Credit24AU;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public enum ScoreAU {

  SCORE_225(225, 18, 20, 3000, "scoringA.au"),
  SCORE_235(235, 55, 64, 3500, "scoringA.au"),
  SCORE_245(245, 18, 20, 2000, "scoringB.au"),
  SCORE_255(255, 29, 38, 5000, "scoringB.au"),
  SCORE_265(265, 18, 20, 1000, "scoringC.au"),
  SCORE_275(275, 55, 64, 2000, "scoringC.au"),
  SCORE_285(285, 39, 54, 1500, "scoringD.au"),
  SCORE_295(295, 55, 64, 6500, "scoringD.au");

  public final int scoreValue;
  public final int minAge;
  public final int maxAge;
  public final int baseIncomeValue;
  public final String reportName;

  protected static final Properties scoringProperties = new Properties();
  private static final String SCORING_PATH = "scoringAU.properties";
  private static final Logger LOG = LogManager.getLogger(ScoreAU.class);

  ScoreAU(
      final int scoreValue, final int minAge, final int maxAge, final int baseIncomeValue,
      final String reportName) {
    this.scoreValue = scoreValue;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.baseIncomeValue = baseIncomeValue;
    this.reportName = reportName;
  }

  public static void loadScoringProperties() {
    try {
      scoringProperties.load(Credit24AU.class.getClassLoader().getResourceAsStream(SCORING_PATH));
      LOG.info("Loaded scoring from file: {}", SCORING_PATH);
    } catch (final IOException e) {
      LOG.error("Unable to read scoring from file: {}", SCORING_PATH);
    }
  }

  public Properties getScoringProperties() {
    return scoringProperties;
  }

  public static ScoreAU getScoreAUForScoreValue(final int scoreValue) {
    for (final ScoreAU scoreAU : ScoreAU.values())
      if (scoreAU.scoreValue == scoreValue)
        return scoreAU;
    return null;
  }
}
