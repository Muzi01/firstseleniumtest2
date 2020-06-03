package bindings.commons.automation.aio.rest.dto.au;

import java.util.ArrayList;
import java.util.List;

public class ProductCreditLineAU {
  public long productId;
  public int creditAmount;
  public List<WithdrawDTO> withdraws = new ArrayList<>();
}
