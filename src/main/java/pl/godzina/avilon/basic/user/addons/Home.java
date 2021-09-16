package pl.godzina.avilon.basic.user.addons;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.LocationHelper;

import javax.persistence.SqlResultSetMapping;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
@Getter
@Setter
public class Home {

    private final User owner;
    private final String name;
    private Location location;
    private final String permission;
    private final int slots;

    public Home(String name, User owner, String permission, int slots) {
        this.owner = owner;
        this.slots = slots;
        this.name = name;
        this.permission = permission;
    }
    public Home(ResultSet rs) throws SQLException {
        this.owner = AvilonPlugin.getInstance().getUserManager().getUser(UUID.fromString(rs.getString("owner")));
        this.location = LocationHelper.locFromString(rs.getString("location"));
        this.slots = rs.getInt("slots");
        this.permission = rs.getString("permission");
        this.name = rs.getString("name");
    }
}
