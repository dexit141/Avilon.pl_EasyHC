package pl.godzina.avilon.commands.api;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.Arrays;

@Getter
public abstract class CustomCommand extends Command implements PluginIdentifiableCommand {

    protected final AvilonPlugin plugin;
    protected final String name;
    protected final String usage;
    protected final String permission;

    public CustomCommand(AvilonPlugin plugin, String name, String usage, String permission, String... aliases) {
        super(name, "", usage, Arrays.asList(aliases));
        this.plugin = plugin;
        this.name = name;
        this.usage = usage;
        this.permission = permission;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.permission))
            return ChatHelper.sendMessage(sender, "&cCzynność którą chcesz wykonać wymaga permisji ({PERM})".replace("{PERM}", this.permission));

        return this.onExecute(sender, label, args);
    }

    @Override
    public String getUsage() {
        return StringUtils.replace(this.usage, "{LABEL}", getName());
    }

    public String getUsage(String label) {
        return StringUtils.replace(this.usage, "{LABEL}", label);
    }

    public abstract boolean onExecute(CommandSender sender, String label, String[] args);

    public boolean correctUsage(CommandSender sender, String label) {
        return ChatHelper.sendMessage(sender, "&8>> &fPoprawne użycie: &d{USAGE}".replace("{USAGE}", this.getUsage(label)));
    }
}
