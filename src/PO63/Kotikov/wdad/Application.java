package PO63.Kotikov.wdad;

import PO63.Kotikov.wdad.learn.rmi.Client;
import PO63.Kotikov.wdad.learn.rmi.Server;

public class Application
{
    private static String test = "got";
    public static void main(String[] args) throws Exception
    {
        System.out.println("I'm Kostya Kotikov, and I'm not a monkey");
        Server.main(null);
        Client.main(null);
    }
}
