package PO63.Kotikov.wdad.learn.rmi;

import PO63.Kotikov.wdad.data.managers.*;
import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client
{
    private static PreferencesManager preferencesManager = PreferencesManager.instance;

    private static final String CONFIG_FILE = "config.xml";

    private static Registry rmiRegistry;

    private static XmlDataManager stub;

    private static XmlDataManagerImpl xmlDataManagerImpl;

    private static final String remoteObjectName = "XmlDataManagerImpl";

    public static void main(String[] args) throws Exception
    {
        preferencesManager.readXml(CONFIG_FILE);
        Properties.InternalProperties properties = preferencesManager.getProperties();
        try
        {
            rmiRegistry = LocateRegistry.getRegistry(properties.getRegistryAddress(), properties.getRegistryPort());
            XmlDataManager remoteObject = (XmlDataManager)rmiRegistry.lookup(remoteObjectName);
            RegistryInfo.parse(PreferencesManager.getRmi(preferencesManager.getRootElement()).getServer().getRegistryOrBindedobject());
            remoteObject = (XmlDataManager)rmiRegistry.lookup(((Bindedobject)RegistryInfo.registries.get(0).bindedObjects.get(0)).getName());
            remoteObject.test();
            remoteObject.Test(123);
        }
        catch (Exception ex)
        {
            System.out.println("ОшЫбка: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
