package bindings.commons.automation.aio.soap;

import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.JAXBElement;

public class XMLToStringStyle extends RecursiveToStringStyle {

  public static final ToStringStyle XML_READ_STYLE = new XMLToStringStyle();

  private XMLToStringStyle() {
    this.setUseShortClassName(true);
    this.setUseIdentityHashCode(false);
  }

  @Override
  public void append(
      final StringBuffer buffer, final String fieldName, final Object value,
      final Boolean fullDetail) {
    if (value instanceof JAXBElement) {
      super.append(buffer, fieldName, ((JAXBElement) value).getValue(), fullDetail);
    } else {
      super.append(buffer, fieldName, value, fullDetail);
    }
  }
}
