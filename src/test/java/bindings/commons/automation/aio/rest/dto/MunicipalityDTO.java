package bindings.commons.automation.aio.rest.dto;

class MunicipalityDTO {
  public String province;
  public String name;

  @Override
  public int hashCode() {
    return (this.province + this.name).hashCode();
  }

  @Override
  public boolean equals(final Object o) {
    return (this.province + this.name).equals(o);
  }
}
