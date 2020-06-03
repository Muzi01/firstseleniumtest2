package bindings.commons.automation.customer;

public enum State {

  NSW("New South Wales"),
  QLD("Queensland"),
  SA("South Australia"),
  TAS("Tasmania"),
  VIC("Victoria"),
  WA("Western Australia"),
  ACT("Australian Capital Territory"),
  NT("Northern Territory");

  public final String stateName;

  State(final String stateName) {
    this.stateName = stateName;
  }
}
