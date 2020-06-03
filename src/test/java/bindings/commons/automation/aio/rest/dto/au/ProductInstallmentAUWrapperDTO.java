package bindings.commons.automation.aio.rest.dto.au;


import java.util.ArrayList;
import java.util.List;

public class ProductInstallmentAUWrapperDTO {

  public List<ProductInstallmentAUDTO> products = new ArrayList<>();
  public String paymentInterval;
  public int maximumAmount;
}
