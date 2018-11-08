package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.xml.XmlTask;
import PO63.Kotikov.wdad.utils.PreferencesManagerConstants;
import PO63.Kotikov.wdad.utils.RegistryInfo;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PreferencesManager
{

    private String filename;

    private Appconfig appconfig;

    public final static PreferencesManager instance = new PreferencesManager();

    private boolean createRegistry;
    private int port;

    public void readXml(String filename) throws Exception
    {
        appconfig = (Appconfig)XmlTask.loadObjectFromXML(filename, Appconfig.class);
        RegistryInfo.parse(appconfig.rmi.server.registryOrBindedobject);
        fillServerProperties();
        this.filename = filename;
    }


    public void setProperty(String key, String value) throws Exception
    {
        switch (key)
        {
            case PreferencesManagerConstants.CLASS_PROVIDER:
                appconfig.rmi.classprovider = value;
                break;
            case PreferencesManagerConstants.POLICY_PATH:
                appconfig.rmi.client.policypath = value;
                break;
            case PreferencesManagerConstants.USE_CODE_BASE_ONLY:
                appconfig.rmi.client.usecodebaseonly = value;
                break;
            case PreferencesManagerConstants.CREATE_REGISTRY:
                RegistryInfo.registries.get(0).registry.setCreateregistry(value);
                break;
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                RegistryInfo.registries.get(0).registry.setRegistryaddress(value);
                break;
            case PreferencesManagerConstants.REGISTRY_PORT:
                RegistryInfo.registries.get(0).registry.setRegistryport(value);
                break;
        }
    }

    public String getProperty(String key) throws Exception
    {
        switch (key)
        {
            case PreferencesManagerConstants.CLASS_PROVIDER:
                return appconfig.rmi.classprovider;
            case PreferencesManagerConstants.POLICY_PATH:
                return appconfig.rmi.client.policypath;
            case PreferencesManagerConstants.USE_CODE_BASE_ONLY:
                return appconfig.rmi.client.usecodebaseonly;
            case PreferencesManagerConstants.CREATE_REGISTRY:
                createRegistry = RegistryInfo.registries.get(0).registry.getCreateregistry().toLowerCase().equals("yes");
                return RegistryInfo.registries.get(0).registry.getCreateregistry();
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                return RegistryInfo.registries.get(0).registry.getRegistryaddress();
            case PreferencesManagerConstants.REGISTRY_PORT:
                port = Integer.parseInt(RegistryInfo.registries.get(0).registry.getRegistryport());
                return RegistryInfo.registries.get(0).registry.getRegistryport();
                default:
                    throw new Exception("Unknown property " + key);
        }
    }

    public Datasource getDataSource()
    {
        return appconfig.datasource;
    }

    public void fillServerProperties() throws Exception
    {
        getProperty(PreferencesManagerConstants.CREATE_REGISTRY);
        getProperty(PreferencesManagerConstants.REGISTRY_PORT);
    }

    public void addBindedObject(String name, String className, Registry registry)
    {
        appconfig.rmi.server.addBindedObject(name, className, registry);
    }

    public void addBindedObject(String name, String className)
    {
        appconfig.rmi.server.addBindedObject(name, className);
    }

    public void removeBindedObject(String name)
    {
        appconfig.rmi.server.removeBindedObject(name);
    }

    public void removeBindedObject(String name, Registry registry)
    {
        appconfig.rmi.server.removeBindedObject(name, registry);
    }

    public void save() throws Exception
    {
        appconfig.rmi.server.registryOrBindedobject.clear();
        appconfig.rmi.server.registryOrBindedobject.addAll(RegistryInfo.getObjectList());
        XmlTask.saveObjectToXML(filename, Appconfig.class, appconfig);
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public boolean isCreateRegistry()
    {
        return createRegistry;
    }

    public int getPort()
    {
        return port;
    }
}
