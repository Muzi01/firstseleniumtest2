package bindings.commons.automation.aio.rest.dto;

public class ReferencePersonDTO {

  public String name;
  public String relation;
  public String telephoneNumber;

  public ReferencePersonDTO(final String name, final String relation,
      final String telephoneNumber) {
    this.name = name;
    this.relation = relation;
    this.telephoneNumber = telephoneNumber;
  }
}
