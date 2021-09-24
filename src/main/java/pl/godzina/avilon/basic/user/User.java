package pl.godzina.avilon.basic.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.drop.Drop;
import pl.godzina.avilon.basic.guild.Guild;
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
    private boolean vanish, incognito, cobbleDrop, messagesDrop, god;
    private int godApple, goldenApple, pearl, snowBall, eggs;
    private int stars;
    private Guild guild;

    private List<CustomEnderchest> cec = new ArrayList<>();
    private List<Home> homes = new ArrayList<>();
    private final List<Drop> disabledDrops = new ArrayList<>();

    public User(Player p) {
        this.uuid = p.getUniqueId();

        this.name = p.getName();
        this.stars = 1000;

        this.ip = p.getAddress().getAddress().getHostAddress();

//        this.godApple = 0;
//        this.goldenApple = 0;
//        this.pearl = 0;
//        this.snowBall = 0;
//        this.eggs = 0;

        //Vanish oraz Incognito

        this.vanish = false;
        this.incognito = false;
        this.god = false;
        

        this.setupAddons();
    }

    public User(ResultSet rs) throws SQLException {
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.ip = rs.getString("ip");
        this.name = rs.getString("name");
//        this.godApple = rs.getInt("godApple");
//        this.goldenApple = rs.getInt("goldenApple");
//        this.pearl = rs.getInt("pearl");
//        this.snowBall = rs.getInt("snowBall");
//        this.eggs = rs.getInt("eggs");
        this.stars = rs.getInt("stars");
    }

    public void setupAddons() {
        this.cec.add(new CustomEnderchest("Enderchest #1", this, "avilon.enderchest1", 20));
        this.cec.add(new CustomEnderchest("Enderchest #2", this, "avilon.enderchest2", 21));
        this.cec.add(new CustomEnderchest("Enderchest #3", this, "avilon.enderchest3", 22));
        this.cec.add(new CustomEnderchest("Enderchest #4", this, "avilon.enderchest4", 23));
        this.cec.add(new CustomEnderchest("Enderchest #5", this, "avilon.enderchest5", 24));

        this.homes.add(new Home("Home #1", this,"avilon.home1", 20));
        this.homes.add(new Home("Home #2", this,"avilon.home2", 21));
        this.homes.add(new Home("Home #3", this, "avilon.home3", 22));
        this.homes.add(new Home("Home #4", this, "avilon.home4", 23));
        this.homes.add(new Home("Home #5", this, "avilon.home5", 24));
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
    public boolean isDisabledDrop(Drop drop) {
        return this.disabledDrops.contains(drop);
    }

    public void addDisabledDrop(Drop drop) {
        this.disabledDrops.add(drop);
    }

    public void removeDisabledDrop(Drop drop) {
        this.disabledDrops.remove(drop);
    }
}
