package pl.godzina.avilon.basic.storage;

import com.zaxxer.hikari.HikariDataSource;
import pl.godzina.avilon.AvilonPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseProvider {

    private final AvilonPlugin plugin;
    private final HikariDataSource hikariDataSource = new HikariDataSource();

    public DatabaseProvider(AvilonPlugin plugin) {

        this.plugin = plugin;

        String host = plugin.getConfig().getString("mysql.host");
        String database = plugin.getConfig().getString("mysql.name");
        String user = plugin.getConfig().getString("mysql.user");
        String password = plugin.getConfig().getString("mysql.password");
        int port = plugin.getConfig().getInt("mysql.port");

        hikariDataSource.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" +
                database + "?characterEncoding=UTF-8&useUnicode=true&useSSL=" + false);
        hikariDataSource.setUsername(user);
        hikariDataSource.setPassword(password);
        hikariDataSource.addDataSourceProperty("cachePrepStmts", "true");
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource.addDataSourceProperty("useServerPrepStmts", true);
        hikariDataSource.addDataSourceProperty("cacheResultSetMetadata", true);
        hikariDataSource.addDataSourceProperty("tcpKeepAlive", true);
        hikariDataSource.setLeakDetectionThreshold(60000L);
        hikariDataSource.setMaximumPoolSize(5);
        hikariDataSource.setMinimumIdle(0);
        hikariDataSource.setIdleTimeout(30000L);
    }

    public void executeUpdate(String query) {
        try (Connection connection = this.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (statement == null)
                return;

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String query) {
        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        try {
            return this.hikariDataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
 
