package bindings.commons.automation.mule.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
  @Override
  public LocalDate unmarshal(final String date) {
    return LocalDate.parse(date);
  }

  @Override
  public String marshal(final LocalDate date) {
    if (date != null) {
      return date.toString();
    } else {
      return null;
    }
  }
}
