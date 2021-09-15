package pl.godzina.avilon.commands.player;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class IncognitoCommand extends PlayerCommand {
    public IncognitoCommand(AvilonPlugin plugin) {
        super(plugin, "incognito", "/incognito", "avilon.incognito", "ig");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        User user = plugin.getUserManager().getUser(p);
        user.setIncognito(!user.isIncognito());
        return ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie &d" + (user.isIncognito() ? "wlaczyles" : "wylaczyles") + " &fincognito!");
    }
}
