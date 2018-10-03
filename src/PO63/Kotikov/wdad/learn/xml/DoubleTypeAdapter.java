package PO63.Kotikov.wdad.learn.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleTypeAdapter extends XmlAdapter<String, Double>
{
    @Override
    public Double unmarshal(String v) throws Exception
    {
        if(v == null) return null;
        return Double.parseDouble(v);
    }

    @Override
    public String marshal(Double v) throws Exception
    {
        if(v == null) return null;
        return v.toString();
    }
}
