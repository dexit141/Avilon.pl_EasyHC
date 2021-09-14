package pl.godzina.avilon.commands.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.ChatHelper;

public abstract class PlayerCommand extends CustomCommand {

    public PlayerCommand(AvilonPlugin plugin, String name, String usage, String permission, String... aliases) {
        super(plugin, name, usage, permission, aliases);
    }

    @Override
    public boolean onExecute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player))
            return ChatHelper.sendMessage(sender, "&cCzynność musi zostać wykonana jako Gracz.");

        Player p = (Player)sender;
        return this.onExecute(p, label, args);
    }

    public abstract boolean onExecute(Player player, String label, String[] args);

}
