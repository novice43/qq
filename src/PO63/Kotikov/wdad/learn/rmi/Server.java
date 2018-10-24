package PO63.Kotikov.wdad.learn.rmi;

import PO63.Kotikov.wdad.data.managers.PreferencesManager;
import PO63.Kotikov.wdad.data.managers.Properties;
import PO63.Kotikov.wdad.data.managers.XmlDataManager;
import PO63.Kotikov.wdad.data.managers.XmlDataManagerImpl;
import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
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
            rmiRegistry = properties.getCreateRegistry() ? LocateRegistry.createRegistry(properties.getRegistryPort()) : LocateRegistry.getRegistry(properties.getRegistryAddress(), properties.getRegistryPort());
            xmlDataManagerImpl = new XmlDataManagerImpl();
            stub = (XmlDataManager) UnicastRemoteObject.exportObject(xmlDataManagerImpl, 0);
            rmiRegistry.bind(remoteObjectName, stub);
            RegistryInfo.parse(PreferencesManager.getRmi(preferencesManager.getRootElement()).getServer().getRegistryOrBindedobject());
            preferencesManager.addBindedObject(remoteObjectName, stub.getClass().getCanonicalName(), RegistryInfo.registries.get(0).registry);//How todo?
        }
        catch (Exception ex)
        {
            System.out.println("ОшЫбка: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}