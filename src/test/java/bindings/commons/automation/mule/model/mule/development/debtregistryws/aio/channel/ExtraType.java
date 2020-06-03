
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Java class for ExtraType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="valueBoolean" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="valueDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *           &lt;element name="valueDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="valueTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *           &lt;element name="valueDecimal" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *           &lt;element name="valueInt" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="valueString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="valueExtra" type="{http://ipfdigital.com/CDM/DebtRegistry}ExtraType" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtraType", namespace = "http://ipfdigital.com/CDM/DebtRegistry", propOrder = {
    "key",
    "valueBoolean",
    "valueDate",
    "valueDateTime",
    "valueTime",
    "valueDecimal",
    "valueInt",
    "valueString",
    "valueExtra"
})
public class ExtraType {

  @XmlElement(required = true)
  protected String key;
  protected Boolean valueBoolean;
  @XmlSchemaType(name = "date")
  protected XMLGregorianCalendar valueDate;
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar valueDateTime;
  @XmlSchemaType(name = "time")
  protected XMLGregorianCalendar valueTime;
  protected BigDecimal valueDecimal;
  protected Integer valueInt;
  protected String valueString;
  protected List<ExtraType> valueExtra;

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
   * Gets the value of the valueBoolean property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isValueBoolean() {
    return this.valueBoolean;
  }

  /**
   * Sets the value of the valueBoolean property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setValueBoolean(final Boolean value) {
    this.valueBoolean = value;
  }

  /**
   * Gets the value of the valueDate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getValueDate() {
    return this.valueDate;
  }

  /**
   * Sets the value of the valueDate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setValueDate(final XMLGregorianCalendar value) {
    this.valueDate = value;
  }

  /**
   * Gets the value of the valueDateTime property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getValueDateTime() {
    return this.valueDateTime;
  }

  /**
   * Sets the value of the valueDateTime property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setValueDateTime(final XMLGregorianCalendar value) {
    this.valueDateTime = value;
  }

  /**
   * Gets the value of the valueTime property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getValueTime() {
    return this.valueTime;
  }

  /**
   * Sets the value of the valueTime property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setValueTime(final XMLGregorianCalendar value) {
    this.valueTime = value;
  }

  /**
   * Gets the value of the valueDecimal property.
   * 
   * @return possible object is {@link BigDecimal }
   * 
   */
  public BigDecimal getValueDecimal() {
    return this.valueDecimal;
  }

  /**
   * Sets the value of the valueDecimal property.
   * 
   * @param value allowed object is {@link BigDecimal }
   * 
   */
  public void setValueDecimal(final BigDecimal value) {
    this.valueDecimal = value;
  }

  /**
   * Gets the value of the valueInt property.
   * 
   * @return possible object is {@link Integer }
   * 
   */
  public Integer getValueInt() {
    return this.valueInt;
  }

  /**
   * Sets the value of the valueInt property.
   * 
   * @param value allowed object is {@link Integer }
   * 
   */
  public void setValueInt(final Integer value) {
    this.valueInt = value;
  }

  /**
   * Gets the value of the valueString property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getValueString() {
    return this.valueString;
  }

  /**
   * Sets the value of the valueString property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setValueString(final String value) {
    this.valueString = value;
  }

  /**
   * Gets the value of the valueExtra property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the valueExtra property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getValueExtra().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link ExtraType }
   * 
   * 
   */
  public List<ExtraType> getValueExtra() {
    if (this.valueExtra == null) {
      this.valueExtra = new ArrayList<>();
    }
    return this.valueExtra;
  }

}
