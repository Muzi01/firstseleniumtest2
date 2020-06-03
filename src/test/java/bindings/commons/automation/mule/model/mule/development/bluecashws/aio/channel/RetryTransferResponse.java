//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2018.03.06 at 03:53:27 PM CET
//


package bindings.commons.automation.mule.model.mule.development.bluecashws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for RetryTransferResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetryTransferResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ResultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransferAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetryTransferResponse", propOrder = {
    "success",
    "resultCode",
    "transferAppId",
    "transferId"
})
public class RetryTransferResponse {

  @XmlElement(name = "Success")
  protected boolean success;
  @XmlElement(name = "ResultCode")
  protected String resultCode;
  @XmlElement(name = "TransferAppId", required = true)
  protected String transferAppId;
  @XmlElement(name = "TransferId")
  protected String transferId;

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
   * Gets the value of the resultCode property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getResultCode() {
    return this.resultCode;
  }

  /**
   * Sets the value of the resultCode property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setResultCode(final String value) {
    this.resultCode = value;
  }

  /**
   * Gets the value of the transferAppId property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTransferAppId() {
    return this.transferAppId;
  }

  /**
   * Sets the value of the transferAppId property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTransferAppId(final String value) {
    this.transferAppId = value;
  }

  /**
   * Gets the value of the transferId property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTransferId() {
    return this.transferId;
  }

  /**
   * Sets the value of the transferId property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setTransferId(final String value) {
    this.transferId = value;
  }

}
