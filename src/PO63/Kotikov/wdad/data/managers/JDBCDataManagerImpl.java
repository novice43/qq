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

    private Map<Integer, Object[]> executePreparedQuery(String query, Object[] params, AnswerDescription description) throws Exception
    {
        Connection conn = dataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(query);
        for(int i = 0; i < params.length; i++)
            stat.setObject(i+1, params[i]);
        ResultSet resultSet = null;
        List<Object> objs = new ArrayList<>(description.getColumnCount());
        Map<Integer, Object[]> answer = new HashMap<>();
        switch (description.getType())
        {
            case SELECT:
                resultSet = stat.executeQuery();
                int currentRow = 1;
                while(resultSet != null && resultSet.next())
                {
                    for (int i = 1; i <= description.getColumnCount(); i++)
                        objs.add(resultSet.getObject(i));
                    answer.put(currentRow, objs.toArray());
                    currentRow++;
                    objs.clear();
                }
                //while(resultSet != null && resultSet.next())
                  //  objs.add(resultSet.getObject(1));
                break;
            case UPDATE:
                objs.add(stat.executeUpdate());
                answer.put(1, objs.toArray());
                break;
        }
        conn.close();
        return answer;
    }

    @Override
    public double earningsTotal(Officiant officiant, Date date) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<Integer, Object[]> resultSet = executePreparedQuery(earningsTotalQuery,
                new Object[] { sdf.format(date), officiant.getFirstname(), officiant.getSecondname() },
                new AnswerDescription(1, QueryType.SELECT));
        double result = 0.0;
        Object[] ans;
        for(int i = 1; resultSet.containsKey(i); i++)
        {
            ans = resultSet.get(i);
            for(Object d : ans)
            result += (double)d;
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
        Officiant officiant;
        Order order;
        Item item;
        boolean exists;
        Object[] current;
        List<Order> orders = new ArrayList<>();
        Map<Integer, Object[]> answer = executePreparedQuery(getOrdersQuery, new Object[] { sdf.format(date) },
                new AnswerDescription(6, QueryType.SELECT));
        for(int i = 1; answer.containsKey(i); i++)
        {
            current = answer.get(i);
            officiant = new Officiant();
            officiant.setFirstname((String)current[0]);
            officiant.setSecondname((String)current[1]);
            item = new Item();
            item.setName((String)current[2]);
            item.setCost((double)current[4]);
            order = new Order();
            for(long j = 0; j < (long)current[5]; j++)
            {
                order.setOfficiant(officiant);
                order.getItem().add(item);
            }
            order.countTotalCost();
            exists = false;
            for(Order ord : orders)
                if(ord.getOfficiant().equals(officiant))
                {
                    ord.getItem().addAll(order.getItem());
                    ord.countTotalCost();
                    exists = true;
                }
            if(!exists) orders.add(order);
        }
        return orders;
    }

    @Override
    public Date lastOfficiantWorkDate(Officiant officiant) throws Exception
    {
        Map<Integer, Object[]> obj = executePreparedQuery(lastOfficiantWorkDateQuery,
                new Object[] {officiant.getFirstname(), officiant.getSecondname()},
                new AnswerDescription(1, QueryType.SELECT));
        if(obj.containsKey(1)) return (Date)obj.get(1)[0];
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
