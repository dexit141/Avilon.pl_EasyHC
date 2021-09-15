package pl.godzina.avilon.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.TitleHelper;

public class ActionbarNotificationTask extends BukkitRunnable {
    private final AvilonPlugin plugin;

    public ActionbarNotificationTask(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.runTaskTimer(plugin, 200L, 200L);
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {

            String message = "";

            User user = plugin.getUserManager().getUser(p);
            if (user.isVanish() == true) {
                if (!message.isEmpty()) {
                    message += " &8| &r";
                }
                message += "&d&lVanish!";
            }
            TitleHelper.sendActionbar(p, message);
        }
    }
}
