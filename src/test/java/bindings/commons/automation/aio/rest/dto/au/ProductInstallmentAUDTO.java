package bindings.commons.automation.aio.rest.dto.au;

import java.util.ArrayList;
import java.util.List;

public class ProductInstallmentAUDTO {
  public int loanAmount;
  public List<ProductOffersAUDTO> productOffers = new ArrayList<>();
}
