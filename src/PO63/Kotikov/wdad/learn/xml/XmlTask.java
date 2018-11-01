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

import static java.security.Security.getProperty;

public class XmlTask
{
    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    private Restaurant restaurant;

    public XmlTask() throws Exception
    {
        restaurant = (Restaurant)loadObjectFromXML("rest.xml", Restaurant.class);
    }

    static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        System.setProperty("javax.xml.accessExternalDTD", "all");
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Object)unmarshaller.unmarshal(sr);
    }

    public static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
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
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName)
    {
        for(Date date : restaurant.date)
            //todo Я ТЕБЯ НЕНАВИЖУ!
            for(Order order : date.order)
                if(order.officiant.firstname.equals(oldFirstName) && order.officiant.secondname.equals(oldSecondName))
                {
                    order.officiant.firstname = newFirstName;
                    order.officiant.secondname = newSecondName;
                }
    }


}
