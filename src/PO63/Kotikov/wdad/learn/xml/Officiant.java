
package PO63.Kotikov.wdad.learn.xml;

import PO63.Kotikov.wdad.data.managers.JDBCDataManagerImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "officiant")
public class Officiant implements Serializable
{

    @XmlAttribute(name = "firstname")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String firstname;
    @XmlAttribute(name = "secondname", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String secondname;

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the secondname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondname() {
        return secondname;
    }

    /**
     * Sets the value of the secondname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondname(String value) {
        this.secondname = value;
    }

    public static Officiant newInstance(String firstname, String secondname)
    {
        Officiant o = new Officiant();
        o.firstname = firstname;
        o.secondname = secondname;
        return o;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Officiant && ((Officiant) obj).firstname.equals(this.firstname) && ((Officiant) obj).secondname.equals(secondname);
    }

    @Override
    public int hashCode() {
        return firstname.hashCode() ^ secondname.hashCode();
    }

}
