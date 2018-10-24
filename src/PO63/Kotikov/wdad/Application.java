package PO63.Kotikov.wdad;

import PO63.Kotikov.wdad.learn.rmi.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application
{
    private static String test = "got";
    public static void main(String[] args) throws Exception
    {
        System.out.println("I'm Kostya Kotikov, and I'm not a monkey");
        Server server = new Server();
        server.main(null);
        List<Order> orderList = new ArrayList<>();
        Officiant first = new Officiant("Test", "Quest");
        Officiant second = new Officiant("Math", "Sucks");
        List<Item> items = new ArrayList<>();
        items.add(new Item("Cola", 777));
        items.add(new Item("Cola zero", 1000));
        orderList.add(new Order(first, items));
        Date currentDate;
        currentDate = new Date(java.sql.Date.valueOf(LocalDate.of(2010, 10, 2)), orderList);
        items = new ArrayList<>(items);
        items.add(new Item("Requiem for a fish", 1));
        orderList.add(new Order(second, items));
        Date currentDates = new Date(java.sql.Date.valueOf(LocalDate.of(2016, 10, 2)), orderList);
    }
}
