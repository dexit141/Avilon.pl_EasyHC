package pl.godzina.avilon.commands.guild.player;

import com.sun.scenario.Settings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.configs.GuildMessages;
import pl.godzina.avilon.basic.guild.Guild;
import pl.godzina.avilon.basic.guild.GuildManager;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.basic.user.UserManager;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.ItemHelper;
import pl.godzina.avilon.helpers.ItemsSerializationHelper;

import java.util.Locale;

public class GuildCreateCommand extends PlayerCommand {
    private GuildManager guildManager;
    private UserManager userManager;
    public GuildCreateCommand(AvilonPlugin plugin) {
        super(plugin, "create", "/g zaloz (tag) (nazwa)", "avilon.player", "zaloz");
        this.guildManager = plugin.getGuildManager();
        this.userManager = plugin.getUserManager();
}


    @Override
    public boolean onExecute(final Player p, final String label, final String[] args) {
        Guild g = this.guildManager.getGuild(p);
        if (g != null) {
            return ChatHelper.sendMessage(p, GuildMessages.yourGuildNotNull);
        }
        if (args.length != 2) {
            return this.correctUsage(p, label);
        }
        String tag = args[0].toUpperCase();
        String name = args[1];
        if (tag.length() > 5 || tag.length() < 2 || name.length() > 32 || name.length() < 4) {
            return ChatHelper.sendMessage(p, GuildMessages.incorrectGuildName);
        }
        if (this.guildManager.getGuild(tag) != null || this.guildManager.getGuild(name) != null) {
            return ChatHelper.sendMessage(p, GuildMessages.guildAlreadyExist);
        }
        if (!ChatHelper.isAlphaNumeric(tag) || !ChatHelper.isAlphaNumeric(name)) {
            return ChatHelper.sendMessage(p, GuildMessages.incorrectGuildName);
        }
        if (!this.guildManager.canCreateGuildBySpawn(p.getLocation())) {
            return ChatHelper.sendMessage(p, GuildMessages.guildIsTooCloseToSpawn);
        }
        if (!this.guildManager.canCreateGuildByGuild(p.getLocation())) {
            return ChatHelper.sendMessage(p, GuildMessages.guildIsTooCloseToOtherGuild);
        }
        String it = plugin.getConfig().getString("guild.cost.normalcost");
        if (p.hasPermission("avilon.guild.admin")) {
            it = "1:0-0:nic;";
        }
        else if (p.hasPermission("avilon.svip.itemy")) {
            it = plugin.getConfig().getString("guild.cost.premiumcost");
        }
        if (!ItemHelper.checkItems(p, it, 1)) {
            ItemHelper.getItem(p, it, 1);
            return true;
        }
        ItemHelper.removeItems(p, it, 1);
        Location home = new Location(p.getWorld(), p.getLocation().getX(), 31.5, p.getLocation().getZ());
        User u = this.userManager.getUser(p);
        Guild guild = this.guildManager.createGuild(tag.toLowerCase(), name, u, home);
        this.plugin.getServer().getOnlinePlayers().forEach(player -> ChatHelper.sendMessage(player, GuildMessages.guildCreateBroadcastNotification.replace("{TAG}", guild.getTag()).replace("{NAME}", guild.getName()).replace("{PLAYER}", p.getName())));
        return true;
    }
}

