package pl.godzina.avilon.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.godzina.avilon.AvilonPlugin;

public class PlayerMoveListener implements Listener {
    private AvilonPlugin plugin;

    public PlayerMoveListener(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location locFrom = event.getFrom(), locTo = event.getTo();
        if (locFrom.getBlockX() == locTo.getBlockX() && locFrom.getBlockZ() == locTo.getBlockZ())
            return;
        if (!this.plugin.getTeleportManager().getTeleports().containsKey(player.getUniqueId()))
            return;
        this.plugin.getTeleportManager().cancelTeleport(player);
    }
}
