package PO63.Kotikov.wdad.utils;

public class AnswerDescription
{
    private int columnCount;
    private QueryType type;

    public AnswerDescription(int columnCount, QueryType type)
    {
        this.columnCount = columnCount;
        this.type = type;
    }

    public int getColumnCount()
    {
        return columnCount;
    }

    public void setColumnCount(int columnCount)
    {
        this.columnCount = columnCount;
    }

    public QueryType getType()
    {
        return type;
    }

    public void setType(QueryType type)
    {
        this.type = type;
    }
}
