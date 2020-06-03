
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for CountryType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="CountryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ES"/>
 *     &lt;enumeration value="FI"/>
 *     &lt;enumeration value="PL"/>
 *     &lt;enumeration value="LV"/>
 *     &lt;enumeration value="LT"/>
 *     &lt;enumeration value="MX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CountryType")
@XmlEnum
public enum CountryType {

  ES,
  FI,
  PL,
  LV,
  LT,
  MX;

  public String value() {
    return name();
  }

  public static CountryType fromValue(final String v) {
    return valueOf(v);
  }

}
