package PO63.Kotikov.wdad.data.managers;

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
    private Properties activeProperties;

    private String filename;

    private Appconfig rootElement;

    public final static PreferencesManager instance = new PreferencesManager();

    private static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(sr);
    }

    private static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    public void readXml(String filename) throws Exception
    {
        rootElement = (Appconfig) loadObjectFromXML(filename, Appconfig.class);
        activeProperties = new Properties(filename);
        this.filename = filename;
    }

    public Appconfig getRootElement()
    {
        return rootElement;
    }

    @Deprecated
    public static Rmi getRmi(Appconfig rootElement)
    {
        return rootElement.rmi;
    }

    @Deprecated
    public static void setRmi(Appconfig rootElement, Rmi rmi)
    {
        rootElement.rmi = rmi;
    }

    @Deprecated
    public static Server getServer(Appconfig rootElement)
    {
        return rootElement.rmi.server;
    }

    @Deprecated
    public static void setServer(Appconfig rootElement, Server server)
    {
        rootElement.rmi.server = server;
    }

    @Deprecated
    public static Client getClient(Appconfig rootElement)
    {
        return rootElement.rmi.client;
    }

    @Deprecated
    public static void setClient(Appconfig rootElement, Client client)
    {
        rootElement.rmi.client = client;
    }

    @Deprecated
    public static void setClassprovider(Appconfig rootElement, String classprovider)
    {
        rootElement.rmi.classprovider = classprovider;
    }

    @Deprecated
    public static String getClassprovider(Appconfig rootElement)
    {
        return rootElement.rmi.classprovider;
    }

    @Deprecated
    public static List<Object> getRegistryOrBindedObject(Appconfig rootElement)
    {
        return rootElement.rmi.server.registryOrBindedobject;
    }

    @Deprecated
    public static void setRegistryOrBindedObject(Appconfig rootElement, List<Object> objects)
    {
        rootElement.rmi.server.registryOrBindedobject = objects;
    }

    @Deprecated
    public static void setPolicyPath(Appconfig rootElement, String policyPath)
    {
        rootElement.rmi.client.policypath = policyPath;
    }

    @Deprecated
    public static String getPolicyPath(Appconfig rootElement)
    {
        return rootElement.rmi.client.policypath;
    }

    @Deprecated
    public static void setUseCodeBaseOnly(Appconfig rootElement, String useCodeBaseOnly)
    {
        rootElement.rmi.client.usecodebaseonly = useCodeBaseOnly;
    }

    @Deprecated
    public static String getUseCodeBaseOnly(Appconfig rootElement)
    {
        return rootElement.rmi.client.usecodebaseonly;
    }

    private void checkActiveProperties() throws Exception
    {
        if(activeProperties == null) throw new Exception("Trying to set properties when they are not configured(NullPointer)");
    }

    public void setProperty(String key, String value) throws Exception
    {
        checkActiveProperties();
        activeProperties.setProperty(key, value);
        saveFromDOM();
    }

    public String getProperty(String key) throws Exception
    {
        checkActiveProperties();
        return activeProperties.getProperty(key);
    }

    public void setProperties(Properties.InternalProperties prop) throws Exception
    {
        checkActiveProperties();
        activeProperties.setProperties(prop);
        saveFromDOM();
    }

    public Properties.InternalProperties getProperties() throws Exception
    {
        checkActiveProperties();
        return activeProperties.getProperties();
    }

    public void addBindedObject(String name, String className, Registry registry) throws Exception
    {
        Bindedobject obj = new Bindedobject();
        obj.clazz = className;
        obj.name = name;
        RegistryInfo.parse(rootElement.rmi.server.registryOrBindedobject);
        for(RegistryInfo info : RegistryInfo.registries)
            if(info.registry.equals(registry))
            {
                info.lastIndex++;
                rootElement.rmi.server.registryOrBindedobject.add(info.lastIndex, obj);
                return;
            }
            saveFromInstance();
    }

    public void removeBindedObject(String name) throws Exception
    {
        List<Object> collection = rootElement.rmi.server.registryOrBindedobject;
        Object current;
        for(int i = 0; i < collection.size(); i++)
        {
            current = collection.get(i);
            if (current instanceof Bindedobject && ((Bindedobject) current).equalsByName(name))
                collection.remove(i);
        }
        saveFromInstance();
    }

    private void saveFromDOM() throws Exception
    {
        activeProperties.save(filename);
        updateData();
    }

    private void saveFromInstance() throws Exception
    {
        saveObjectToXML(filename, Appconfig.class, rootElement);
        updateData();
    }

    private void updateData() throws Exception
    {
        rootElement = (Appconfig) loadObjectFromXML(filename, Appconfig.class);
        activeProperties = new Properties(filename);
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

}
