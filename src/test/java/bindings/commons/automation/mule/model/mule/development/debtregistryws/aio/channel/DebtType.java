
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>
 * Java class for DebtType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DebtType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ammount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DebtType", namespace = "http://ipfdigital.com/CDM/DebtRegistry", propOrder = {
    "key",
    "ammount",
    "source",
    "startDate",
    "endDate"
})
public class DebtType {

  @XmlElement(required = true)
  protected String key;
  @XmlElement(required = true)
  protected BigDecimal ammount;
  @XmlElement(required = true)
  protected String source;
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar startDate;
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar endDate;

  /**
   * Gets the value of the key property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getKey() {
    return this.key;
  }

  /**
   * Sets the value of the key property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setKey(final String value) {
    this.key = value;
  }

  /**
   * Gets the value of the ammount property.
   * 
   * @return possible object is {@link BigDecimal }
   * 
   */
  public BigDecimal getAmmount() {
    return this.ammount;
  }

  /**
   * Sets the value of the ammount property.
   * 
   * @param value allowed object is {@link BigDecimal }
   * 
   */
  public void setAmmount(final BigDecimal value) {
    this.ammount = value;
  }

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getSource() {
    return this.source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setSource(final String value) {
    this.source = value;
  }

  /**
   * Gets the value of the startDate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getStartDate() {
    return this.startDate;
  }

  /**
   * Sets the value of the startDate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setStartDate(final XMLGregorianCalendar value) {
    this.startDate = value;
  }

  /**
   * Gets the value of the endDate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getEndDate() {
    return this.endDate;
  }

  /**
   * Sets the value of the endDate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setEndDate(final XMLGregorianCalendar value) {
    this.endDate = value;
  }

}
