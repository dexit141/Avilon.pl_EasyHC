package pl.godzina.avilon.basic.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.addons.CustomEnderchest;

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

    public User(Player p) {
        this.uuid = p.getUniqueId();
        this.name = p.getName();
        this.ip = p.getAddress().getAddress().getHostAddress();
        this.cec.add(new CustomEnderchest("Enderchest #1", this, "avilon.enderchest", 20));
        this.cec.add(new CustomEnderchest("Enderchest #2", this, "avilon.player", 21));
        this.cec.add(new CustomEnderchest("Enderchest #3", this, "avilon.player", 22));
        this.cec.add(new CustomEnderchest("Enderchest #4", this, "avilon.player", 23));
        this.cec.add(new CustomEnderchest("Enderchest #5", this, "avilon.player", 24));
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
}
