package pl.godzina.avilon.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;

public class BlockBreakListener implements Listener {
    private AvilonPlugin plugin;

    public BlockBreakListener(AvilonPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, (Plugin)plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (e.isCancelled())
            return;
        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            if (b.getType() == Material.STONE) {
                this.plugin.getDropManager().breakBlock(b, p, p.getItemInHand());
                p.giveExp(3);
                e.setCancelled(true);
            } else {
                return;
            }
        } else {
            return;
        }
    }
}
