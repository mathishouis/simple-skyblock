package fr.kozen.skyrama.storage;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SqlManager {

    private String user = "root";
    private String host = "localhost";
    private String name = "skyrama";
    private String password = "";
    private int port = 3306;
    private Connection connection;
    private MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();

    public SqlManager() { this.initialise(); };

    public void initialise() {

        dataSource.setServerName(host);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(name);
        dataSource.setUser(user);
        dataSource.setPassword(password);

    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
