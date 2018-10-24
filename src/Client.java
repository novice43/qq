import PO63.Kotikov.wdad.learn.rmi.Officiant;

import java.sql.Date;
import java.time.LocalDate;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        PO63.Kotikov.wdad.learn.rmi.Client client = new PO63.Kotikov.wdad.learn.rmi.Client();
        client.main(null);
        client.getRemoteObject().changeOfficiantName(new Officiant("Test", "Quest"), new Officiant("jack", "black"));
        client.getRemoteObject().earningsTotal(new Officiant("kek", "pek"), Date.valueOf(LocalDate.of(2010, 12, 1)));
        client.getRemoteObject().getOrders(Date.valueOf(LocalDate.of(2010, 12, 1)));
        client.getRemoteObject().lastOfficiantWorkDate(new Officiant("kek", "pek"));
        client.getRemoteObject().removeDay(Date.valueOf(LocalDate.of(2010, 12, 1)));
    }
}
