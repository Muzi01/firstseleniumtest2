package bindings.commons.automation.customer;

public enum ContextOfGeneratorUse {

  WEB("_g_"),
  AUTOMATION_TEST("_a_");

  public final String emailSuffix;

  ContextOfGeneratorUse(final String emailSuffix) {
    this.emailSuffix = emailSuffix;
  }
}
