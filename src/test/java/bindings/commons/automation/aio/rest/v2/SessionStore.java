package bindings.commons.automation.aio.rest.v2;

import org.apache.commons.lang3.StringUtils;

public class SessionStore {
  private String sessionId;

  public String getSessionId() {
    return this.sessionId;
  }

  public void updateSessionId(final String newSessionId) {
    if (StringUtils.isBlank(this.sessionId)) {
      this.sessionId = newSessionId;
    }
  }

  public boolean isSessionIdAvailable() {
    return StringUtils.isNotBlank(this.sessionId);
  }

  public void clear() {
    this.sessionId = StringUtils.EMPTY;
  }
}
