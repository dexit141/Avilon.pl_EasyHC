package pl.godzina.avilon.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;

public class LocationHelper {
    public static Location locFromString(String str) {
        String[] str2loc = str.split(":");
        Location loc = new Location(Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D);
        loc.setWorld(Bukkit.getWorld(str2loc[0]));
        loc.setX(Double.parseDouble(str2loc[1]));
        loc.setY(Double.parseDouble(str2loc[2]));
        loc.setZ(Double.parseDouble(str2loc[3]));
        return loc;
    }

    public static String locToString(Location loc) {
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
    }

    public static boolean Location(int minX, int maxX, int minZ, int maxZ, Location l) {
        double[] dim = { minX, maxX };
        Arrays.sort(dim);
        if (l.getX() >= dim[1] || l.getX() <= dim[0])
            return false;
        dim[0] = minZ;
        dim[1] = maxZ;
        Arrays.sort(dim);
        return (l.getZ() < dim[1] && l.getZ() > dim[0]);
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
