package PO63.Kotikov.wdad.utils;

import PO63.Kotikov.wdad.data.managers.Registry;

import java.util.ArrayList;
import java.util.List;

public class RegistryInfo
{
    public static List<RegistryInfo> registries = new ArrayList<RegistryInfo>();//Registry list startIndex -> Registry in global registry list
    private int startIndex;//Index in global registry list
    public int lastIndex;
    public Registry registry;//Registry by its startIndex

    private RegistryInfo(int startIndex, Registry registry) throws Exception
    {
        if(registry == null) throw new Exception("Null registry cannot be added to registry list");
        this.startIndex = startIndex;
        this.registry = registry;
        this.lastIndex = -1;
    }

    /**
     * Pushing registry and its indices in list. If lastIndex == -1 that means real lastIndex points to the end of the list
     * @param objects list of some objects where are some registries
     * @throws Exception something go wrong like nullpointer etc...
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
                registries.add(new RegistryInfo(i, (Registry) current));
            }
        }
    }
}
