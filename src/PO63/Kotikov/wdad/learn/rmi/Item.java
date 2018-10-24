package PO63.Kotikov.wdad.learn.rmi;

import java.io.Serializable;

public class Item implements Serializable
{
    private String name;
    private double cost;

    public Item(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }
}
