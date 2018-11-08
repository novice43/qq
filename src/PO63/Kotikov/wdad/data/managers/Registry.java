
package PO63.Kotikov.wdad.data.managers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createregistry",
    "registryaddress",
    "registryport"
})
@XmlRootElement(name = "registry")
public class Registry {

    @XmlElement(required = true)
    private String createregistry;
    @XmlElement(required = true)
    private String registryaddress;
    @XmlElement(required = true)
    private String registryport;

    /**
     * Gets the value of the createregistry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateregistry() {
        return createregistry;
    }

    /**
     * Sets the value of the createregistry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateregistry(String value) {
        this.createregistry = value;
    }

    /**
     * Gets the value of the registryaddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryaddress() {
        return registryaddress;
    }

    /**
     * Sets the value of the registryaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryaddress(String value) {
        this.registryaddress = value;
    }

    /**
     * Gets the value of the registryport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistryport() {
        return registryport;
    }

    /**
     * Sets the value of the registryport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistryport(String value) {
        this.registryport = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Registry && this.createregistry.equals(((Registry) obj).createregistry) &&
                this.registryaddress.equals(((Registry) obj).registryaddress) &&
                this.registryport.equals(((Registry) obj).registryport);
    }

    @Override
    public int hashCode()
    {
        return this.registryport.hashCode() ^ this.registryaddress.hashCode() ^ this.createregistry.hashCode();
    }
}
