package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class TpCommand extends PlayerCommand {
    public TpCommand(AvilonPlugin plugin) {
        super(plugin, "tp", "/tp (gracz)", "avilon.tp", "teleportuj");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) return this.correctUsage(p, label);

        Player other = Bukkit.getPlayerExact(args[0]);
        if (other == null) {
            return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPodany gracz jest offline.");
        }
        p.teleport(other);
        return ChatHelper.sendMessage(p, "&d&LAvilon &8>> &aPomyslnie przeteleportowales sie do gracza: &d" + other.getName());
    }
}
