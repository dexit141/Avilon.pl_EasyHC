package pl.godzina.avilon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.admin.GodCommand;

public class EntityDamageByEntityListener implements Listener {
    private final AvilonPlugin plugin;

    public EntityDamageByEntityListener(AvilonPlugin plugin) {
        this.plugin = plugin;
    }

    public void onGodHit(EntityDamageByEntityEvent e) {
        Player p = (Player)e.getEntity();
        if (GodCommand.god.contains(p)) {
            e.setCancelled(true);
        }
    }
}
