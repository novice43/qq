package PO63.Kotikov.wdad.learn.rmi;

import PO63.Kotikov.wdad.data.managers.*;
import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    private static PreferencesManager preferencesManager = PreferencesManager.instance;

    private static final String CONFIG_FILE = "config.xml";

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
        Properties.InternalProperties properties = preferencesManager.getProperties();
        try
        {
            rmiRegistry = LocateRegistry.getRegistry(properties.getRegistryAddress(), properties.getRegistryPort());
            remoteObject = (XmlDataManager)rmiRegistry.lookup(remoteObjectName);
            RegistryInfo.parse(PreferencesManager.getRmi(preferencesManager.getRootElement()).getServer().getRegistryOrBindedobject());
            remoteObject = (XmlDataManager)rmiRegistry.lookup(((Bindedobject)RegistryInfo.registries.get(0).bindedObjects.get(0)).getName());
        }
        catch (Exception ex)
        {
            System.out.println("ОшЫбка: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}