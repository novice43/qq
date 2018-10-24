package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.rmi.Officiant;
import PO63.Kotikov.wdad.learn.rmi.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface XmlDataManager extends Remote
{
    double earningsTotal(Officiant officiant, Date calendar) throws RemoteException;
    void removeDay(Date calendar) throws RemoteException;
    void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception;
    List<Order> getOrders(Date date) throws RemoteException;
    Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException;
}
