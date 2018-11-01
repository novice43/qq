package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.xml.Date;
import PO63.Kotikov.wdad.learn.xml.Officiant;
import PO63.Kotikov.wdad.learn.xml.Order;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface XmlDataManager extends Remote
{
    double earningsTotal(Officiant officiant, java.util.Date calendar) throws RemoteException;
    void removeDay(java.util.Date calendar) throws RemoteException;
    void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception;
    List<Order> getOrders(java.util.Date date) throws RemoteException;
    java.util.Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException;
    void save() throws Exception;
}
