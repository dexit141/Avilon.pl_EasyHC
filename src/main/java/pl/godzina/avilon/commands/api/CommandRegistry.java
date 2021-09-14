package pl.godzina.avilon.commands.api;

import org.bukkit.command.SimpleCommandMap;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.Reflection;

public class CommandRegistry {

    private final SimpleCommandMap commandMap;

    public CommandRegistry(AvilonPlugin plugin) {
        Reflection.FieldAccessor<?> bukkitCommandMapField = Reflection.getField(plugin.getServer().getClass(), "commandMap", SimpleCommandMap.class);
        this.commandMap = (SimpleCommandMap) bukkitCommandMapField.get(plugin.getServer());
    }

    public void register(CustomCommand command) {
        this.commandMap.register(command.getName(), command);
    }

}
