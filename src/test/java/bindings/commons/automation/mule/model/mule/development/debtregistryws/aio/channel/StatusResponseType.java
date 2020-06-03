
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for StatusResponseType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusResponseType", propOrder = {
    "success",
    "reason"
})
public class StatusResponseType {

  @XmlElement(name = "Success")
  protected boolean success;
  @XmlElement(name = "Reason")
  protected String reason;

  /**
   * Gets the value of the success property.
   * 
   */
  public boolean isSuccess() {
    return this.success;
  }

  /**
   * Sets the value of the success property.
   * 
   */
  public void setSuccess(final boolean value) {
    this.success = value;
  }

  /**
   * Gets the value of the reason property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getReason() {
    return this.reason;
  }

  /**
   * Sets the value of the reason property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setReason(final String value) {
    this.reason = value;
  }

}
