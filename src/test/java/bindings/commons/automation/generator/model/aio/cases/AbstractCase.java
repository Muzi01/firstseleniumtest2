package bindings.commons.automation.generator.model.aio.cases;

import java.sql.Date;
import java.sql.Timestamp;

public class AbstractCase {
  public Long id;
  public Long entityVersion;
  public Date closeStamp;
  public String closedById;
  public String description;
  public Date openStamp;
  public CaseState state;
  public Timestamp updated;
}
