package pl.godzina.avilon.commands.player;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.TitleHelper;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HelpopCommand extends PlayerCommand{
    private static final HashMap<UUID, Long> time = new HashMap<UUID, Long>();
    public HelpopCommand(AvilonPlugin plugin) {
        super(plugin, "helpop", "/helpop (tresc)", "avilon.player", "report");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) return correctUsage(p, label);

        String message = StringUtils.join(args, " ", 0, args.length);

        if (HelpopCommand.time.containsKey(p.getUniqueId()) && System.currentTimeMillis() < HelpopCommand.time.get(p.getUniqueId())) {
            return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fMusisz odczekac &d" + time.get(p.getUniqueId()) + "&fsekund, przed następnym wysłaniem");
        }
        HelpopCommand.time.put(p.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L));
        Bukkit.broadcast(ChatHelper.fixColor("\n &8( &d&lHELPOP &8) &f" + p.getName() + " &8>> &4" + message + "\n"), "avilon.helpop");
        return ChatHelper.sendMessage(p, "&8>> &aTwoje zgłoszenie zostało wysłane!");
    }
}
