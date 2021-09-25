package pl.godzina.avilon.basic.nametag;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.guild.Guild;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.RandomHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

public class TagManager {
    private PacketContainer packet;

    public void create(Player player1, Player player2) {
        try {
            this.packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);

            String name = player1.getName();

            this.packet.getStrings().write(0, name);
            this.packet.getStrings().write(1, name);
            this.packet.getStrings().write(2, ChatHelper.fixColor(prefix(player1, player2)));
            this.packet.getStrings().write(3, ChatHelper.fixColor(suffix(player1, player2)));

            this.packet.getIntegers().write(0, -1);
            this.packet.getIntegers().write(1, 0);
            this.packet.getIntegers().write(2, 1);

            this.packet.getSpecificModifier(Collection.class).write(0, Collections.singletonList(name));
            ProtocolLibrary.getProtocolManager().sendServerPacket(player2, packet);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void update(Player player1, Player player2) {
        try {

            this.packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);

            String name = player1.getName();

            this.packet.getStrings().write(0, name);
            this.packet.getStrings().write(1, name);
            this.packet.getStrings().write(2, ChatHelper.fixColor(prefix(player1, player2)));
            this.packet.getStrings().write(3, ChatHelper.fixColor(suffix(player1, player2)));

            this.packet.getIntegers().write(0, -1);
            this.packet.getIntegers().write(1, 2);
            this.packet.getIntegers().write(2, 1);

            this.packet.getSpecificModifier(Collection.class).write(0, Collections.singletonList(name));
            ProtocolLibrary.getProtocolManager().sendServerPacket(player2, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void send(Player p) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            create(p, online);
            update(p, online);
        }
    }


    public String prefix(Player get, Player send) {
        Guild guildGet = AvilonPlugin.getInstance().getUserManager().getUser(get).getGuild();
        Guild guildSend = AvilonPlugin.getInstance().getUserManager().getUser(send).getGuild();
        User userGet = AvilonPlugin.getInstance().getUserManager().getUser(get);
        if (!send.hasPermission("avilon.incognito.bypass")) {
            if (userGet.isIncognito()) {
                if (userGet.getGuild() != null) {
                    if (guildSend == null)
                        return "&8[&c?&] &c&k";
                    else if (guildGet.getTag().equalsIgnoreCase(guildSend.getTag()))
                        return "&8[&a" + guildGet.getTag() + "&8] &f";
                    else if (guildSend.getAlly().contains(guildGet))
                        return "&8[&6" + guildGet.getTag() + "&8] &6";

                    return "&8[&c?&8] &f&k";
                }
                else {
                    return "&f&k";
                }
            }
        }

        if (get.hasPermission("avilon.root"))
            return "&4&lCEO &r&c";
        else if (get.hasPermission("avilon.headadmin"))
            return "&4&lH@ &r&c";
        else if (get.hasPermission("avilon.admin"))
            return "&4&lADMIN &r&c";
        else if (get.hasPermission("avilon.mod"))
            return "&2&lMOD &r&a";
        else if (get.hasPermission("avilon.helper"))
            return "&3&lHELPER &r&b";
        else if (guildGet == null)
            return "";
        else if (guildSend == null)
            return "&8[&c" + guildGet.getTag() + "&8] &6";
        else if (guildGet.getTag().equalsIgnoreCase(guildSend.getTag()))
            return "&8[&a" + guildGet.getTag() + "&8] &a";
        else if (guildSend.getAlly().contains(guildGet))
            return "&8[&6" + guildGet.getTag() + "&8] &6";

        return "&8[&c" + guildGet.getTag() + "&8] &f";
    }

    public String suffix(Player get, Player send) {
        User userGet = AvilonPlugin.getInstance().getUserManager().getUser(get);
        if (userGet.isVanish()) {
            if (send.hasPermission("avilon.vanish")) {
                return " &3[⚠]";
            }
        }
        if (userGet.isIncognito()) {
            if (send.hasPermission("avilon.incognito.bypass")) {
                return " &8(&bIG&8)";
            }
        }
        return "";
    }
}