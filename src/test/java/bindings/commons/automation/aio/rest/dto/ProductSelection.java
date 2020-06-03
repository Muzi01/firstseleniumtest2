package bindings.commons.automation.aio.rest.dto;

public class ProductSelection {

  public Long id;
  public Integer firstDrawAmount;

  public ProductSelection(final Long id, final Integer firstDrawAmount) {
    this.id = id;
    this.firstDrawAmount = firstDrawAmount;
  }
}
