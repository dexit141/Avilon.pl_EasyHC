package pl.godzina.avilon.basic.nametag;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
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

            final String name = player1.getName();

            this.packet.getStrings().write(0, name); //set name
            this.packet.getStrings().write(1, name); //set displayname
            this.packet.getStrings().write(2, ChatHelper.fixColor(prefix(player1, player2)));
            this.packet.getStrings().write(3, ChatHelper.fixColor(suffix(player1, player2)));

            this.packet.getIntegers().write(0, -1); //set color, -1 = no color
            this.packet.getIntegers().write(1, 0); //set mode, 0=create team
            this.packet.getIntegers().write(2, 1); //set packOptionData

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

            final String name = player1.getName();

            this.packet.getStrings().write(0, name); //set name
            this.packet.getStrings().write(1, name); //set displayname
            this.packet.getStrings().write(2, ChatHelper.fixColor(prefix(player1, player2)));
            this.packet.getStrings().write(3, ChatHelper.fixColor(suffix(player1, player2)));
//            this.packet.getStrings().write(4, ChatHelper.translate("elo"));

            this.packet.getIntegers().write(0, -1); //set color, -1 = no color
            this.packet.getIntegers().write(1, 2); //set mode, 0=create team
            this.packet.getIntegers().write(2, 1); //set packOptionData

            this.packet.getSpecificModifier(Collection.class).write(0, Collections.singletonList(name));
            ProtocolLibrary.getProtocolManager().sendServerPacket(player2, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//    public void delete(Player player1, Player player2) {
//        try {
//
//            this.packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SCOREBOARD_TEAM);
//            final String name = player1.getName();
//
//            this.packet.getStrings().write(0, name); //set name
//            this.packet.getIntegers().write(1, 1); //set mode, 0=create team
//
//            ProtocolLibrary.getProtocolManager().sendServerPacket(player2, packet);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    public void send(Player p) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            create(p, online);
            update(p, online);
        }
    }


    public String prefix(Player get, Player send) {
//        Guild guildGet = CorePlugin.getInstance().getUserManager().getUser(get).getGuild();
//        Guild guildSend = CorePlugin.getInstance().getUserManager().getUser(send).getGuild();
        User userGet = AvilonPlugin.getInstance().getUserManager().getUser(get);
        if (get.hasPermission("avilon.root")) {
            return "&4&lCEO &c";
        }
        if (get.hasPermission("avilon.dev")) {
            return "&3&lDEV &b";
        }
        if (get.hasPermission("avilon.headadmin")) {
            return "&4&lH@ &c";
        }
        if (get.hasPermission("avilon.admin")) {
            return "&4&lADMIN &c";
        }
        if (get.hasPermission("avilon.moderator")) {
            return "&2&lMOD &a";
        }
        if (get.hasPermission("avilon.helper")) {
            return "&3&lHELPER &b";
        }
        if (!send.hasPermission("avilon.incognito.bypass")) {
            if (userGet.isIncognito()) {
//                if (userGet.getGuild() != null) {
//                    if (guildSend == null) {
//                        return "&8[&c???&8] &f&k";
//                    }
//                    if (guildGet.getTag().equalsIgnoreCase(guildSend.getTag())) {
//                        return "&8[&a???&8] &f&k";
//                    }
//                    if (guildSend.getAlly().contains(guildGet)) {
//                        return "&8[&6???&8] &f&k";
//                    }
//                    return "&8[&c???&8] &f&k";
            } else {
                return "&k";
            }
//        }
//        if (guildGet == null) {
//            return "";
//        }
//        if (guildSend == null) {
//            return "&8[&c" + guildGet.getTag() + "&8] &f";
//        }
//        if (guildGet.getTag().equalsIgnoreCase(guildSend.getTag())) {
//            return "&8[&a" + guildGet.getTag() + "&8] &f";
//        }
//        if (guildSend.getAlly().contains(guildGet)) {
//            return "&8[&6" + guildGet.getTag() + "&8] &f";
//        }
//        return "&8[&c" + guildGet.getTag() + "&8] &f";
//    }

            return "";
        }
        return "";
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
                return " &b&l(IG)";
            }
        }
        return "";
    }
}
