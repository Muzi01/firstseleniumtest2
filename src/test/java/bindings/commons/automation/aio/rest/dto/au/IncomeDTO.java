
package bindings.commons.automation.aio.rest.dto.au;

import java.util.ArrayList;
import java.util.List;

public class IncomeDTO {
  public Integer amount;
  public String frequency;
  public List<Integer> nextPayDay = new ArrayList<>();
}
