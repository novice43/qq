package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.rmi.Date;
import PO63.Kotikov.wdad.learn.rmi.Officiant;
import PO63.Kotikov.wdad.learn.rmi.Order;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager, Serializable
{
    @Override
    public double earningsTotal(Officiant officiant, java.util.Date calendar) throws RemoteException
    {
        double total = 0.0;
        List<Order> orders = Date.getOrdersByDate(calendar);
        for(Order order : orders)
            if(order.byOfficiant(officiant))
                total += order.getTotalCost();
        return total == 0.0 ? -1 : total;
    }

    @Override
    public void removeDay(java.util.Date calendar) throws RemoteException
    {
        Date.removeDate(calendar);
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception
    {

        Officiant.findOfficiant(oldOfficiant).changeOfficiantFullname(newOfficiant);
    }

    @Override
    public List<Order> getOrders(java.util.Date date) throws RemoteException
    {
        return Date.getOrdersByDate(date);
    }

    @Override
    public java.util.Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException
    {
        List<Date> dates = Date.getDatesByOfficiant(officiant);
        List<Long> ret = new ArrayList<>();
        dates.forEach((a) -> {
            ret.add(a.getDate().getTime());
        });
        return new java.util.Date(ret.stream().min((a, b) -> Long.compare(a, b)).get());
    }
}
