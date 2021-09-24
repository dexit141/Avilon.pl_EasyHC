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
    public void createTables() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_users (" +
                "id int(16) PRIMARY KEY AUTO_INCREMENT," +
                "uuid text NOT NULL," +
                "ip text NOT NULL)");
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_enderchest (" +
                "id int(16) PRIMARY KEY AUTO_INCREMENT," +
                "name text NOT NULL," +
                "owner text NOT NULL," +
                "lore text NOT NULL," +
                "permission text NOT NULL," +
                "slots int NOT NULL)");
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_home (" +
                "id int(16) PRIMARY KEY AUTO_INCREMENT," +
                "owner text NOT NULL," +
                "location text NOT NULL," +
                "permission text NOT NULL," +
                "slots int NOT NULL)");
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_guilds (id int(16) PRIMARY KEY AUTO_INCREMENT,tag varchar(5) NOT NULL,name varchar(32) NOT NULL,owner text NOT NULL,guildHome text NOT NULL,cuboidX int(11) NOT NULL,cuboidZ int(11) NOT NULL,cuboidSize int(11) NOT NULL,lives int(11) NOT NULL,stars int(11) NOT NULL,kills int(11) NOT NULL,deaths int(11) NOT NULL,expireDate bigint NOT NULL,createDate bigint NOT NULL,lastAttackDate bigint NOT NULL)");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_deputies (id int(16) PRIMARY KEY AUTO_INCREMENT,uuid text NOT NULL,guild varchar(5) NOT NULL)");
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_allies (id int(16) PRIMARY KEY AUTO_INCREMENT,guildA varchar(5) NOT NULL,guildB varchar(5) NOT NULL)");
        this.executeUpdate("CREATE TABLE IF NOT EXISTS avilon_members (id int(16) PRIMARY KEY AUTO_INCREMENT,uuid text NOT NULL,guild varchar(5) NOT NULL,build int NOT NULL,destroy int NOT NULL,addSize int NOT NULL,kick int NOT NULL,openChest int NOT NULL,changePvp int NOT NULL,teleport int NOT NULL,manage int NOT NULL,destroyObsidian int NOT NULL,destroyBeacon int NOT NULL,buildTnt int NOT NULL,alliance int NOT NULL,invite int NOT NULL,farmers int NOT NULL,fill int NOT NULL)");
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
 
