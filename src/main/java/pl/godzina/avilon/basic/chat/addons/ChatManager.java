package pl.godzina.avilon.basic.chat.addons;

import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;

import org.bukkit.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public final class ChatManager
{
    private final AvilonPlugin plugin;
    private static boolean chat;
    private static int msgPerSecond;
    private static final LinkedList<Long> msgTimes;
    private static final WeakHashMap<UUID, Long> times;

    static {
        ChatManager.chat = false;
        ChatManager.msgPerSecond = 3;
        msgTimes = new LinkedList<Long>();
        times = new WeakHashMap<UUID, Long>();
    }

    public ChatManager(AvilonPlugin plugin) {
        this.plugin = plugin;
    }

    public  void clearChat() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 100; ++i) {
                p.sendMessage("");
            }
        }
    }

    public  void toggleChat() {
        setChat(!isChat());
        for (final Player p : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 10; ++i) {
                p.sendMessage("");
            }
        }
    }

    public  boolean canSendMessage(final Player p) {
        if (p.hasPermission("kguild.chat.bypass")) {
            return true;
        }
        final Long time = ChatManager.times.get(p.getUniqueId());
        return time == null || System.currentTimeMillis() - time >= TimeUnit.SECONDS.toMillis(5L);
    }

    public void addMessageTime() {
        ChatManager.msgTimes.add(System.currentTimeMillis());
    }

    public boolean canSendMessage() {
        if (ChatManager.msgTimes.size() <= 2) {
            return true;
        }
        final long start = ChatManager.msgTimes.getFirst();
        final long end = ChatManager.msgTimes.getLast();
        final int size = ChatManager.msgTimes.size();
        return (end - start) / size <= ChatManager.msgPerSecond;
    }

    public  boolean isChat() {
        return ChatManager.chat;
    }

    public void setChat(final boolean chat) {
        ChatManager.chat = chat;
    }

    public int getMsgPerSecond() {
        return ChatManager.msgPerSecond;
    }

    public void setMsgPerSecond(final int msgPerSecond) {
        ChatManager.msgPerSecond = msgPerSecond;
    }

    public  LinkedList<Long> getMsgTimes() {
        return ChatManager.msgTimes;
    }

    public WeakHashMap<UUID, Long> getTimes() {
        return ChatManager.times;
    }
}
