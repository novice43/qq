package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.lang.reflect.Field;

public class Properties
{
    public class InternalProperties
    {
        private String classProvider;
        private String policyPath;
        private boolean useCodeBaseOnly;
        private boolean createRegistry;
        private String registryAddress;
        private int registryPort;

        InternalProperties(String classProvider, String policyPath, String useCodeBaseOnly, String createRegistry, String registryAddress, String registryPort)
        {
            this.classProvider = classProvider;
            this.policyPath = policyPath;
            this.useCodeBaseOnly = useCodeBaseOnly.toLowerCase().equals("yes");
            this.createRegistry = createRegistry.toLowerCase().equals("yes");
            this.registryAddress = registryAddress;
            this.registryPort = Integer.parseInt(registryPort);
        }

        public String getClassProvider()
        {
            return classProvider;
        }

        public void setClassProvider(String classProvider)
        {
            this.classProvider = classProvider;
        }

        public String getPolicyPath()
        {
            return policyPath;
        }

        public void setPolicyPath(String policyPath)
        {
            this.policyPath = policyPath;
        }

        public boolean getUseCodeBaseOnly()
        {
            return useCodeBaseOnly;
        }

        public void setUseCodeBaseOnly(boolean useCodeBaseOnly)
        {
            this.useCodeBaseOnly = useCodeBaseOnly;
        }

        public boolean getCreateRegistry()
        {
            return createRegistry;
        }

        public void setCreateRegistry(boolean createRegistry)
        {
            this.createRegistry = createRegistry;
        }

        public String getRegistryAddress()
        {
            return registryAddress;
        }

        public void setRegistryAddress(String registryAddress)
        {
            this.registryAddress = registryAddress;
        }

        public int getRegistryPort()
        {
            return registryPort;
        }

        public void setRegistryPort(int registryPort)
        {
            this.registryPort = registryPort;
        }
    }
    private static final String DEF_CLASS_PROVIDER_DEFAULT = "http://www.yourhost.free.ru/cp/cp.jar";
    private static final String DEF_POLICY_PATH_DEFAULT = "client.policy";
    private static final String DEF_USE_CODE_BASE_ONLY_DEFAULT = "no";
    private static final String DEF_CREATE_REGISTRY_DEFAULT = "yes";
    private static final String DEF_REGISTRY_ADDRESS = "localhost";
    private static final String DEF_REGISTRY_PORT = "1099";

    private String filename;
    private Document document;
    private XPath xpath = XPathFactory.newInstance().newXPath();

    Properties(String filename) throws Exception
    {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
        this.filename = filename;
    }

    private void checkDocument() throws Exception
    {
        if(document == null) throw new Exception("Trying to parse null document");
    }

    private void validateKey(String key) throws Exception
    {
        for(Field field : PreferencesManagerConstants.class.getDeclaredFields())
        {
            if(key.equals(field.get(null))) return;
        }
        throw new Exception("Invalid property key: " + key);
    }

    /**
     *
     * @param key One of PreferencesManagerConstants
     * @return node by key
     * @throws Exception If document was not loaded
     */
    private Node getNode(String key) throws Exception
    {
        checkDocument();
        return (Node)xpath.evaluate(key.replace('.', '/'), document, XPathConstants.NODE);
    }

    String getProperty(String key) throws Exception
    {
        validateKey(key);
        return getNode(key).getNodeValue();
    }

    void setProperty(String key, String value) throws Exception
    {
        validateKey(key);
        getNode(key).setNodeValue(value);
    }

    void setProperties(InternalProperties properties) throws Exception
    {
        if(properties.classProvider != null) setProperty(PreferencesManagerConstants.CLASS_PROVIDER, properties.classProvider);
        if(properties.policyPath != null) setProperty(PreferencesManagerConstants.POLICY_PATH, properties.policyPath);
        setProperty(PreferencesManagerConstants.USE_CODE_BASE_ONLY, properties.useCodeBaseOnly ? "yes" : "no");
        setProperty(PreferencesManagerConstants.CREATE_REGISTRY, properties.createRegistry ? "yes" : "no");
        if(properties.registryAddress != null) setProperty(PreferencesManagerConstants.REGISTRY_ADDRESS, properties.registryAddress);
        setProperty(PreferencesManagerConstants.REGISTRY_PORT, String.valueOf(properties.registryPort));
    }

    InternalProperties getProperties() throws Exception
    {
        return new InternalProperties(
                getProperty(PreferencesManagerConstants.CLASS_PROVIDER),
                getProperty(PreferencesManagerConstants.POLICY_PATH),
                getProperty(PreferencesManagerConstants.USE_CODE_BASE_ONLY),
                getProperty(PreferencesManagerConstants.CREATE_REGISTRY),
                getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS),
                getProperty(PreferencesManagerConstants.REGISTRY_PORT)
        );
    }

    void save(String filename) throws Exception
    {
        if(filename == null) throw new Exception("Filename was not set");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result out = new StreamResult(new File(filename));
        Source in = new DOMSource(document);
        transformer.transform(in, out);
    }

    public void save() throws Exception
    {
        if(filename != null) save(filename);
        else
            throw new Exception("Don't know where to save properties. Filename is null");
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
