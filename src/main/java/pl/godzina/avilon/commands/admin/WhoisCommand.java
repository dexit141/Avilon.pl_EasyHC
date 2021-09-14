package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.LoginHelper;

public class WhoisCommand extends PlayerCommand {
    public WhoisCommand(AvilonPlugin plugin) {
        super(plugin, "whois", "/whois (gracz)", "avilon.whois", "ip");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) {
            ChatHelper.sendMessage(p, "&8&m[----[-----&r &D&lWHOIS &8(&fTY&8)&8&m-----]----]");
            ChatHelper.sendMessage(p, "");
            ChatHelper.sendMessage(p, "&8>> &fAktywny: &d" + (p.isOnline() ? "AKTYWNY" : "NIEAKTYWNY"));
            ChatHelper.sendMessage(p, "&8>> &fIP: &d" + p.getAddress());
            ChatHelper.sendMessage(p, "&8>> &fZbanowany: &dSOON");
            ChatHelper.sendMessage(p,"&8>> &fTryb gry: &d" + p.getGameMode().toString().toUpperCase());
            ChatHelper.sendMessage(p, "&8>> &fEfekty: &d" + p.getActivePotionEffects().toString().toUpperCase());
            ChatHelper.sendMessage(p, "&8>> &fGracz PREMIUM/NONPREMIUM &D" + LoginHelper.isPremium(p.getName()));
            ChatHelper.sendMessage(p, "");
            ChatHelper.sendMessage(p, "&8&m[----[-----&r &D&lWHOIS &8(&fTY&8)&8&m-----]----]");
        } else {
            Player other = Bukkit.getPlayerExact(args[0]);
            if (other == null) {
                return ChatHelper.sendMessage(p, "&d&LAvilon &8>> &fGracz jest offline.");
            } else {
                ChatHelper.sendMessage(p, "&8&m[----[-----&r &D&lWHOIS &8(&f "+ other.getName() + " &8)&8&m-----]----]");
                ChatHelper.sendMessage(p, "");
                ChatHelper.sendMessage(p, "&8>> &fAktywny: &d" + (other.isOnline() ? "AKTYWNY" : "NIEAKTYWNY"));
                ChatHelper.sendMessage(p, "&8>> &fIP: &d" + other.getAddress());
                ChatHelper.sendMessage(p, "&8>> &fZbanowany: &dSOON");
                ChatHelper.sendMessage(p,"&8>> &fTryb gry: &d" + other.getGameMode().toString().toUpperCase());
                ChatHelper.sendMessage(p, "&8>> &fEfekty: &d" + other.getActivePotionEffects().toString().toUpperCase());
                ChatHelper.sendMessage(p, "&8>> &fGracz PREMIUM/NONPREMIUM &D" + LoginHelper.isPremium(other.getName()));
                ChatHelper.sendMessage(p, "");
                ChatHelper.sendMessage(p, "&8&m[----[-----&r &D&lWHOIS &8(&f "+ other.getName()  +" &8)&8&m-----]----]");
            }
        }
        return false;
    }
}
