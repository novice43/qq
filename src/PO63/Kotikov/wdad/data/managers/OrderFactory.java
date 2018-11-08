package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.learn.xml.Item;
import PO63.Kotikov.wdad.learn.xml.Officiant;
import PO63.Kotikov.wdad.learn.xml.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFactory
{
    public static Order newInstance(Officiant officiant, List<Item> items)
    {
        Order order = new Order();
        order.setOfficiant(officiant);
        order.getItem().addAll(items);
        order.countTotalCost();
        return order;
    }

    public static Order newInstance(List<Object> rowData)
    {
        Officiant officiant = Officiant.newInstance((String)rowData.get(0), (String)rowData.get(1));
        Item currentItem = Item.newInstance((String)rowData.get(2), (double)rowData.get(4));
        List<Item> items = new ArrayList<>();
        for(long i = 0; i < (long)rowData.get(5); i++)
            items.add(currentItem);
        return newInstance(officiant, items);
    }

    public static List<Order> createOrderList(List<List<Object>> rowData)
    {
        Order currentOrder;
        List<Order> orders = new ArrayList<>();
        boolean officiantAlreadyAdded;
        for(List<Object> objects : rowData)
        {
            officiantAlreadyAdded = false;
            currentOrder = newInstance(objects);
            for(Order ord : orders)
            {
                if(ord.getOfficiant().equals(currentOrder.getOfficiant()))
                {
                    ord.getItem().addAll(currentOrder.getItem());
                    ord.countTotalCost();
                    officiantAlreadyAdded = true;
                    break;
                }
            }
            if(!officiantAlreadyAdded)
            {
                orders.add(currentOrder);
            }
        }
        return orders;
    }
}
