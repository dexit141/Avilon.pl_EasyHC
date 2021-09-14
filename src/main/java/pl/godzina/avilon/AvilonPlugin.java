package pl.godzina.avilon;

import lombok.Data;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.godzina.avilon.commands.api.CommandRegistry;
import pl.godzina.avilon.commands.player.EffectCommand;
import pl.godzina.avilon.data.storage.DatabaseProvider;
import pl.godzina.avilon.listeners.PlayerCommandPreeprocessListener;
import pl.godzina.avilon.tasks.DatabaseTask;

@Getter
public class AvilonPlugin extends JavaPlugin {
    @Getter
    private static AvilonPlugin instance;
    private DatabaseProvider databaseProvider;



    @Override
    public void onEnable() {
        instance = this;
        this.databaseProvider = new DatabaseProvider(this);
        getLogger().info("Plugin by: 15godzina, Licensed to: Avilon.pl ( getsector#0501 )");

        registerCommands();
        registerRunnable();
        registerListeners();
    }

    private void registerListeners() {
        new PlayerCommandPreeprocessListener(this);
    }


    private void registerCommands() {

        CommandRegistry cr = new CommandRegistry(this);
        cr.register(new EffectCommand(this));
    }
    private void registerRunnable() {
        new DatabaseTask(this);
    }

}
