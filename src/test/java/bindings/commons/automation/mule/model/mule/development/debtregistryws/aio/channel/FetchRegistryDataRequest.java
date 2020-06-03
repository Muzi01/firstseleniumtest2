
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="Body">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="customerPersonalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="country" type="{http://ipfdigital.com/Mule/DebtRegistry/Channel}CountryType"/>
 *                   &lt;element name="agency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="debug" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="test" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="caching" type="{http://ipfdigital.com/CDM/DebtRegistry}CachingType"/>
 *                   &lt;element name="extras">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="extra" type="{http://ipfdigital.com/CDM/DebtRegistry}ExtraType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
@XmlType(name = "", propOrder = {
    "body"
})
@XmlRootElement(name = "FetchRegistryDataRequest")
public class FetchRegistryDataRequest {

  @XmlElement(name = "Body", required = true)
  protected Body body;

  /**
   * Gets the value of the body property.
   * 
   * @return possible object is {@link Body }
   * 
   */
  public Body getBody() {
    return this.body;
  }

  /**
   * Sets the value of the body property.
   * 
   * @param value allowed object is {@link Body }
   * 
   */
  public void setBody(final Body value) {
    this.body = value;
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
   *         &lt;element name="customerPersonalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="country" type="{http://ipfdigital.com/Mule/DebtRegistry/Channel}CountryType"/>
   *         &lt;element name="agency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="debug" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
   *         &lt;element name="test" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
   *         &lt;element name="caching" type="{http://ipfdigital.com/CDM/DebtRegistry}CachingType"/>
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
  @XmlType(name = "", propOrder = {
      "customerPersonalId",
      "country",
      "agency",
      "scope",
      "debug",
      "test",
      "caching",
      "extras"
  })
  public static class Body {

    @XmlElement(required = true)
    protected String customerPersonalId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CountryType country;
    protected String agency;
    protected String scope;
    protected Boolean debug;
    protected Boolean test;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CachingType caching;
    @XmlElement(required = true)
    protected Extras extras;

    /**
     * Gets the value of the customerPersonalId property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCustomerPersonalId() {
      return this.customerPersonalId;
    }

    /**
     * Sets the value of the customerPersonalId property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCustomerPersonalId(final String value) {
      this.customerPersonalId = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return possible object is {@link CountryType }
     * 
     */
    public CountryType getCountry() {
      return this.country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value allowed object is {@link CountryType }
     * 
     */
    public void setCountry(final CountryType value) {
      this.country = value;
    }

    /**
     * Gets the value of the agency property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAgency() {
      return this.agency;
    }

    /**
     * Sets the value of the agency property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setAgency(final String value) {
      this.agency = value;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getScope() {
      return this.scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setScope(final String value) {
      this.scope = value;
    }

    /**
     * Gets the value of the debug property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isDebug() {
      return this.debug;
    }

    /**
     * Sets the value of the debug property.
     * 
     * @param value allowed object is {@link Boolean }
     * 
     */
    public void setDebug(final Boolean value) {
      this.debug = value;
    }

    /**
     * Gets the value of the test property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isTest() {
      return this.test;
    }

    /**
     * Sets the value of the test property.
     * 
     * @param value allowed object is {@link Boolean }
     * 
     */
    public void setTest(final Boolean value) {
      this.test = value;
    }

    /**
     * Gets the value of the caching property.
     * 
     * @return possible object is {@link CachingType }
     * 
     */
    public CachingType getCaching() {
      return this.caching;
    }

    /**
     * Sets the value of the caching property.
     * 
     * @param value allowed object is {@link CachingType }
     * 
     */
    public void setCaching(final CachingType value) {
      this.caching = value;
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

}
