package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class FeedCommand extends PlayerCommand {
    public FeedCommand(AvilonPlugin plugin) {
        super(plugin, "feed", "/feed (gracz)", "avilon.feed", "nakarm");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) {
            p.setFoodLevel(20);
        }
        Player other = Bukkit.getPlayerExact(args[0]);
        if (other != null) {
            other.setFoodLevel(20);
        } else {
            return ChatHelper.sendMessage(p, "&d&LAvilon &8>> &fGracz jest offline");
        }
        return false;
    }
}
