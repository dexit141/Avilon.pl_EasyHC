package pl.godzina.avilon.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.godzina.avilon.helpers.TitleHelper;

public class TeleportTask extends BukkitRunnable {
    private long delay;

    private final Player p;

    public TeleportTask(long delay, Player p) {
        this.delay = delay;
        this.p = p;
    }

    public void run() {
        if (this.delay > 0L) {
            TitleHelper.sendTitle(this.p, "", "&fTeleportacja nastapi za: &d" + this.delay + "s", 0, 30, 0);
            TitleHelper.sendActionbar(this.p, "&fZostaniesz przeteleportowany za &d" + this.delay);
            this.delay--;
        } else {
            TitleHelper.sendTitle(this.p, "", "&aPomyslnie przeteleportowano.", 0, 20, 40);
            cancel();
        }
    }
}

