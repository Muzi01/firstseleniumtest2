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
import java.math.BigDecimal;


/**
 * <p>
 * Java class for RetryTransfer complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetryTransfer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransferAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransferId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransferAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransferStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetryTransfer", propOrder = {
    "transferAppId",
    "transferId",
    "transferAmount",
    "currency",
    "transferStatus"
})
public class RetryTransfer {

  @XmlElement(name = "TransferAppId", required = true)
  protected String transferAppId;
  @XmlElement(name = "TransferId", required = true)
  protected String transferId;
  @XmlElement(name = "TransferAmount", required = true)
  protected BigDecimal transferAmount;
  @XmlElement(name = "Currency", required = true)
  protected String currency;
  @XmlElement(name = "TransferStatus")
  protected int transferStatus;

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

  /**
   * Gets the value of the transferAmount property.
   * 
   * @return possible object is {@link BigDecimal }
   * 
   */
  public BigDecimal getTransferAmount() {
    return this.transferAmount;
  }

  /**
   * Sets the value of the transferAmount property.
   * 
   * @param value allowed object is {@link BigDecimal }
   * 
   */
  public void setTransferAmount(final BigDecimal value) {
    this.transferAmount = value;
  }

  /**
   * Gets the value of the currency property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCurrency() {
    return this.currency;
  }

  /**
   * Sets the value of the currency property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCurrency(final String value) {
    this.currency = value;
  }

  /**
   * Gets the value of the transferStatus property.
   * 
   */
  public int getTransferStatus() {
    return this.transferStatus;
  }

  /**
   * Sets the value of the transferStatus property.
   * 
   */
  public void setTransferStatus(final int value) {
    this.transferStatus = value;
  }

}
