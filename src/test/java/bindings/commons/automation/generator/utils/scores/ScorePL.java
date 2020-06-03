package bindings.commons.automation.generator.utils.scores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum ScorePL {

  AA(10, 6, 1, 0, 5, Constants.PAST_DUE_BELOW_30_DAYS, 76, 310, 360),
  A(10, 0, 1, 0, 3, Constants.PAST_DUE_BELOW_30_DAYS, 61, 299, 309),
  B(0, 0, 1, 0, 0, Constants.PAST_DUE_BELOW_30_DAYS, 45, 281, 298),
  C(9, 0, 0, 0, 0, Constants.PAST_DUE_BELOW_30_DAYS, 27, 265, 280),
  D(9, 0, 0, 301, 3, Constants.PAST_DUE_BELOW_30_DAYS, 19, 258, 264),
  E(9, 0, 0, 301, 2, Constants.PAST_DUE_BELOW_30_DAYS, 14, 252, 257),
  F(4, 0, 0, 301, 0, Constants.PAST_DUE_BELOW_30_DAYS, 7, 245, 251),
  G(4, 0, 0, 200, 0, Constants.PAST_DUE_BELOW_90_DAYS, 0, 238, 244),
  H(4, 0, 0, 301, 0, Constants.PAST_DUE_BELOW_90_DAYS, -8, 230, 237);

  private final int bik4001;
  private final int bik4015;
  private final int psn4016;
  private final int bik4019;
  private final int bik4029;
  private final String bik4034;
  public final int scoreValue;
  public final int minimalScoreValue;
  public final int maximumScoreValue;

  ScorePL(final int bik4001, final int bik4015, final int psn4016, final int bik4019,
      final int bik4029, final String bik4034, final int scoreValue, final int minimalScoreValue,
      final int maximumScoreValue) {
    this.bik4001 = bik4001;
    this.bik4015 = bik4015;
    this.psn4016 = psn4016;
    this.bik4019 = bik4019;
    this.bik4029 = bik4029;
    this.bik4034 = bik4034;
    this.scoreValue = scoreValue;
    this.minimalScoreValue = minimalScoreValue;
    this.maximumScoreValue = maximumScoreValue;
  }

  private static final Map<String, String> messages = new HashMap<>();
  private static final Map<String, String> updateMessages = new HashMap<>();
  private static final Properties scoringProperties = new Properties();
  private static final String SCORING_PATH = "scoringPL.properties";
  private static final Logger LOG = LogManager.getLogger(ScorePL.class);


  public String getUpdateMessage(final MonitorsPL monitorsPL) {
    return updateMessages.get(monitorsPL.toString());
  }

  public static Map<String, String> getUpdateMessage() {
    return updateMessages;
  }

  public String getMessage(final CreditBureausPL creditBureausPL) {
    return messages.get(creditBureausPL.getName())
        .replace("%bik4001%", Integer.toString(bik4001))
        .replace("%bik4015%", Integer.toString(bik4015))
        .replace("%psn4015%", "0")
        .replace("%bik4016%", "0")
        .replace("%psn4016%", Integer.toString(psn4016))
        .replace("%bik4018%", "0")
        .replace("%psn4018%", "0")
        .replace("%bik4019%", Integer.toString(bik4019))
        .replace("%psn4019%", "0")
        .replace("%bik4029%", Integer.toString(bik4029))
        .replace("%psn4029%", "0")
        .replace("%bik4034%", bik4034)
        .replace("%psn4034%", Constants.PAST_DUE_BELOW_30_DAYS);
  }

  public static ScorePL getTierByScore(final int scoreValue) {
    return Arrays.stream(ScorePL.values())
        .filter(scorePL -> scorePL.minimalScoreValue <= scoreValue)
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("Not found tier for scoring " + scoreValue));
  }

  public String getMessageWithReplacedNipAndRegon(
      final CreditBureausForCompaniesPL creditBureausForCompaniesPL, final String nip,
      final String regon) {
    return messages.get(creditBureausForCompaniesPL.getName())
        .replace("PL_NIP_VALUE", nip)
        .replace("PL_REGON_VALUE", regon);
  }

  static {

    try {
      scoringProperties.load(ScorePL.class.getClassLoader().getResourceAsStream(SCORING_PATH));
      LOG.info("Loaded scoring from file: {}", SCORING_PATH);
    } catch (final IOException | NullPointerException e) {
      LOG.error("Unable to read scoring from file: {}", SCORING_PATH);
    }

    messages.put(CreditBureausPL.DELTA_VISTA.getName(),
        scoringProperties.getProperty("scoring.pl.delta_vista"));
    messages.put(CreditBureausPL.GDS_ERIF.getName(),
        scoringProperties.getProperty("scoring.pl.gds_erif"));
    messages.put(CreditBureausPL.GDS_KRD.getName(),
        scoringProperties.getProperty("scoring.pl.gds_krd"));
    messages.put(CreditBureausPL.GDS_IM.getName(),
        scoringProperties.getProperty("scoring.pl.gds_im"));
    messages.put(CreditBureausPL.GDS_BIK.getName(),
        scoringProperties.getProperty("scoring.pl.gds_bik"));
    messages.put(CreditBureausPL.GDS_OFFER.getName(),
        scoringProperties.getProperty("scoring.pl.gds_offer"));
    updateMessages.put(MonitorsPL.GDS_M_BIK.toString(),
        scoringProperties.getProperty("scoring.pl.gds_m_bik"));
    updateMessages.put(MonitorsPL.GDS_M_OFFER.toString(),
        scoringProperties.getProperty("scoring.pl.gds_m_offer"));
    updateMessages.put(MonitorsPL.VCS.toString(),
        scoringProperties.getProperty("scoring.pl.gds.vcs"));
    messages.put(CreditBureausForCompaniesPL.DELTA_VISTA_COMPANY_BASIC.getName(),
        scoringProperties.getProperty("scoring.pl.delta_vista_company"));
  }

  private static class Constants {
    static final String PAST_DUE_BELOW_30_DAYS = "pastDueBelow30Days";
    static final String PAST_DUE_BELOW_90_DAYS = "pastDueBelow90Days";
  }
}
