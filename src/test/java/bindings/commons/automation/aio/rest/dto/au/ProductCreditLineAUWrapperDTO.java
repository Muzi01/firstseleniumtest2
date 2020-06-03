package bindings.commons.automation.aio.rest.dto.au;


import java.util.ArrayList;
import java.util.List;

public class ProductCreditLineAUWrapperDTO {

  public List<ProductCreditLineAU> products = new ArrayList<>();
  public String paymentInterval;
  public int maximumLimit;
}
