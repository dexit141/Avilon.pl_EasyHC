package pl.godzina.avilon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.nametag.TagManager;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.basic.user.UserManager;

public class PlayerJoinAndQuitListener implements Listener {
    private AvilonPlugin plugin;

    public PlayerJoinAndQuitListener(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        User user = plugin.getInstance().getUserManager().getUser(p);

        if (user == null) {
            AvilonPlugin.getInstance().getUserManager().createUser(p);
        }
        TagManager tagManager = new TagManager();
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
