//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2018.03.23 at 04:21:53 PM CET
//


package bindings.commons.automation.mule.model.mule.development.documents.aio.channel;

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
 * &lt;complexType name="StatusResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
