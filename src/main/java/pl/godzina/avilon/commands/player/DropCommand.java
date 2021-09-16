package pl.godzina.avilon.commands.player;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.drop.Drop;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.menus.DropMenus;

public class DropCommand extends PlayerCommand {
    public DropCommand(AvilonPlugin plugin) {
        super(plugin, "drop", "/drop", "avilon.player", "stone");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        new DropMenus().openMain(p);
        return false;
    }
}
