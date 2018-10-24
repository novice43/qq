package PO63.Kotikov.wdad.learn.rmi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Order implements Serializable
{
    Officiant officiant;
    List<Item> items;

    public Order(Officiant officiant, Item[] items)
    {
        this(officiant, Arrays.asList(items));
    }

    public Order(Officiant officiant, List<Item> items)
    {
        this.officiant = officiant;
        this.items = items;
    }

    public Officiant getOfficiant()
    {
        return officiant;
    }

    public void setOfficiant(Officiant officiant)
    {
        this.officiant = officiant;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }

    public boolean byOfficiant(Officiant officiant)
    {
        return this.officiant.equals(officiant);
    }

    public boolean byOfficiant(String firstname, String secondname)
    {
        return this.officiant.getFirstname().equals(firstname) && this.officiant.getSecondname().equals(secondname);
    }

    public double getTotalCost()
    {
        double totalCost = 0.0;
        for(Item item : items)
            totalCost += item.getCost();
        return totalCost;
    }
}
