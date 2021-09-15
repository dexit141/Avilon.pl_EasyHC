package pl.godzina.avilon.commands.player;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.menus.EnderchestMenus;

public class EnderchestCommand extends PlayerCommand {
    public EnderchestCommand(AvilonPlugin plugin) {
        super(plugin, "ec", "/ec", "avilon.player", "enderchest");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        EnderchestMenus enderchestMenus = new EnderchestMenus(plugin);
        if (args.length < 1) {
            enderchestMenus.open(p);
        }
        return false;
    }
}
