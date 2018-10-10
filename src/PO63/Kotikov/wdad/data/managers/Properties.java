package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.utils.PreferencesManagerConstants;

import java.util.List;

public class Properties
{
    private Appconfig appconfig;
    private String classprovider;
    private String policyPath;
    private String useCodeBaseOnly;
    private String createRegistry;
    private String registryAddress;
    private String registryPort;
    private static final String CLASS_PROVIDER_DEFAULT = "http://www.yourhost.free.ru/cp/cp.jar";
    private static final String POLICY_PATH_DEFAULT = "client.policy";
    private static final String USE_CODE_BASE_ONLY_DEFAULT = "no";
    private static final String CREATE_REGISTRY_DEFAULT = "yes";
    private static final String REGISTRY_ADDRESS = "localhost";
    private static final String REGISTRY_PORT = "1099";
    public Properties(String classprovider, String policyPath, String useCodeBaseOnly, String createRegistry, String registryAddress, String registryPort)
    {
        this.classprovider = classprovider;
        this.policyPath = policyPath;
        this.useCodeBaseOnly = useCodeBaseOnly;
        this.createRegistry = createRegistry;
        this.registryAddress = registryAddress;
        this.registryPort = registryPort;
    }

    public Properties()
    {
        this.classprovider = CLASS_PROVIDER_DEFAULT;
        this.policyPath = POLICY_PATH_DEFAULT;
        this.useCodeBaseOnly = USE_CODE_BASE_ONLY_DEFAULT;
        this.createRegistry = CREATE_REGISTRY_DEFAULT;
        this.registryAddress = REGISTRY_ADDRESS;
        this.registryPort = REGISTRY_PORT;
    }

    public Properties(Appconfig appconfig)
    {
        this.appconfig = appconfig;
        classprovider = appconfig.rmi.classprovider;
        policyPath = appconfig.rmi.client.policypath;
        useCodeBaseOnly = appconfig.rmi.client.usecodebaseonly;
        List<Object> objectList = appconfig.rmi.server.registryOrBindedobject;
        Registry registry;
        for(Object obj : objectList)
            if(obj instanceof Registry)
            {
                registry = (Registry)obj;
                createRegistry = registry.createregistry;
                registryAddress = registry.registryaddress;
                registryPort = registry.registryport;
                break;
            }
    }

    public void setProperty(String key, String value) throws Exception
    {
        switch (key)
        {
            case PreferencesManagerConstants.CLASS_PROVIDER:
                classprovider = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            case PreferencesManagerConstants.CREATE_REGISTRY:
                createRegistry = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            case PreferencesManagerConstants.POLICY_PATH:
                policyPath = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                registryAddress = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            case PreferencesManagerConstants.REGISTRY_PORT:
                registryPort = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            case PreferencesManagerConstants.USE_CODE_BASE_ONLY:
                useCodeBaseOnly = value;
                if(appconfig != null)
                    PreferencesManager.setClassprovider(appconfig, value);
                break;
            default:
                throw new Exception("Unknown property " + key);
        }
    }

    public String getProperty(String key) throws Exception
    {
        switch (key)
        {
            case PreferencesManagerConstants.CLASS_PROVIDER:
                return classprovider;
            case PreferencesManagerConstants.CREATE_REGISTRY:
                return createRegistry;
            case PreferencesManagerConstants.POLICY_PATH:
                return policyPath;
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                return registryAddress;
            case PreferencesManagerConstants.REGISTRY_PORT:
                return registryPort;
            case PreferencesManagerConstants.USE_CODE_BASE_ONLY:
                return useCodeBaseOnly;
            default:
                throw new Exception("Unknown property " + key);
        }
    }
}
