package PO63.Kotikov.wdad.learn.xml;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestXmlTask
{
    public static void main(String[] args) throws Exception
    {
        XmlTask task = new XmlTask();
        Restaurant res = task.getRestaurant();
        task.changeOfficiantName("petya", "petrov", "vasya", "vasilenko");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse("07.12.2006");
        cal.setTime(date);
        int total = task.earningsTotal("vasilenko", cal);
        task.removeDay(cal);
    }
}
