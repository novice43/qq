package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.data.storage.DataSourceFactory;
import PO63.Kotikov.wdad.learn.xml.Item;
import PO63.Kotikov.wdad.learn.xml.Officiant;
import PO63.Kotikov.wdad.learn.xml.Order;
import PO63.Kotikov.wdad.utils.*;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class JDBCDataManagerImpl implements DataManager
{
    private DataSource dataSource;

    /**
     * Needs 3 params into an object array:
     * [0] - date
     * [1] - firstname
     * [2] - secondname
     */
    private static final String earningsTotalQuery = "SELECT items.cost*items_orders.quantity FROM ((" +
            "(officiants INNER JOIN orders ON (orders.officiant_id = officiants.id) AND (orders.date = ?))" +
            " INNER JOIN items_orders ON items_orders.orders_id = orders.id)" +
            " INNER JOIN items ON items_orders.items_dictionary_id = items.id)" +
            " WHERE (officiants.firstname = ?) AND (officiants.secondname = ?)";

    private static final String removeDayQuery = "DELETE FROM orders WHERE orders.date = ?";

    /**
     * Needs 4 params into an object array:
     * [0] - new firstname
     * [1] - new secondname
     * [2] - old firstname
     * [3] - old secondname
     */
    private static final String changeOfficiantNameQuery = "UPDATE officiants SET" +
            " firstname = ?, secondname = ? WHERE (firstname = ?) AND (secondname = ?)";

    /**
     * Needs a param into an object array:
     * [0] - date
     */
    private static final String getOrdersQuery = "SELECT officiants.firstname, officiants.secondname," +
            " items.name, items.description, items.cost, items_orders.quantity FROM ((" +
            "(officiants INNER JOIN orders ON (orders.date = ?) AND (orders.officiant_id = officiants.id))" +
            " INNER JOIN items_orders ON items_orders.orders_id = orders.id)" +
            " INNER JOIN items ON items.id = items_orders.items_dictionary_id)";
    /**
     * Needs two param into an object array:
     * [0] - firstname
     * [1] - secondname
     */
    private static final String lastOfficiantWorkDateQuery = "SELECT MAX(orders.date) FROM" +
            " (orders INNER JOIN officiants ON" +
            " orders.officiant_id = officiants.id AND officiants.firstname = ? AND officiants.secondname = ?)";

    private ResultSet executeQuery(String query) throws Exception
    {
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery(query);
        conn.close();
        return resultSet;
    }

    private List<List<Object>> executePreparedQuery(String query, Object[] params, AnswerDescription description) throws Exception
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(query);
        for(int i = 0; i < params.length; i++)
            stat.setObject(i+1, params[i]);
        ResultSet resultSet;
        List<Object> objs = new ArrayList<>(description.getColumnCount());
        List<List<Object>> answer = new ArrayList<List<Object>>();
        switch (description.getType())
        {
            case SELECT:
                resultSet = stat.executeQuery();
                while(resultSet != null && resultSet.next())
                {
                    for (int i = 1; i <= description.getColumnCount(); i++)
                        objs.add(resultSet.getObject(i));
                    answer.add(objs);
                    objs = new ArrayList<>(description.getColumnCount());
                }
                break;
            case UPDATE:
                objs.add(stat.executeUpdate());
                answer.add(objs);
                break;
        }
        conn.close();
        return answer;
    }

    @Override
    public double earningsTotal(Officiant officiant, Date date) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<List<Object>> resultSet = executePreparedQuery(earningsTotalQuery,
                new Object[] { sdf.format(date), officiant.getFirstname(), officiant.getSecondname() },
                new AnswerDescription(1, QueryType.SELECT));
        double result = 0.0;
        for(List<Object> list : resultSet)
        {
            for(Object obj : list)
            {
                result += (double)obj;
            }
        }
        return result;
    }

    @Override
    public void removeDay(Date date) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        executePreparedQuery(removeDayQuery, new Object[]{sdf.format(date)},
                new AnswerDescription(0, QueryType.UPDATE));
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception
    {
        executePreparedQuery(changeOfficiantNameQuery,
                new Object[]{newOfficiant.getFirstname(), newOfficiant.getSecondname(),
                        oldOfficiant.getFirstname(), oldOfficiant.getSecondname()},
                new AnswerDescription(0, QueryType.UPDATE));
    }

    @Override
    public List<Order> getOrders(Date date) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<List<Object>> answer = executePreparedQuery(getOrdersQuery, new Object[] { sdf.format(date) },
                new AnswerDescription(6, QueryType.SELECT));
        return OrderFactory.createOrderList(answer);
    }

    @Override
    public Date lastOfficiantWorkDate(Officiant officiant) throws Exception
    {
        List<List<Object>> obj = executePreparedQuery(lastOfficiantWorkDateQuery,
                new Object[] {officiant.getFirstname(), officiant.getSecondname()},
                new AnswerDescription(1, QueryType.SELECT));
        if(obj.size() == 1 && obj.get(0).size() == 1) return (Date)obj.get(0).get(0);
        return null;
    }

    @Override
    public void save() throws Exception
    {

    }

    public JDBCDataManagerImpl(Datasource datasource) throws Exception
    {
        dataSource = DataSourceFactory.createDataSource(datasource);
    }
}
