package PO63.Kotikov.wdad.data.managers;

import PO63.Kotikov.wdad.data.storage.DataSourceFactory;
import PO63.Kotikov.wdad.learn.xml.Officiant;
import PO63.Kotikov.wdad.learn.xml.Order;
import PO63.Kotikov.wdad.utils.DBCondition;
import PO63.Kotikov.wdad.utils.DBConditionType;
import PO63.Kotikov.wdad.utils.DBInnerQuery;
import PO63.Kotikov.wdad.utils.DBSelect;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class JDBCDataManagerImpl implements DataManager
{
    private DataSource dataSource;

    @Override
    public double earningsTotal(Officiant officiant, Date calendar) throws Exception
    {
        Connection conn = dataSource.getConnection();
        Statement stat = conn.createStatement();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT items.cost FROM (((officiants INNER JOIN orders ON (orders.officiant_id = officiants.id) AND (orders.date = '").
                append(sdf.format(calendar)).append("'))").
                append(" INNER JOIN items_orders ON items_orders.orders_id = orders.id)").
                append(" INNER JOIN items ON items_orders.items_dictionary_id = items.id)").
                append(" WHERE (officiants.firstname = '").append(officiant.getFirstname()).append("') AND").
                append(" (officiants.secondname = '").append(officiant.getSecondname()).append("')");
        System.out.println(sb.toString());
        ResultSet resultSet = stat.executeQuery(sb.toString());
        double result = 0.0;
        while(resultSet.next())
            result += resultSet.getDouble(1);
        conn.close();
        return result;
    }

    @Override
    public void removeDay(Date calendar) throws RemoteException
    {

    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) throws Exception
    {

    }

    @Override
    public List<Order> getOrders(Date date) throws RemoteException
    {
        return null;
    }

    @Override
    public Date lastOfficiantWorkDate(Officiant officiant) throws RemoteException
    {
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
