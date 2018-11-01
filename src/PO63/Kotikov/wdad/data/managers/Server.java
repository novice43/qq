
package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.utils.RegistryInfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "registryOrBindedobject"
})
@XmlRootElement(name = "server")
public class Server {

    @XmlElements({
        @XmlElement(name = "registry", required = true, type = Registry.class),
        @XmlElement(name = "bindedobject", required = true, type = Bindedobject.class)
    })
    protected List<Object> registryOrBindedobject;

    /**
     * Gets the value of the registryOrBindedobject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registryOrBindedobject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegistryOrBindedobject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Registry }
     * {@link Bindedobject }
     * 
     * 
     */
    public List<Object> getRegistryOrBindedobject() {
        if (registryOrBindedobject == null) {
            registryOrBindedobject = new ArrayList<Object>();
        }
        return this.registryOrBindedobject;
    }

    public void addBindedObject(String name, String className, Registry registry)
    {
        for(RegistryInfo rinfo : RegistryInfo.registries)
            if(rinfo.equalsByRegistry(registry))
                rinfo.addBindedObject(name, className);
    }

    public void addBindedObject(String name, String className)
    {
        RegistryInfo rinfo = RegistryInfo.registries.get(RegistryInfo.registries.size()-1);
        rinfo.addBindedObject(name, className);
    }

    public void removeBindedObject(String name)
    {
        for(RegistryInfo rinfo : RegistryInfo.registries)
            rinfo.removeBindedObject(name);
    }

    public boolean removeBindedObject(String name, Registry registry)
    {
        for(RegistryInfo rinfo : RegistryInfo.registries)
        {
            if (rinfo.equalsByRegistry(registry))
            {
                rinfo.removeBindedObject(name);
                return true;
            }
        }
        return false;
    }

}
