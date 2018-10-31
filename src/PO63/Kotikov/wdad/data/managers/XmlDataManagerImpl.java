package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.xml.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager, Serializable
{
    private XmlTask task;

    public void init() throws Exception
    {
        task = new XmlTask();
    }

    @Override
    public double earningsTotal(Officiant officiant, java.util.Date calendar) throws RemoteException
    {
        /*double total = 0.0;
        List<Order> orders = Date.getOrdersByDate(calendar);
        for(Order order : orders)
            if(order.byOfficiant(officiant))
                total += order.getTotalCost();
        return total == 0.0 ? -1 : total;*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar);
        return task.earningsTotal(officiant.getSecondname(), cal);
    }

    @Override
    public void removeDay(java.util.Date calendar) throws RemoteException
    {
        //Date.removeDate(calendar);
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar);
        task.removeDay(cal);
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception
    {
        //Officiant.findOfficiant(oldOfficiant).changeOfficiantFullname(newOfficiant);
        task.changeOfficiantName(oldOfficiant.getFirstname(), oldOfficiant.getSecondname(), newOfficiant.getFirstname(), newOfficiant.getSecondname());
    }

    @Override
    public List<Order> getOrders(Date date) throws RemoteException
    {
        return Date.getOrdersByDate(Date.newInstance(date.getDay(), date.getMonth(), date.getYear(), null), task.getRestaurant().getDate());
    }

    @Override
    public Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException
    {
        List<Date> dates = Date.getDatesByOfficiant(officiant, task.getRestaurant().getDate());
        List<Long> ret = new ArrayList<>();
        dates.forEach((a) -> {
            ret.add(a.getDate().getTime());
        });
        java.util.Date date;
        if(ret.size() > 1) date = new java.util.Date(ret.stream().min((a, b) -> Long.compare(a, b)).get());
        else
            if(ret.size() == 1) date = new java.util.Date(ret.get(0));
                    else return null;
        return Date.newInstance(date.getDay(), date.getMonth(), date.getYear(), null);
    }

    @Override
    public void save() throws Exception
    {
        XmlTask.saveObjectToXML("rest.xml", Restaurant.class, task.getRestaurant());
    }
}
