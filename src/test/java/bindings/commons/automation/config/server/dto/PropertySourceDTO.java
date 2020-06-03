package bindings.commons.automation.config.server.dto;

import java.util.Map;

public final class PropertySourceDTO {
  private String name;
  private Map<String, String> source;

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Map<String, String> getSource() {
    return this.source;
  }

  public void setSource(final Map<String, String> source) {
    this.source = source;
  }
}
