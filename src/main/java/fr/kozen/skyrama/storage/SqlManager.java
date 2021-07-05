package fr.kozen.skyrama.storage;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import fr.kozen.skyrama.Skyrama;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlManager {

    private String user = (String) Skyrama.getPlugin(Skyrama.class).getConfig().get("mysql.user");
    private String host = (String) Skyrama.getPlugin(Skyrama.class).getConfig().get("mysql.host");
    private String name = (String) Skyrama.getPlugin(Skyrama.class).getConfig().get("mysql.name");
    private String password = (String) Skyrama.getPlugin(Skyrama.class).getConfig().get("mysql.password");
    private int port = (int) Skyrama.getPlugin(Skyrama.class).getConfig().get("mysql.port");
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
