package pl.godzina.avilon.data.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
public class User {
    private final UUID uuid;
    private String name, ip;



    public User(Player p) {
        this.uuid = p.getUniqueId();
        this.name = p.getName();
        this.ip = p.getAddress().getAddress().getHostAddress();
    }
    public User(ResultSet rs) throws SQLException {
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.ip = rs.getString("ip");
        this.name = rs.getString("name");
    }
}
