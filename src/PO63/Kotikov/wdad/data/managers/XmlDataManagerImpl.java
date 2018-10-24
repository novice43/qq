package PO63.Kotikov.wdad.data.managers;

import java.rmi.RemoteException;

public class XmlDataManagerImpl implements XmlDataManager
{

    @Override
    public void test() throws RemoteException
    {
        System.out.println("tested string");
    }

    @Override
    public void Test(Integer innt) throws RemoteException
    {
        System.out.println(innt);
    }
}
