package pl.godzina.avilon.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.data.storage.DatabaseProvider;

public class DatabaseTask extends BukkitRunnable {
    private final AvilonPlugin plugin;

    public DatabaseTask(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.runTaskTimerAsynchronously(plugin, 200L, 200L);
    }

    @Override
    public void run() {
    }
}
