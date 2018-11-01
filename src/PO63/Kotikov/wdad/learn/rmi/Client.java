package PO63.Kotikov.wdad.learn.rmi;

import PO63.Kotikov.wdad.data.managers.*;
import PO63.Kotikov.wdad.utils.PreferencesManagerConstants;
import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    private static PreferencesManager preferencesManager = PreferencesManager.instance;

    private static final String CONFIG_FILE = "appconfig.xml";

    private Registry rmiRegistry;

    private XmlDataManager stub;

    private XmlDataManagerImpl xmlDataManagerImpl;

    private static final String remoteObjectName = "XmlDataManagerImpl";

    private XmlDataManager remoteObject;

    public XmlDataManager getRemoteObject() throws Exception
    {
        if(remoteObject == null) throw new Exception("Remote object is null pointer");
        return remoteObject;
    }

    public void main(String[] args) throws Exception
    {
        preferencesManager.readXml(CONFIG_FILE);
        try
        {
            rmiRegistry = LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS),
                    preferencesManager.getPort());
            remoteObject = (XmlDataManager)rmiRegistry.lookup(remoteObjectName);
        }
        catch (Exception ex)
        {
            System.out.println("ОшЫбка: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
