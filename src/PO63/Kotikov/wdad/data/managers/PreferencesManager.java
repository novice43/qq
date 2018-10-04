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
    protected static Appconfig rootElement;

    protected final static PreferencesManager instance = new PreferencesManager();

    private static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Object)unmarshaller.unmarshal(sr);
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
    }

    public Rmi getRmi()
    {
        return rootElement.rmi;
    }

    public void setRmi(Rmi rmi)
    {
        rootElement.rmi = rmi;
    }

    public Server getServer()
    {
        return rootElement.rmi.server;
    }

    public void setServer(Server server)
    {
        rootElement.rmi.server = server;
    }

    public Client getClient()
    {
        return rootElement.rmi.client;
    }

    public void setClient(Client client)
    {
        rootElement.rmi.client = client;
    }

    public void setClassprovider(String classprovider)
    {
        rootElement.rmi.classprovider = classprovider;
    }

    public String getClassprovider()
    {
        return rootElement.rmi.classprovider;
    }

    public List<Object> getRegistryOrBindedObject()
    {
        return rootElement.rmi.server.registryOrBindedobject;
    }

    public void setRegistryOrBindedObject(List<Object> objects)
    {
        rootElement.rmi.server.registryOrBindedobject = objects;
    }

    public void setPolicyPath(String policyPath)
    {
        rootElement.rmi.client.policypath = policyPath;
    }

    public String getPolicyPath()
    {
        return rootElement.rmi.client.policypath;
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly)
    {
        rootElement.rmi.client.usecodebaseonly = useCodeBaseOnly;
    }

    public String getUseCodeBaseOnly()
    {
        return rootElement.rmi.client.usecodebaseonly;
    }
}
