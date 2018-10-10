package PO63.Kotikov.wdad.data.managers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PreferencesManager
{
    private static Properties activeProperties;

    protected static Appconfig rootElement;

    protected final static PreferencesManager instance = new PreferencesManager();

    private static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Object) unmarshaller.unmarshal(sr);
    }

    private static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    public void readXml(String filename) throws Exception
    {
        rootElement = (Appconfig) loadObjectFromXML(filename, Rmi.class);
        activeProperties = new Properties(rootElement);
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

    public void setProperty(String key, String value)
    {

    }

    public String getProperty(String key)
    {
        return null;
    }

    public void setProperties(Properties prop)
    {

    }

    public Properties getProperties()
    {
        return null;
    }

    public void addBindedObject(String name, String className)
    {

    }

    public void removeBindedObject(String name)
    {

    }
}
