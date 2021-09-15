package pl.godzina.avilon.basic.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.addons.CustomEnderchest;
import pl.godzina.avilon.basic.user.addons.Home;
import pl.godzina.avilon.helpers.LocationHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {
    private final UUID uuid;
    private String name, ip;



    private List<CustomEnderchest> cec = new ArrayList<>();
    private List<Home> homes = new ArrayList<>();

    public User(Player p) {
        this.uuid = p.getUniqueId();
        this.name = p.getName();
        this.ip = p.getAddress().getAddress().getHostAddress();
        this.cec.add(new CustomEnderchest("Enderchest #1", this, "avilon.enderchest1", 20));
        this.cec.add(new CustomEnderchest("Enderchest #2", this, "avilon.enderchest2", 21));
        this.cec.add(new CustomEnderchest("Enderchest #3", this, "avilon.enderchest3", 22));
        this.cec.add(new CustomEnderchest("Enderchest #4", this, "avilon.enderchest4", 23));
        this.cec.add(new CustomEnderchest("Enderchest #5", this, "avilon.enderchest5", 24));
        this.homes.add(new Home("Home #1", this,"avilon.home3", 20));
        this.homes.add(new Home("Home #2", this,"avilon.home3", 21));
        this.homes.add(new Home("Home #3", this, "avilon.home3", 22));
        this.homes.add(new Home("Home #4", this, "avilon.home4", 23));
        this.homes.add(new Home("Home #5", this, "avilon.home5", 24));
    }

    public User(ResultSet rs) throws SQLException {
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.ip = rs.getString("ip");
        this.name = rs.getString("name");
    }
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
    public CustomEnderchest getEnderchest(String name) {
        for (CustomEnderchest enderchest : this.getCec()) {
            if (enderchest.getName().equalsIgnoreCase(name)) return enderchest;
        }
        return null;
    }
    public Home getHome(String name) {
        for (Home home : this.homes) {
            if (home.getName().equalsIgnoreCase(name) && home.getOwner().equals(this))
                return home;
        }
        return null;
    }
}
