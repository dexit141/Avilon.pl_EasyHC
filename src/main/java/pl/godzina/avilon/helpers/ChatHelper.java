package pl.godzina.avilon.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatHelper {
    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text)
                .replace(">>", "»")
                .replace("<<", "«");
    }
    public static boolean sendMessage(CommandSender cs, String message) {
        cs.sendMessage(fixColor(message));
        return true;
    }
}
