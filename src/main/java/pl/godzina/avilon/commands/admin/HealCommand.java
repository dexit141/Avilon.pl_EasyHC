package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class HealCommand extends PlayerCommand {
    public HealCommand(AvilonPlugin plugin) {
        super(plugin, "heal", "/heal (gracz)", "avilon.heal", "ulecz");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) {
            p.setHealth(20);
        }
        Player other = Bukkit.getPlayerExact(args[0]);
        if (other != null) {
            other.setHealth(20);
        } else {
            return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPodany gracz jest offline");
        }
        return false;
    }
}
