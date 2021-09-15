package pl.godzina.avilon.commands.player;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.menus.HomeMenus;

public class HomeCommand extends PlayerCommand {
    public HomeCommand(AvilonPlugin plugin) {
        super(plugin, "home", "/home", "avilon.player", "holm");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        HomeMenus homeMenus = new HomeMenus(plugin);
        if (args.length < 1) {
            homeMenus.open(p);
        }
        return false;
    }
}
