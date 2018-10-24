package PO63.Kotikov.wdad.data.managers;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager
{

    @Override
    public double earningsTotal(String officiantSecondName, Calendar calendar) throws RemoteException
    {
        return 0;
    }

    @Override
    public void removeDay(Calendar calendar) throws RemoteException
    {

    }

    @Override
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) throws RemoteException
    {

    }

    @Override
    public List<Integer> getOrders(Date date) throws RemoteException
    {
        return null;
    }

    @Override
    public Date lastOfficiantWorkDate(Integer officiant) throws RemoteException
    {
        return null;
    }
}
