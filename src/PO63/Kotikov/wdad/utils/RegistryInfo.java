package PO63.Kotikov.wdad.utils;

import PO63.Kotikov.wdad.data.managers.Bindedobject;
import PO63.Kotikov.wdad.data.managers.PreferencesManager;
import PO63.Kotikov.wdad.data.managers.Registry;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

public class RegistryInfo
{
    public static List<RegistryInfo> registries = new ArrayList<RegistryInfo>();//Registry list startIndex -> Registry in global registry list
    private int startIndex;//Index in global registry list
    public int lastIndex;
    public Registry registry;//Registry by its startIndex
    public List<Object> bindedObjects = new ArrayList<>();

    private RegistryInfo(int startIndex, Registry registry, int lastIndex) throws Exception
    {
        if(registry == null) throw new Exception("Null registry cannot be added to registry list");
        this.startIndex = startIndex;
        this.registry = registry;
        this.lastIndex = lastIndex;
    }

    public RegistryInfo(Registry registry, List<Bindedobject> objects)
    {
        startIndex = -1;
        lastIndex = -1;
        bindedObjects.addAll(objects);
        this.registry = registry;
    }

    /**
     * Pushing registry and its indices in list. If lastIndex == -1 that means real lastIndex points to the end of the list
     * @param objects list of some objects where are some registries
     * @throws Exception something went wrong like nullpointer etc...
     */
    public static void parse(List<Object> objects) throws Exception
    {
        registries.clear();//Clear before new parsing
        Object current;
        for(int i = 0; i < objects.size(); i++)
        {
            current = objects.get(i);
            if(current instanceof Registry)
            {
                if(registries.size() > 0) registries.get(registries.size()-1).lastIndex = i-1;
                registries.add(new RegistryInfo(i, (Registry) current, i));
            }
            else
            {
                registries.get(registries.size() - 1).bindedObjects.add(current);
                registries.get(registries.size()-1).lastIndex = i;
            }
        }
    }

    public static void parse(Document document) throws Exception
    {
        XPath xp = XPathFactory.newInstance().newXPath();
        NodeList registries = (NodeList)xp.evaluate(PreferencesManagerConstants.REGISTRY_ENTRY_POINT, document, XPathConstants.NODESET);
        List<Bindedobject> registryObjects = new ArrayList<>();
        Node reg;
        Node bindedObject;
        Bindedobject obj;
        Registry registry;
        RegistryInfo rinfo;
        for(int i = 0; i < registries.getLength(); i++)
        {
            registryObjects.clear();
            reg = registries.item(i);
            bindedObject = reg.getNextSibling();
            while(bindedObject != null && bindedObject.getLocalName() != null && bindedObject.getLocalName().equals("bindedobject"))
            {
                obj = new Bindedobject();
                obj.setName(bindedObject.getAttributes().getNamedItem("name").getTextContent());
                obj.setClazz(bindedObject.getAttributes().getNamedItem("class").getTextContent());
                registryObjects.add(obj);
                bindedObject = bindedObject.getNextSibling();
            }
            registry = new Registry();
            reg = reg.getFirstChild();
            registry.setCreateregistry(reg.getTextContent());
            reg = reg.getNextSibling();
            registry.setRegistryaddress(reg.getTextContent());
            reg = reg.getNextSibling();
            registry.setRegistryport(reg.getTextContent());
            rinfo = new RegistryInfo(registry, registryObjects);
            RegistryInfo.registries.add(rinfo);
        }
    }
}
