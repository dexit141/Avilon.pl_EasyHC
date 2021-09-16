package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class ChatCommand extends PlayerCommand {
    public ChatCommand(AvilonPlugin plugin) {
        super(plugin, "chat", "/chat (clear/on/off)", "avilon.chat", "cm");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1) return this.correctUsage(p, label);

        if (args[0].equalsIgnoreCase("clear")) {
            plugin.getChatManager().clearChat();
        }
        if (args[0].equalsIgnoreCase("off")) {
            plugin.getChatManager().setChat(false);
            return ChatHelper.sendMessage((CommandSender) Bukkit.getOnlinePlayers(), "&d&lAvilon &8>> &fChat zostal wylaczony...");
        } else if (args[0].equalsIgnoreCase("on")) {
            plugin.getChatManager().setChat(true);
            return ChatHelper.sendMessage((CommandSender) Bukkit.getOnlinePlayers(), "&d&lAvilon &8>> &fChat zostal wlaczony.....");
        }
        return false;
    }
}
