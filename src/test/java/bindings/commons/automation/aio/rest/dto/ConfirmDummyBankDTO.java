package bindings.commons.automation.aio.rest.dto;

import feign.form.FormProperty;

public class ConfirmDummyBankDTO {
  @FormProperty("callback")
  public String callback;

  @FormProperty("t")
  public String transactionId;

  @FormProperty("TID")
  public String tid;

  @FormProperty("xc")
  public String country;

  @FormProperty("xl")
  public String language;

  @FormProperty("xb")
  public String brand;

  @FormProperty("CSSN")
  public String cssn;

  @FormProperty("SSN")
  public String ssn;
}
