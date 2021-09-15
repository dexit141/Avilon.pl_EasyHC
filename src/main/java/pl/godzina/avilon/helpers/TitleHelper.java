package pl.godzina.avilon.helpers;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleHelper {
    public static void sendActionbar(Player player, String msg) {
        IChatBaseComponent cmp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatHelper.fixColor(msg) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(cmp, (byte)2);
        (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)bar);
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        CraftPlayer craftPlayer = (CraftPlayer)player;
        PacketPlayOutTitle packetTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        (craftPlayer.getHandle()).playerConnection.sendPacket((Packet)packetTimes);
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatHelper.fixColor(title) + "\"}");
        PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        (craftPlayer.getHandle()).playerConnection.sendPacket((Packet)packetTitle);
        IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatHelper.fixColor(subtitle) + "\"}");
        PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        (craftPlayer.getHandle()).playerConnection.sendPacket((Packet)packetSubtitle);
    }
}

