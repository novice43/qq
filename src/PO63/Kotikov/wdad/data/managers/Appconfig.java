
package PO63.Kotikov.wdad.data.managers;
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
        "rmi",
        "datasource"
})
@XmlRootElement(name = "appconfig")
public class Appconfig {

    @XmlElement(required = true)
    protected Rmi rmi;
    @XmlElement(required = true)
    protected Datasource datasource;

    /**
     * Gets the value of the rmi property.
     *
     * @return
     *     possible object is
     *     {@link Rmi }
     *
     */
    public Rmi getRmi() {
        return rmi;
    }

    /**
     * Sets the value of the rmi property.
     *
     * @param value
     *     allowed object is
     *     {@link Rmi }
     *
     */
    public void setRmi(Rmi value) {
        this.rmi = value;
    }

    /**
     * Gets the value of the datasource property.
     *
     * @return
     *     possible object is
     *     {@link Datasource }
     *
     */
    public Datasource getDatasource() {
        return datasource;
    }

    /**
     * Sets the value of the datasource property.
     *
     * @param value
     *     allowed object is
     *     {@link Datasource }
     *
     */
    public void setDatasource(Datasource value) {
        this.datasource = value;
    }

}
