package PO63.Kotikov.wdad.utils;

import PO63.Kotikov.wdad.data.managers.Bindedobject;
import PO63.Kotikov.wdad.data.managers.Registry;
import java.util.ArrayList;
import java.util.List;

public class RegistryInfo
{
    public static List<RegistryInfo> registries = new ArrayList<>();//Registry list startIndex -> Registry in global registry list
    public Registry registry;//Registry by its startIndex
    public List<Bindedobject> bindedObjects = new ArrayList<>();

    public RegistryInfo(Registry registry, List<Bindedobject> objects)
    {
        if(objects != null) bindedObjects.addAll(objects);
        this.registry = registry;
    }

    public static void parse(List<Object> objects)
    {
        registries.clear();
        Registry registry;
        Bindedobject bindedobject = null;
        for(Object obj : objects)
            if(obj instanceof Registry)
            {
                registry = (Registry)obj;
                registries.add(new RegistryInfo(registry, null));
            }
            else
            {
                registries.get(registries.size()-1).bindedObjects.add((Bindedobject)obj);
            }
    }

    public static List<Object> getObjectList()
    {
        List<Object> result = new ArrayList<>();
        for(RegistryInfo rinfo : registries)
        {
            result.add(rinfo.registry);
            result.addAll(rinfo.bindedObjects);
        }
        return result;
    }

    public void addBindedObject(String name, String className)
    {
        Bindedobject obj = new Bindedobject();
        obj.setName(name);
        obj.setClazz(className);
        bindedObjects.add(obj);
    }

    public void removeBindedObject(String name)
    {
        Bindedobject obj;
        for(int i = 0; i < bindedObjects.size(); i++)
        {
            obj = bindedObjects.get(i);
            if (obj.getName().equals(name))
            {
                bindedObjects.remove(i);
                i--;
            }
        }
    }

    public boolean equalsByRegistry(Registry registry)
    {
        return this.registry.equals(registry);
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof RegistryInfo && ((RegistryInfo) obj).registry.equals(registry);
    }

    @Override
    public int hashCode()
    {
        return registries.hashCode() ^ registry.hashCode() ^ bindedObjects.hashCode();
    }
}
