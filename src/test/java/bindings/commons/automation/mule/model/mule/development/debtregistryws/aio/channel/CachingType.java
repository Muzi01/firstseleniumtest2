
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for CachingType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="CachingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NORMAL"/>
 *     &lt;enumeration value="IGNORE_CACHING"/>
 *     &lt;enumeration value="CACHE_ONLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CachingType", namespace = "http://ipfdigital.com/CDM/DebtRegistry")
@XmlEnum
public enum CachingType {

  NORMAL,
  IGNORE_CACHING,
  CACHE_ONLY;

  public String value() {
    return name();
  }

  public static CachingType fromValue(final String v) {
    return valueOf(v);
  }

}
