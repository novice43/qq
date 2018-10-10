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

class XmlTask
{
    private static boolean inited;

    Restaurant getRestaurant()
    {
        return restaurant;
    }

    private Restaurant restaurant;

    XmlTask(String filename, Class c) throws Exception
    {
        if(!inited)
        {
            System.setProperty("javax.xml.accessExternalDTD", "all");
            inited = true;
        }
        restaurant = (Restaurant)loadObjectFromXML(filename, c);
    }

    private static Object loadObjectFromXML(String filename, Class c) throws Exception
    {
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(sr);
    }

    static void saveObjectToXML(String filename, Class c, Object obj) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    double earningsTotal(String officiantSecondName, Calendar calendar)
    {
        double total = 0.0;
        Date comparingDate = Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), null);
        for(Order ord : restaurant.getDate(comparingDate).getOrdersByOfficiantSecondName(officiantSecondName))
            total += ord.totalcost;
        return total == 0.0 ? -1 : total;
    }
    void removeDay(Calendar calendar)
    {
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        restaurant.date.remove(restaurant.getDate(Date.newInstance(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR), null)));
    }
    void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName)
    {
        for(Date date : restaurant.date)
            for(Order order : date.order)
                if(order.officiant.firstname.equals(oldFirstName) && order.officiant.secondname.equals(oldSecondName))
                {
                    order.officiant.firstname = newFirstName;
                    order.officiant.secondname = newSecondName;
                }
    }
}
