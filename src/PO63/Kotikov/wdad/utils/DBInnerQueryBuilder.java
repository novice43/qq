package PO63.Kotikov.wdad.utils;

public class DBInnerQueryBuilder
{
    private StringBuilder sb = new StringBuilder();

    public DBInnerQueryBuilder append(DBInnerQuery query)
    {
        sb.append(query.getInnerQuery());
        if(sb.length() != 0)
        {
            sb.insert(0, '(');
            sb.insert(sb.length()-1, ')');
        }
        return this;
    }

    @Override
    public String toString()
    {
        return sb.toString();
    }
}
