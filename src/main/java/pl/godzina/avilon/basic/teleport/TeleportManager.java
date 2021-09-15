package pl.godzina.avilon.basic.teleport;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.TitleHelper;
import pl.godzina.avilon.tasks.TeleportTask;

public class TeleportManager {
    private AvilonPlugin plugin;

    private HashMap<UUID, Integer> teleports = new HashMap<>();

    private TeleportTask teleportRunnable;

    public TeleportManager(AvilonPlugin plugin) {
        this.plugin = plugin;
    }

    public void teleport(Player p, Location loc, int time) {
        if (p == null)
            return;
        if (p.hasPermission("avilon.nodelay")) {
            p.teleport(loc);
            return;
        }
        if (this.teleports.containsKey(p.getUniqueId())) {
            ChatHelper.sendMessage(p, "&d&lAvilon &8>> &cTeleportujesz sie juz gdzies.");
            return;
        }
        this.teleportRunnable = new TeleportTask(time, p);
        this.teleportRunnable.runTaskTimer((Plugin)this.plugin, 0L, 20L);
        int id = this.plugin.getServer().getScheduler().runTaskLater((Plugin)this.plugin, () -> {
            p.teleport(loc);
            this.teleports.remove(p.getUniqueId());
        }, time * 20L).getTaskId();
        this.teleports.put(p.getUniqueId(), Integer.valueOf(id));
    }

    public void cancelTeleport(Player p) {
        if (this.teleports.get(p.getUniqueId()) == null)
            return;
        this.plugin.getServer().getScheduler().cancelTask(((Integer)this.teleports.get(p.getUniqueId())).intValue());
        this.teleportRunnable.cancel();
        this.teleports.remove(p.getUniqueId());
        TitleHelper.sendTitle(p, "", "&cTeleportacja zostala przerwana!", 0, 40, 20);
    }

    public HashMap<UUID, Integer> getTeleports() {
        return this.teleports;
    }
}
