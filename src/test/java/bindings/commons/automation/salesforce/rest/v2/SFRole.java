package bindings.commons.automation.salesforce.rest.v2;

public class SFRole {

  private final String clientIdKey;
  private final String clientSecretKey;
  private final String usernameKey;
  private final String passKey;

  public SFRole(final String clientIdKey, final String clientSecretKey, final String usernameKey,
      final String passKey) {
    this.clientIdKey = clientIdKey;
    this.clientSecretKey = clientSecretKey;
    this.usernameKey = usernameKey;
    this.passKey = passKey;
  }

  public String getClientIdKey() {
    return this.clientIdKey;
  }

  public String getClientSecretKey() {
    return this.clientSecretKey;
  }

  public String getUsernameKey() {
    return this.usernameKey;
  }

  public String getPassKey() {
    return this.passKey;
  }
}
