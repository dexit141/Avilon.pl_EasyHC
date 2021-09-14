package pl.godzina.avilon.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.commands.api.PlayerCommand;
import pl.godzina.avilon.helpers.ChatHelper;

public class GamemodeCommand extends PlayerCommand {
    public GamemodeCommand(AvilonPlugin plugin) {
        super(plugin, "gamemode", "/gamemode (0/1/2/3) (gracz)", "avilon.gamemode", "gm");
    }

    @Override
    public boolean onExecute(Player p, String label, String[] args) {
        if (args.length < 1)
            return correctUsage(p, label);
        GameMode gameMode = checkPlayerMode(args[0]);
        if (gameMode == null)
            return correctUsage( p, label);
        if (args.length == 1) {
            p.setGameMode(gameMode);
            return  ChatHelper.sendMessage( p, "&d&LAvilon &8>> &fTryb of kjerwa gra zostal kjerwa kjerwa zmieniony na: &d{MODE}".replace("{MODE}", p.getGameMode().toString().toUpperCase()));
        }
        if (args.length != 2)
            return correctUsage( p, label);
        Player other = Bukkit.getPlayerExact(args[1]);
        if (other == null)
            return ChatHelper.sendMessage(p, "&d&LAvilon &8>> &fGracz jest offline.");
        other.setGameMode(gameMode);
        return  ChatHelper.sendMessage( p, "&d&lAvilon &8>> &fTryb dla &d{PLAYER} &fzostal zmieniony na &d{MODE}".replace("{MODE}", other.getGameMode()
                        .toString()
                        .toUpperCase())
                .replace("{PLAYER}", other.getName()));
    }

    private GameMode checkPlayerMode(String s) {
        switch (s) {
            case "survival":
            case "s":
            case "0":
                return GameMode.SURVIVAL;
            case "creative":
            case "c":
            case "1":
                return GameMode.CREATIVE;
            case "adventure":
            case "a":
            case "2":
                return GameMode.ADVENTURE;
            case "spectator":
            case "3":
                return GameMode.SPECTATOR;
        }
        return null;
    }
}