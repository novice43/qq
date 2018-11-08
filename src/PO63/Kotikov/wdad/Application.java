package PO63.Kotikov.wdad;

import PO63.Kotikov.wdad.learn.rmi.*;
import PO63.Kotikov.wdad.utils.*;

import java.util.Date;

public class Application
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("I'm Kostya Kotikov, and I'm not a monkey");
        Server server = new Server();
        server.main(null);
    }
}
