package PO63.Kotikov.wdad.utils;

public class DBSelect
{
    /*SELECT items.cost
FROM (((officiants
INNER JOIN orders ON (orders.officiant_id = '1') AND (orders.date > '2018-10-20'))
INNER JOIN items_orders ON items_orders.orders_id = orders.id)
INNER JOIN items ON items_orders.items_dictionary_id = items.id)*/
    private String tagret;
    private String from;
    private String condition;
    private boolean basicVersion = true;
    public DBSelect(String tagret, String from, String condition)
    {
        this.tagret = tagret;
        this.from = from;
        this.condition = condition;
    }

    public String getTagret()
    {
        return tagret;
    }

    public void setTagret(String tagret)
    {
        this.tagret = tagret;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public boolean isBasicVersion()
    {
        return basicVersion;
    }

    public void setBasicVersion(boolean basicVersion)
    {
        this.basicVersion = basicVersion;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ").append(this.tagret).append(" FROM ").append(this.from);
        if (condition != null && !condition.isEmpty())
            if(basicVersion)
                sb.append(" WHERE ").append(condition);
            else
                sb.append(' ').append(condition);
        return sb.toString();
    }
}
