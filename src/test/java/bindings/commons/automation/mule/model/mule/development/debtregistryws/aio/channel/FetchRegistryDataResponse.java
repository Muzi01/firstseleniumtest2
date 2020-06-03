
package bindings.commons.automation.mule.model.mule.development.debtregistryws.aio.channel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="ResponseStatus" type="{http://ipfdigital.com/Mule/DebtRegistry/Channel}StatusResponseType"/>
 *         &lt;element name="Body">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="debtRegistry" type="{http://ipfdigital.com/CDM/DebtRegistry}DebtRegistryDataType"/>
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
    "responseStatus",
    "body"
})
@XmlRootElement(name = "FetchRegistryDataResponse")
public class FetchRegistryDataResponse {

  @XmlElement(name = "ResponseStatus", required = true)
  protected StatusResponseType responseStatus;
  @XmlElement(name = "Body", required = true)
  protected Body body;

  /**
   * Gets the value of the responseStatus property.
   * 
   * @return possible object is {@link StatusResponseType }
   * 
   */
  public StatusResponseType getResponseStatus() {
    return this.responseStatus;
  }

  /**
   * Sets the value of the responseStatus property.
   * 
   * @param value allowed object is {@link StatusResponseType }
   * 
   */
  public void setResponseStatus(final StatusResponseType value) {
    this.responseStatus = value;
  }

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
   *         &lt;element name="debtRegistry" type="{http://ipfdigital.com/CDM/DebtRegistry}DebtRegistryDataType"/>
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
      "debtRegistry"
  })
  public static class Body {

    @XmlElement(required = true)
    protected DebtRegistryDataType debtRegistry;

    /**
     * Gets the value of the debtRegistry property.
     * 
     * @return possible object is {@link DebtRegistryDataType }
     * 
     */
    public DebtRegistryDataType getDebtRegistry() {
      return this.debtRegistry;
    }

    /**
     * Sets the value of the debtRegistry property.
     * 
     * @param value allowed object is {@link DebtRegistryDataType }
     * 
     */
    public void setDebtRegistry(final DebtRegistryDataType value) {
      this.debtRegistry = value;
    }

  }

}
