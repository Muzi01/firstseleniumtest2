package bindings.commons.automation.aio.rest.v2;

class NoAddSessionIdItem {
  private final String uri;
  private final String method;

  public NoAddSessionIdItem(final String uri, final String method) {
    this.uri = uri;
    this.method = method;
  }

  public String getUri() {
    return this.uri;
  }

  public String getMethod() {
    return this.method;
  }

}
