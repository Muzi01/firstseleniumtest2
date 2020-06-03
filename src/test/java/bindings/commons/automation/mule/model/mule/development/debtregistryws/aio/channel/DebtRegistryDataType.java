
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Java class for DebtRegistryDataType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DebtRegistryDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fetchDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="debts">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="debt" type="{http://ipfdigital.com/CDM/DebtRegistry}DebtType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="extras">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="extra" type="{http://ipfdigital.com/CDM/DebtRegistry}ExtraType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DebtRegistryDataType", namespace = "http://ipfdigital.com/CDM/DebtRegistry",
    propOrder = {
        "fetchDate",
        "debts",
        "extras"
    })
public class DebtRegistryDataType {

  @XmlElement(required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar fetchDate;
  @XmlElement(required = true)
  protected Debts debts;
  @XmlElement(required = true)
  protected Extras extras;

  /**
   * Gets the value of the fetchDate property.
   * 
   * @return possible object is {@link XMLGregorianCalendar }
   * 
   */
  public XMLGregorianCalendar getFetchDate() {
    return this.fetchDate;
  }

  /**
   * Sets the value of the fetchDate property.
   * 
   * @param value allowed object is {@link XMLGregorianCalendar }
   * 
   */
  public void setFetchDate(final XMLGregorianCalendar value) {
    this.fetchDate = value;
  }

  /**
   * Gets the value of the debts property.
   * 
   * @return possible object is {@link Debts }
   * 
   */
  public Debts getDebts() {
    return this.debts;
  }

  /**
   * Sets the value of the debts property.
   * 
   * @param value allowed object is {@link Debts }
   * 
   */
  public void setDebts(final Debts value) {
    this.debts = value;
  }

  /**
   * Gets the value of the extras property.
   * 
   * @return possible object is {@link Extras }
   * 
   */
  public Extras getExtras() {
    return this.extras;
  }

  /**
   * Sets the value of the extras property.
   * 
   * @param value allowed object is {@link Extras }
   * 
   */
  public void setExtras(final Extras value) {
    this.extras = value;
  }


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="debt" type="{http://ipfdigital.com/CDM/DebtRegistry}DebtType" maxOccurs="unbounded" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "debt"
  })
  public static class Debts {

    protected List<DebtType> debt;

    /**
     * Gets the value of the debt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the debt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDebt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link DebtType }
     * 
     * 
     */
    public List<DebtType> getDebt() {
      if (this.debt == null) {
        this.debt = new ArrayList<>();
      }
      return this.debt;
    }

  }


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="extra" type="{http://ipfdigital.com/CDM/DebtRegistry}ExtraType" maxOccurs="unbounded" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "extra"
  })
  public static class Extras {

    protected List<ExtraType> extra;

    /**
     * Gets the value of the extra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the extra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getExtra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link ExtraType }
     * 
     * 
     */
    public List<ExtraType> getExtra() {
      if (this.extra == null) {
        this.extra = new ArrayList<>();
      }
      return this.extra;
    }

  }

}
