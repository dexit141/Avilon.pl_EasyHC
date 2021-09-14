package pl.godzina.avilon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.ChatHelper;

public class PlayerCommandPreeprocessListener implements Listener
{
    private AvilonPlugin plugin;

    public PlayerCommandPreeprocessListener(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }

    @EventHandler
    public void onBlockedCmd(PlayerCommandPreprocessEvent e) {
         Player p = e.getPlayer();
        if (!p.hasPermission("avilon.seeplugins")) {

            String message = e.getMessage();
            String[] splittedMessage = message.split(" ");
           String[] pluginCommands = { "/help", "/?", "/pl", "/plugins", "/ver", "/version", "/about", "/icanhasbukkit", "/bukkit:pl", "/bukkit:plugins", "/bukkit:ver", "/bukkit:version", "/bukkit:about", "/bukkit:?", "/bukkit:help", "/me", "/minecraft:me" };
            if (this.containsIgnoreCase(pluginCommands, splittedMessage[0])) {
                e.setCancelled(true);
                ChatHelper.sendMessage(p, "&8>> &fPlugin &d" + plugin.getName() + "");
                ChatHelper.sendMessage(p, "&8>> &fPosiada licencje w stanie: &aPoprawny");
                ChatHelper.sendMessage(p, "&8>> &fPrzypisana na: getsector#0501");
            }
        }
    }

    public boolean containsIgnoreCase(final String[] board, final String string) {
        for (final String otherstring : board) {
            if (otherstring.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
}
