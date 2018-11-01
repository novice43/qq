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
        task = new XmlTask("rest.xml");
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
        task.changeOfficiantName(oldOfficiant, newOfficiant);
    }

    //todo параметр - как и везде java.util.Date DONE
    @Override
    public List<Order> getOrders(java.util.Date date) throws RemoteException
    {
        //todo сделай метод в xmlTask и вызывай его здесь DONE
        return task.getOrders(date);
    }

    //todo возвращаем java.util.Date DONE
    @Override
    public java.util.Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException
    {
        //todo сделай метод в xmlTask и вызывай его здесь DONE
        return task.lastOfficiantWorkDate(officiant);
    }

    @Override
    public void save() throws Exception
    {
        XmlTask.saveObjectToXML("rest.xml", Restaurant.class, task.getRestaurant());
    }
}
