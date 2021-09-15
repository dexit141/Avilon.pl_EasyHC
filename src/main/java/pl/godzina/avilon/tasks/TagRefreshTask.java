package pl.godzina.avilon.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.nametag.TagManager;

public class TagRefreshTask extends BukkitRunnable {
    private final AvilonPlugin plugin;

    public TagRefreshTask(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.runTaskTimer(plugin, 100L, 100L);
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            TagManager tagManager = new TagManager();
            tagManager.send(p);
            System.out.println("[NAMETAGMANAGER] Refreshed Tags.");
        }
    }
}
