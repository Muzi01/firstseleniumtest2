//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2018.03.05 at 10:43:19 AM CET
//


package bindings.commons.automation.mule.model.mule.development.bluecashws.bm.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransferAppId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TransferId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BlueCashTransferAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="BlueCashTransferCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BlueCashTransferStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="BlueCashTransferStatusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="BlueCashTransferId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Fee" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transferAppId",
    "transferId",
    "blueCashTransferAmount",
    "blueCashTransferCurrency",
    "blueCashTransferStatus",
    "blueCashTransferStatusDate",
    "blueCashTransferId",
    "fee",
    "hash"
})
@XmlRootElement(name = "BlueCashTransferStatusReq")
public class BlueCashTransferStatusReq {

  @XmlElement(name = "TransferAppId", required = true)
  protected String transferAppId;
  @XmlElement(name = "TransferId", required = true)
  protected String transferId;
  @XmlElement(name = "BlueCashTransferAmount", required = true)
  protected BigDecimal blueCashTransferAmount;
  @XmlElement(name = "BlueCashTransferCurrency", required = true)
  protected String blueCashTransferCurrency;
  @XmlElement(name = "BlueCashTransferStatus")
  protected int blueCashTransferStatus;
  @XmlElement(name = "BlueCashTransferStatusDate", required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar blueCashTransferStatusDate;
  @XmlElement(name = "BlueCashTransferId", required = true)
  protected String blueCashTransferId;
  @XmlElement(name = "Fee")
  protected int fee;
  @XmlElement(name = "Hash", required = true)
  protected String hash;

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
   * Gets the value of the blueCashTransferAmount property.
   * 
   * @return possible object is {@link BigDecimal }
   * 
   */
  public BigDecimal getBlueCashTransferAmount() {
    return this.blueCashTransferAmount;
  }

  /**
   * Sets the value of the blueCashTransferAmount property.
   * 
   * @param value allowed object is {@link BigDecimal }
   * 
   */
  public void setBlueCashTransferAmount(final BigDecimal value) {
    this.blueCashTransferAmount = value;
  }

  /**
   * Gets the value of the blueCashTransferCurrency property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getBlueCashTransferCurrency() {
    return this.blueCashTransferCurrency;
  }

  /**
   * Sets the value of the blueCashTransferCurrency property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setBlueCashTransferCurrency(final String value) {
    this.blueCashTransferCurrency = value;
  }

  /**
   * Gets the value of the blueCashTransferStatus property.
   * 
   */
  public int getBlueCashTransferStatus() {
    return this.blueCashTransferStatus;
  }

  /**
   * Sets the value of the blueCashTransferStatus property.
   * 
   */
  public void setBlueCashTransferStatus(final int value) {
    this.blueCashTransferStatus = value;
  }

  /**
   * Gets the value of the blueCashTransferStatusDate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getBlueCashTransferStatusDate() {
    return this.blueCashTransferStatusDate;
  }

  /**
   * Sets the value of the blueCashTransferStatusDate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setBlueCashTransferStatusDate(final XMLGregorianCalendar value) {
    this.blueCashTransferStatusDate = value;
  }

  /**
   * Gets the value of the blueCashTransferId property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getBlueCashTransferId() {
    return this.blueCashTransferId;
  }

  /**
   * Sets the value of the blueCashTransferId property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setBlueCashTransferId(final String value) {
    this.blueCashTransferId = value;
  }

  /**
   * Gets the value of the fee property.
   * 
   */
  public int getFee() {
    return this.fee;
  }

  /**
   * Sets the value of the fee property.
   * 
   */
  public void setFee(final int value) {
    this.fee = value;
  }

  /**
   * Gets the value of the hash property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getHash() {
    return this.hash;
  }

  /**
   * Sets the value of the hash property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setHash(final String value) {
    this.hash = value;
  }



}
