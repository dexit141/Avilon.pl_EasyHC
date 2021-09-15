package pl.godzina.avilon;

import lombok.Data;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.godzina.avilon.basic.storage.DatabaseProvider;
import pl.godzina.avilon.basic.teleport.TeleportManager;
import pl.godzina.avilon.basic.user.UserManager;
import pl.godzina.avilon.commands.admin.GamemodeCommand;
import pl.godzina.avilon.commands.admin.WhoisCommand;
import pl.godzina.avilon.commands.api.CommandRegistry;
import pl.godzina.avilon.commands.player.EffectCommand;
import pl.godzina.avilon.commands.player.EnderchestCommand;
import pl.godzina.avilon.commands.player.HomeCommand;
import pl.godzina.avilon.listeners.PlayerCommandPreeprocessListener;
import pl.godzina.avilon.listeners.PlayerJoinAndQuitListener;
import pl.godzina.avilon.listeners.PlayerMoveListener;
import pl.godzina.avilon.tasks.DatabaseTask;
import pl.godzina.avilon.tasks.TeleportTask;

@Getter
public class AvilonPlugin extends JavaPlugin {
    @Getter
    private static AvilonPlugin instance;
    private DatabaseProvider databaseProvider;
    private UserManager userManager;
    private TeleportManager teleportManager;


    @Override
    public void onEnable() {
        instance = this;
        this.userManager = new UserManager(this);
        this.teleportManager = new TeleportManager(this);
        this.databaseProvider = new DatabaseProvider(this);
        getLogger().info("Plugin by: 15godzina, Licensed to: Avilon.pl ( getsector#0501 )");

        registerCommands();
        registerRunnable();
        registerListeners();
    }

    private void registerListeners() {

        new PlayerCommandPreeprocessListener(this);
        new PlayerJoinAndQuitListener(this);
        new PlayerMoveListener(this);
    }


    private void registerCommands() {

        CommandRegistry cr = new CommandRegistry(this);
        cr.register(new EffectCommand(this));
        cr.register(new GamemodeCommand(this));
        cr.register(new WhoisCommand(this));
        cr.register(new EnderchestCommand(this));
        cr.register(new HomeCommand(this));
    }
    private void registerRunnable() {

        new DatabaseTask(this);
    }

}
