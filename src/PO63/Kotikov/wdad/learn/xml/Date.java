
package PO63.Kotikov.wdad.learn.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "order"
})
@XmlRootElement(name = "date")
public class Date {

    @XmlAttribute(name = "day", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String day;
    @XmlAttribute(name = "month", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String month;
    @XmlAttribute(name = "year", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String year;
    protected List<Order> order;

    public static Date newInstance(String day, String month, String year, List<Order> order)
    {
        Date newDate = new Date();
        newDate.day = day;
        newDate.month = month;
        newDate.year = year;
        newDate.order = order;
        return newDate;
    }

    /**
     * Gets the value of the day property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDay(String value) {
        this.day = value;
    }

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonth(String value) {
        this.month = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the order property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Order }
     * 
     * 
     */
    public List<Order> getOrder() {
        if (order == null) {
            order = new ArrayList<>();
        }
        return this.order;
    }

    public boolean equalsByDate(Date obj)
    {
        return this.year.equals(obj.year) && this.month.equals(obj.month) && this.day.equals(obj.day);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Date)
        {
            Date o = (Date)obj;
            return equalsByDate(o) && this.order.equals(o.order);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.day.hashCode()^this.month.hashCode()^this.year.hashCode()^this.order.hashCode();
    }

    public List<Order> getOrdersByOfficiantSecondName(String officiantSecondName)
    {
        List<Order> officiantsOrder = new ArrayList<>();
        for(Order order : this.order)
            if(order.getOfficiant().secondname.equals(officiantSecondName))
                officiantsOrder.add(order);
        return officiantsOrder;
    }
}
