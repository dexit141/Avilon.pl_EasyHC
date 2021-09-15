package pl.godzina.avilon.commands.admin;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class VanishCommand extends PlayerCommand {
    public VanishCommand(AvilonPlugin plugin) {
        super(plugin, "vanish", "/vanish (gracz)", "avilon.vanish", "v");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        User u = this.plugin.getUserManager().getUser(p);
        if (args.length < 1) {

            if (!u.isVanish()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("avilon.vanish")) {
                        player.hidePlayer(p);
                    }
                }

                u.setVanish(true);

            } else {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(p);
                }

                u.setVanish(false);
            }

            return ChatHelper.sendMessage(p, "&8>> &fPomyślnie &dzmieniłes &ftryb vanisha na: " + (u.isVanish() ? "&aON" : "&cOFF"));
        }

        return false;
    }
}