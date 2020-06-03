package bindings.commons.automation.config.server.dto;


import java.util.List;

public final class ConfigServerPropertiesDTO {
  private String name;
  private String label;
  private Integer version;
  private String state;
  private List<String> profiles;
  private List<PropertySourceDTO> propertySources;

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(final String label) {
    this.label = label;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

  public String getState() {
    return this.state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public List<String> getProfiles() {
    return this.profiles;
  }

  public void setProfiles(final List<String> profiles) {
    this.profiles = profiles;
  }

  public List<PropertySourceDTO> getPropertySources() {
    return this.propertySources;
  }

  public void setPropertySources(final List<PropertySourceDTO> propertySources) {
    this.propertySources = propertySources;
  }
}
