package PO63.Kotikov.wdad.data.storage;

import PO63.Kotikov.wdad.data.managers.Datasource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory
{

    public static DataSource createDataSource() throws Exception
    {
        return new MysqlDataSource();
    }

    public static DataSource createDataSource(String className, String
            driverType, String host, int port, String dbName, String user, String password) throws Exception
    {

        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(host);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        ds.setUser(user);
        ds.setPassword(password);
        return ds;
    }

    public static DataSource createDataSource(Datasource datasource) throws Exception
    {
        return createDataSource(datasource.getClassname(), datasource.getDrivertype(), datasource.getHostName(),
                datasource.getPort(), datasource.getDBName(), datasource.getUser(), datasource.getPass());
    }
}
