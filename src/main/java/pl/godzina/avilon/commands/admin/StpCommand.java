package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class StpCommand extends PlayerCommand {
    public StpCommand(AvilonPlugin plugin) {
        super(plugin, "stp", "/stp (gracz)", "avilon.stp", "s");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) return this.correctUsage(p, label);

        Player other = Bukkit.getPlayerExact(args[0]);
        if (other == null) {
            return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPodany gracz jest offline");
        }
        other.teleport(p);
        ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie przeteleportowales gracza &d" + other.getDisplayName() + " &fdo siebie");
        return ChatHelper.sendMessage(other, "&d&lAvilon &8>> &fZostales przeteleportowany do administratora &d" + p.getName());
    }
}
