package pl.godzina.avilon.helpers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

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
    public static List<String> fixColor(List<String> list){
        List<String> toColored = new ArrayList<>();
        list.forEach(x -> {
            String lore = fixColor(x);
            toColored.add(lore);
        });
        return toColored;
    }
}
