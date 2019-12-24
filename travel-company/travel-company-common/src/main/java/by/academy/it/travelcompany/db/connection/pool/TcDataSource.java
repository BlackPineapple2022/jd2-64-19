package by.academy.it.travelcompany.db.connection.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class TcDataSource {

    private static DataSource dataSource;

    private TcDataSource() {
    }

    public static void configure(ResourceBundle bundle) throws ClassNotFoundException {
        Class.forName(bundle.getString("db.driver.name"));
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(bundle.getString("db.url"));
        config.setUsername(bundle.getString("db.user.name"));
        config.setPassword(bundle.getString("db.user.password"));
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        check();
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() throws SQLException {
        check();
        return dataSource;
    }

    private static void check() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Datasource is null; Need to call init() first");
        }
    }

}