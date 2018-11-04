package PO63.Kotikov.wdad.utils;

public class DBInnerQuery
{

    private String query;

    private final String acceptorTable;

    private final String joinerTable;

    private final String condition;

    public DBInnerQuery(String acceptorTable, String joinerTable, String condition)
    {
        this.acceptorTable = acceptorTable;
        this.joinerTable = joinerTable;
        this.condition = condition;
    }

    public String getInnerQuery()
    {
        query = (condition == null || condition.isEmpty()) ?
                String.format("(%s INNER JOIN %s)", acceptorTable, joinerTable) :
                String.format("(%s INNER JOIN %s ON %s)", acceptorTable, joinerTable, condition);
        return query;
    }

    public String getInnerQuery(DBCondition condition)
    {
        query = String.format("(%s INNER JOIN %s ON %s)", acceptorTable, joinerTable, condition.toString());
        return query;
    }

    public String getInnerQuery(String condition)
    {
        query = String.format("(%s INNER JOIN %s ON %s)", acceptorTable, joinerTable, condition);
        return query;
    }
}
