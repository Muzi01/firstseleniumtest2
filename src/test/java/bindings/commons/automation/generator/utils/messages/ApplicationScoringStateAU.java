package bindings.commons.automation.generator.utils.messages;

public enum ApplicationScoringStateAU {
  BANK_SCRAPING_DONE_STATUS("BANK_SCRAPING_DONE"),
  SCORING_PROCESS_STARTED_STATUS("SCORING_PROCESS_STARTED"),
  DECISION_ENGINE_SUCCESS_STATUS("DECISION_ENGINE_SUCCESS"),
  DECISION_ENGINE_NEGATIVE_STATUS("DECISION_ENGINE_REJECTION"),
  DECISION_ENGINE_FINANCIAL_DATA_STATUS("FINANCIAL_DATA_DONE"),
  DECISION_ENGINE_REQUEST_SENT_STATUS("DECISION_ENGINE_REQUEST_SENT"),
  VEDA_ERROR("veda_error"),
  DB_ERROR("db_error");

  private final String state;

  ApplicationScoringStateAU(final String state) {
    this.state = state;
  }

  public String getState() {
    return this.state;
  }

}
