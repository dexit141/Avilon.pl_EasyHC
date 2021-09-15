package pl.godzina.avilon.basic.user;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private final Map<UUID, User> users = new HashMap<>();
    private final AvilonPlugin plugin;

    public UserManager(AvilonPlugin plugin) {
        this.plugin = plugin;
    }
    public User getUser(UUID uuid) {
        return this.users.get(uuid);
    }
    public User getUser(Player player) {
        return this.users.get(player.getUniqueId());
    }
    public User createUser(Player p) {
        User u = new User(p);
        users.put(p.getUniqueId(), u);
        return u;
    }

}
