package PO63.Kotikov.wdad.learn.rmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Date implements Serializable
{
    private static List<Date> dateList = new ArrayList<>();
    private java.util.Date date;
    private List<Order> orderList;

    public Date(java.util.Date date, List<Order> orderList) throws Exception
    {
        this.date = date;
        this.orderList = orderList;
        for(Date d : dateList)
            if(d.date.equals(date)) throw new Exception("Attempt to add same date. " + date.toString() + " this date is in list again");
        dateList.add(this);
    }

    public static List<Order> getOrdersByDate(java.util.Date date)
    {
        for(Date currentDate : dateList)
            if(currentDate.date.equals(date))
                return currentDate.orderList;
        return new ArrayList<>();
    }

    public static boolean removeDate(java.util.Date day)
    {
        for(int i = 0; i < dateList.size(); i++)
        {
            if (dateList.get(i).date.equals(day))
            {
                dateList.remove(i);
                return true;
            }
        }
        return false;
    }

    public static List<Date> getDateList()
    {
        return dateList;
    }

    public List<Order> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(List<Order> orderList)
    {
        this.orderList = orderList;
    }

    public java.util.Date getDate()
    {
        return date;
    }

    public static List<Date> getDatesByOfficiant(Officiant officiant)
    {
        List<Date> dateList = new ArrayList<>();
        for (Date date : Date.dateList)
        {
            for (Order order : date.orderList)
            {
                if (order.officiant.equals(officiant))
                {
                    dateList.add(date);
                }
            }
        }
        return dateList;
    }
}
