package pl.godzina.avilon.basic.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.godzina.avilon.AvilonPlugin;

public class DropSettings {
    private static FileConfiguration dropConfig = null;

    private static File dropConfigFile = null;

    public static void reloadConfig() {
        if (dropConfigFile == null)
            dropConfigFile = new File(AvilonPlugin.getInstance().getDataFolder(), "drops.yml");
        File m = new File(AvilonPlugin.getInstance().getDataFolder(), "drops.yml");
        if (!m.exists())
            AvilonPlugin.getInstance().saveResource("drops.yml", true);
        dropConfig = YamlConfiguration.loadConfiguration(dropConfigFile);
        InputStream defConfigStream = AvilonPlugin.getInstance().getResource("drops.yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            dropConfig.setDefaults(defConfig);
        }
    }

    public static FileConfiguration getConfig() {
        if (dropConfig == null)
            reloadConfig();
        return dropConfig;
    }

    public static void saveConfig() {
        if (dropConfig == null || dropConfigFile == null)
            return;
        try {
            getConfig().save(dropConfigFile);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config to " + dropConfigFile, ex);
        }
    }

    public static void saveDefaultConfig() {
        if (dropConfigFile == null)
            dropConfigFile = new File(AvilonPlugin.getInstance().getDataFolder(), "drops.yml");
        if (!dropConfigFile.exists())
            AvilonPlugin.getInstance().saveResource("drops.yml", false);
    }
}

