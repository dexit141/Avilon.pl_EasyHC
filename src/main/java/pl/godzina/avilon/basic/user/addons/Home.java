package pl.godzina.avilon.basic.user.addons;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;
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
    public String isClaimed(Player p) {
        if (this.location != null) {
            return ChatHelper.fixColor(!owner.getPlayer().hasPermission("avilon.youtuber") ? "&aZajęty &8( &fX: &d" + ChatHelper.round(location.getX(), 2) + " &fY: &d" + ChatHelper.round(location.getY(), 2) + " &fZ: &d" + ChatHelper.round(location.getZ(), 2) + " &FBiom: &d" + location.getBlock().getBiome() + "&8 )" : "&cUKRYTE &8( &fdla bezpieczenstwa twojej bazy :) &8)");
        } else {
            return "Miejsce wolne";
        }
    }
}
