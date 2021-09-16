package pl.godzina.avilon.commands.player;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.menus.EffectMenus;

public class EffectCommand extends PlayerCommand {
    public EffectCommand(AvilonPlugin plugin) {
        super(plugin, "efekty", "", "avilon.player", "eg");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        EffectMenus effectMenus = new EffectMenus(plugin);
        effectMenus.open(p);
        return true;
    }
}
