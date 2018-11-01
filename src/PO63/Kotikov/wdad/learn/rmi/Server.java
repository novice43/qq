package PO63.Kotikov.wdad.learn.rmi;

import PO63.Kotikov.wdad.data.managers.PreferencesManager;
import PO63.Kotikov.wdad.data.managers.XmlDataManager;
import PO63.Kotikov.wdad.data.managers.XmlDataManagerImpl;
import PO63.Kotikov.wdad.learn.xml.XmlTask;
import PO63.Kotikov.wdad.utils.PreferencesManagerConstants;
import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    private static PreferencesManager preferencesManager = PreferencesManager.instance;

    private static final String CONFIG_FILE = "appconfig.xml";

    private Registry rmiRegistry;

    private XmlDataManager stub;

    private  XmlDataManagerImpl xmlDataManagerImpl;

    private static final String remoteObjectName = "XmlDataManagerImpl";

    public void main(String[] args) throws Exception
    {
        preferencesManager.readXml(CONFIG_FILE);
        try
        {
            rmiRegistry = preferencesManager.isCreateRegistry() ?
                    LocateRegistry.createRegistry(preferencesManager.getPort()) :
                    LocateRegistry.getRegistry(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS),
                            preferencesManager.getPort());
            xmlDataManagerImpl = new XmlDataManagerImpl();
            xmlDataManagerImpl.init();
            stub = (XmlDataManager) UnicastRemoteObject.exportObject(xmlDataManagerImpl, 0);
            rmiRegistry.bind(remoteObjectName, stub);
            preferencesManager.addBindedObject(remoteObjectName, stub.getClass().getCanonicalName(), RegistryInfo.registries.get(0).registry);
            preferencesManager.save();
        }
        catch (Exception ex)
        {
            System.out.println("ОшЫбка: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}