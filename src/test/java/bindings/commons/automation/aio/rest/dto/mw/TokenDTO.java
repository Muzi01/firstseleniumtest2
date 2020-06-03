package bindings.commons.automation.aio.rest.dto.mw;

public class TokenDTO {
  public Boolean success;
  public Result result;

  public class Result {
    public String token;
    public String expDate;
  }
}
