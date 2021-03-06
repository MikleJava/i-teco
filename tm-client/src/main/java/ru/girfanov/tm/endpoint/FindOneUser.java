
package ru.girfanov.tm.endpoint;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findOneUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findOneUser"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="session" type="{http://endpoint.tm.girfanov.ru/}sessionDto" minOccurs="0"/&gt;
 *         &lt;element name="userUuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findOneUser", propOrder = {
    "session",
    "userUuid"
})
public class FindOneUser {

    protected SessionDto session;
    protected String userUuid;

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDto }
     *     
     */
    public SessionDto getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDto }
     *     
     */
    public void setSession(SessionDto value) {
        this.session = value;
    }

    /**
     * Gets the value of the userUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * Sets the value of the userUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserUuid(String value) {
        this.userUuid = value;
    }

}
