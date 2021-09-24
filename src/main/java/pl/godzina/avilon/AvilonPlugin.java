package pl.godzina.avilon;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.godzina.avilon.basic.chat.addons.ChatManager;
import pl.godzina.avilon.basic.configuration.DropSettings;
import pl.godzina.avilon.basic.drop.DropManager;
import pl.godzina.avilon.basic.guild.GuildManager;
import pl.godzina.avilon.basic.ranking.RankingManager;
import pl.godzina.avilon.basic.storage.DatabaseProvider;
import pl.godzina.avilon.basic.teleport.TeleportManager;
import pl.godzina.avilon.basic.user.UserManager;
import pl.godzina.avilon.commands.admin.*;
import pl.godzina.avilon.commands.api.CommandRegistry;
import pl.godzina.avilon.commands.guild.GuildCommand;
import pl.godzina.avilon.commands.player.*;
import pl.godzina.avilon.listeners.*;
import pl.godzina.avilon.tasks.ActionbarNotificationTask;
import pl.godzina.avilon.tasks.DatabaseTask;
import pl.godzina.avilon.tasks.TagRefreshTask;

@Getter
public class AvilonPlugin extends JavaPlugin {
    @Getter
    private static AvilonPlugin instance;
    private DatabaseProvider databaseProvider;
    private UserManager userManager;
    private TeleportManager teleportManager;
    private DropManager dropManager;
    private ChatManager chatManager;
    private RankingManager rankingManager;
    private GuildManager guildManager;


    @Override
    public void onEnable() {
        instance = this;
        this.dropManager = new DropManager(this);
        this.userManager = new UserManager(this);
        this.chatManager = new ChatManager(this);
        this.teleportManager = new TeleportManager(this);
        this.databaseProvider = new DatabaseProvider(this);
        this.guildManager = new GuildManager(this);
        getLogger().info("Plugin by: 15godzina, Licensed to: Avilon.pl ( getsector#0501 )");
        DropSettings.reloadConfig();
        this.dropManager.setup();

        registerCommands();
        registerRunnable();
        registerListeners();
    }

    private void registerListeners() {

        new PlayerCommandPreeprocessListener(this);
        new PlayerJoinAndQuitListener(this);
        new PlayerMoveListener(this);
        new BlockBreakListener(this);
        new EntityDamageByEntityListener(this);
        new InventoryCloseListener(this);
    }


    private void registerCommands() {

        CommandRegistry cr = new CommandRegistry(this);
        cr.register(new EffectCommand(this));
        cr.register(new GamemodeCommand(this));
        cr.register(new WhoisCommand(this));
        cr.register(new EnderchestCommand(this));
        cr.register(new HomeCommand(this));
        cr.register(new IncognitoCommand(this));
        cr.register(new VanishCommand(this));
        cr.register(new HelpopCommand(this));
        cr.register(new GodCommand(this));
        cr.register(new TpCommand(this));
        cr.register(new StpCommand(this));
        cr.register(new FeedCommand(this));
        cr.register(new HealCommand(this));
        cr.register(new ChatCommand(this));
        cr.register(new ClearCommand(this));
        cr.register(new DropCommand(this));
        cr.register(new GuildCommand(this));
    }
    private void registerRunnable() {
        new TagRefreshTask(this);
        new DatabaseTask(this);
        new ActionbarNotificationTask(this);
    }

}
