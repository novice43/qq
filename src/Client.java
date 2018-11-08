import PO63.Kotikov.wdad.learn.xml.*;

import java.util.List;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        PO63.Kotikov.wdad.learn.rmi.Client client = new PO63.Kotikov.wdad.learn.rmi.Client();
        client.main(null);
        Officiant s = Officiant.newInstance("Константин", "Симакин");
        client.getRemoteObject().changeOfficiantName(Officiant.newInstance("Вадим", "Усинов"), Officiant.newInstance("Kirk", "Hammett"));
        System.out.println(client.getRemoteObject().earningsTotal(s, Date.newInstance(22, 11, 2018, null).getDate()));
        List<Order> oo = client.getRemoteObject().getOrders(Date.newInstance(22, 11, 2018, null).getDate());
        for(Order o : oo)
            System.out.println(o.toString());
        System.out.println(client.getRemoteObject().lastOfficiantWorkDate(s));
        client.getRemoteObject().removeDay(Date.newInstance(5, 11, 2018, null).getDate());
        client.getRemoteObject().save();
    }
}
