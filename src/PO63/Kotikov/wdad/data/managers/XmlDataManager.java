package PO63.Kotikov.wdad.data.managers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface XmlDataManager extends Remote
{
    double earningsTotal(String officiantSecondName, Calendar calendar) throws RemoteException;
    void removeDay(Calendar calendar) throws RemoteException;
    void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) throws RemoteException;
    List<Integer> getOrders(Date date) throws RemoteException;
    Date lastOfficiantWorkDate(Integer officiant) throws RemoteException;
}
