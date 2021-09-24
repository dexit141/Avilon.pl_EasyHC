package pl.godzina.avilon.commands.guild;


import org.bukkit.entity.*;
import org.bukkit.command.*;

import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.configs.GuildMessages;
import pl.godzina.avilon.commands.api.CustomCommand;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.commands.guild.player.GuildCreateCommand;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.*;

public class GuildCommand extends PlayerCommand
{
    private final LinkedHashSet<CustomCommand> subCommands;

    public GuildCommand(final AvilonPlugin plugin) {
        super(plugin, "g", "/g", "avilon.player", "gildia", "guild", "guilds");
        (this.subCommands = new LinkedHashSet<CustomCommand>()).add(new GuildCreateCommand(plugin));
    }

    @Override
    public boolean onExecute(final Player p, final String label, final String[] args) {
        if (args.length < 1) {
            return this.listOfCommands((CommandSender)p);
        }
        final String name = args[0];
        final CustomCommand sc = this.getSubCommand(name);
        if (sc == null) {
            return this.listOfCommands((CommandSender)p);
        }
        return sc.onExecute((CommandSender)p, label, Arrays.copyOfRange(args, 1, args.length));
    }

    private boolean listOfCommands(final CommandSender sender) {
        for (final String s : GuildMessages.listOfCommands) {
            ChatHelper.sendMessage(sender, s);
        }
        return true;
    }

    private CustomCommand getSubCommand(final String sub) {
        for (final CustomCommand sc : this.subCommands) {
            if (sc.getName().equalsIgnoreCase(sub) || sc.getAliases().contains(sub)) {
                return sc;
            }
        }
        return null;
    }
}

