package PO63.Kotikov.wdad.utils;

public class DBCondition
{
    protected String condition;
    private String leftOperand;
    private String rightOperand;
    private boolean brackets = true;

    public DBCondition(String leftOperand, String rightOperand, boolean brackets)
    {
        this(leftOperand, rightOperand);
        this.brackets = brackets;
    }

    public DBCondition()
    {

    }

    public DBCondition(String condition)
    {
        this.condition = condition;
        this.brackets = false;
    }

    public DBCondition(String leftOperand, Object rightOperand)
    {
        setOperands(leftOperand, rightOperand);
    }

    public boolean isBrackets()
    {
        return brackets;
    }

    public void setBrackets(boolean brackets)
    {
        this.brackets = brackets;
    }

    public void setOperands(String leftOperand, Object rightOperand)
    {
        this.leftOperand = leftOperand;
        if(rightOperand instanceof String)
        {
            this.rightOperand = (String) rightOperand;
            this.brackets = true;
        }
        else if (rightOperand instanceof Number)
        {
            this.rightOperand = String.valueOf(rightOperand);
            this.brackets = false;
        }
    }

    @Override
    public String toString()
    {
        if(leftOperand != null && rightOperand != null) this.condition = String.format("(%s=%3$s%s%3$s)", leftOperand, rightOperand, brackets ? "'" : "");
        return this.condition;
    }

    public DBCondition concat(DBCondition condition, DBConditionType type, boolean grouped)
    {
        return new DBCondition(String.format("%1$s%2$s %3$s %4$s%1$s", (grouped ? "(" : ""), this, type.name(), condition));
    }

    public static class DBConditionBuilder
    {
        private StringBuilder sb = new StringBuilder();

        public void addCondition(DBCondition condition)
        {
            sb.append(condition.toString());
        }

        public void addCondition(String condition)
        {
            sb.append(condition);
        }

        public void addConditionLink(DBConditionType type)
        {
            sb.append(' ').append(type.name()).append(' ');
        }

        public void makeGroup()
        {
            sb.insert(0, '(');
            sb.insert(sb.length(), ')');
        }

        public void clear()
        {
            sb.delete(0, sb.length());
        }

        @Override
        public String toString()
        {
            return sb.toString();
        }
    }
}
