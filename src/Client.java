import PO63.Kotikov.wdad.learn.xml.*;

import java.util.List;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        PO63.Kotikov.wdad.learn.rmi.Client client = new PO63.Kotikov.wdad.learn.rmi.Client();
        client.main(null);
        Officiant s = Officiant.newInstance("petya", "petrov");
        client.getRemoteObject().changeOfficiantName(Officiant.newInstance("Test", "Quest"), Officiant.newInstance("jack", "black"));
        System.out.println(client.getRemoteObject().earningsTotal(s, Date.newInstance(7, 12, 2006, null).getDate()));
        List<Order> oo = client.getRemoteObject().getOrders(Date.newInstance(7, 12, 2006, null).getDate());
        for(Order o : oo)
            System.out.println(o.toString());
        client.getRemoteObject().lastOfficiantWorkDate(s);
        client.getRemoteObject().removeDay(Date.newInstance(7, 12, 2006, null).getDate());
        client.getRemoteObject().save();
    }
}
