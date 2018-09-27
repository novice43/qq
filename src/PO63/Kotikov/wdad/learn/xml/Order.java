
package PO63.Kotikov.wdad.learn.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "officiant",
    "item",
    "totalcost"
})
@XmlRootElement(name = "order")
public class Order {

    protected Officiant officiant;
    protected List<Item> item;
    protected String totalcost;

    /**
     * Gets the value of the officiant property.
     * 
     * @return
     *     possible object is
     *     {@link Officiant }
     *     
     */
    public Officiant getOfficiant() {
        return officiant;
    }

    /**
     * Sets the value of the officiant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Officiant }
     *     
     */
    public void setOfficiant(Officiant value) {
        this.officiant = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getItem() {
        if (item == null) {
            item = new ArrayList<Item>();
        }
        return this.item;
    }

    /**
     * Gets the value of the totalcost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalcost() {
        return totalcost;
    }

    /**
     * Sets the value of the totalcost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalcost(String value) {
        this.totalcost = value;
    }

}
