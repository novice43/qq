
package PO63.Kotikov.wdad.learn.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
    "date"
})
@XmlRootElement(name = "restaurant")
public class Restaurant implements Serializable
{

    protected List<Date> date;

    /**
     * Gets the value of the date property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the date property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Date }
     * 
     * 
     */
    public List<Date> getDate() {
        if (date == null) {
            date = new ArrayList<>();
        }
        return this.date;
    }

    public Date getDate(Date d)
    {
        for(Date date : this.date)
        {
            if(date.equalsByDate(d))
                return date;
        }
        return null;
    }

    //todo переносим в restaurant DONE
    public List<Date> getDatesByOfficiant(Officiant officiant)
    {
        List<Date> dateList = new ArrayList<>();
        for (Date d : date)
            if(d.hasSomeOrdersByOfficiant(officiant))
                dateList.add(d);
        return dateList;
    }

    public List<java.util.Date> getDatesByOfficiantUtilDate(Officiant officiant)
    {
        List<java.util.Date> dateList = new ArrayList<>();
        for (Date d : getDatesByOfficiant(officiant))
            dateList.add(d.getDate());
        return dateList;
    }

    public void changeOfficiantName(Officiant oldName, Officiant newName)
    {
        for(Date d : date)
            //todo Я ТЕБЯ НЕНАВИЖУ! DONE. I HATE YOU TOO
            d.updateOfficiantName(oldName, newName);
    }

    public List<Order> getOrders(java.util.Date date)
    {
        List<Order> result = new ArrayList<>();
        for(Date d : this.date)
        {
            if(d.equalsByDate(date))
            {
                result.addAll(d.order);
                return result;
            }
        }
        return null;
    }

    public List<Order> getOrdersByDate(Date date)
    {
        for(Date currentDate : this.date)
            if(currentDate.equalsByDate(date))
                return currentDate.getOrder();
        return new ArrayList<>();
    }

    public boolean removeDate(Date day)
    {
        for(int i = 0; i < date.size(); i++)
        {
            if (date.get(i).equalsByDate(day))
            {
                date.remove(i);
                return true;
            }
        }
        return false;
    }
}
