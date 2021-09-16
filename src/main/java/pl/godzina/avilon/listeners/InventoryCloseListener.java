package pl.godzina.avilon.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.basic.user.addons.CustomEnderchest;

public class InventoryCloseListener implements Listener {

    private final AvilonPlugin plugin;

    public InventoryCloseListener(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        User user = this.plugin.getUserManager().getUser(p);

        CustomEnderchest enderchest = user.getEnderchest(ChatColor.stripColor(e.getInventory().getName()));

        if (enderchest == null) return;

        enderchest.closeEnderchest(e.getInventory());
//        enderchest.setNeedUpdate(true);
    }
}
