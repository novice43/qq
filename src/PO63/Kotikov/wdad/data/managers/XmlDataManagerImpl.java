package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.xml.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

public class XmlDataManagerImpl implements DataManager, Serializable
{
    private XmlTask task;

    public void init() throws Exception
    {
        task = new XmlTask("rest.xml");
    }

    @Override
    public double earningsTotal(Officiant officiant, java.util.Date calendar) throws RemoteException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar);
        return task.earningsTotal(officiant.getSecondname(), cal);
    }

    @Override
    public void removeDay(java.util.Date calendar) throws RemoteException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar);
        task.removeDay(cal);
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception
    {
        task.changeOfficiantName(oldOfficiant, newOfficiant);
    }

    @Override
    public List<Order> getOrders(java.util.Date date) throws RemoteException
    {
        return task.getOrders(date);
    }

    @Override
    public java.util.Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException
    {
        return task.lastOfficiantWorkDate(officiant);
    }

    @Override
    public void save() throws Exception
    {
        XmlTask.saveObjectToXML("rest.xml", Restaurant.class, task.getRestaurant());
    }
}
