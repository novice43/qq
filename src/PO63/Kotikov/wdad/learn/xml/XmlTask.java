package PO63.Kotikov.wdad.learn.xml;

import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import java.util.Calendar;
import java.util.List;

import static java.security.Security.getProperty;

public class XmlTask
{
    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    private Restaurant restaurant;

    private String filename;

    public XmlTask(String filename) throws Exception
    {
        this.filename = filename;
        restaurant = (Restaurant)loadObjectFromXML(filename, Restaurant.class);
    }

    public static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        System.setProperty("javax.xml.accessExternalDTD", "all");
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(sr);
    }

    public static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    public double earningsTotal(String officiantSecondName, Calendar calendar)
    {
        double total = 0.0;
        Date comparingDate = Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), null);
        System.out.println(comparingDate.toString());
        for(Order ord : restaurant.getDate(comparingDate).getOrdersByOfficiantSecondName(officiantSecondName))
            total += ord.totalcost;
        return total == 0.0 ? -1 : total;
    }
    public void removeDay(Calendar calendar)
    {
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        restaurant.date.remove(restaurant.getDate(Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), null)));
    }
    public void changeOfficiantName(Officiant oldName, Officiant newName)
    {
        restaurant.changeOfficiantName(oldName, newName);
    }

    public void save() throws Exception
    {
        XmlTask.saveObjectToXML(filename, Restaurant.class, restaurant);
    }

    public List<Order> getOrders(java.util.Date date)
    {
        return restaurant.getOrders(date);
    }

    public java.util.Date lastOfficiantWorkDate(Officiant officiant)
    {
        java.util.Date current;
        List<java.util.Date> d = restaurant.getDatesByOfficiantUtilDate(officiant);
        if(d != null && d.size() != 0) current = d.get(0);
        else return null;
        for(java.util.Date date : d)
        {
            if(date.after(current))
                current = date;
        }
        return current;
    }
}
