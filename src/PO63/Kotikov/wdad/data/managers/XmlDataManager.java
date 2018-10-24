package PO63.Kotikov.wdad.data.managers;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface XmlDataManager extends Remote
{
    double earningsTotal(String officiantSecondName, Calendar calendar) throws RemoteException;
    void removeDay(Calendar calendar) throws RemoteException;
    void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) throws RemoteException;
}
