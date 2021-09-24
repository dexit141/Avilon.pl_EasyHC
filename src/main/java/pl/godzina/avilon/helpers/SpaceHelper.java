package pl.godzina.avilon.helpers;

import org.bukkit.*;
import java.util.*;

public final class SpaceHelper
{
    public static List<Location> getSquare(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<Location>();
        final int cX = center.getBlockX();
        final int cZ = center.getBlockZ();
        final int minX = Math.min(cX + radius, cX - radius);
        final int maxX = Math.max(cX + radius, cX - radius);
        final int minZ = Math.min(cZ + radius, cZ - radius);
        final int maxZ = Math.max(cZ + radius, cZ - radius);
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                locs.add(new Location(center.getWorld(), (double)x, (double)center.getBlockY(), (double)z));
            }
        }
        locs.add(center);
        return locs;
    }

    public static List<Location> getCorners(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<Location>();
        final int cX = center.getBlockX();
        final int cZ = center.getBlockZ();
        final int minX = Math.min(cX + radius, cX - radius);
        final int maxX = Math.max(cX + radius, cX - radius);
        final int minZ = Math.min(cZ + radius, cZ - radius);
        final int maxZ = Math.max(cZ + radius, cZ - radius);
        locs.add(new Location(center.getWorld(), (double)minX, (double)center.getBlockY(), (double)minZ));
        locs.add(new Location(center.getWorld(), (double)maxX, (double)center.getBlockY(), (double)minZ));
        locs.add(new Location(center.getWorld(), (double)minX, (double)center.getBlockY(), (double)maxZ));
        locs.add(new Location(center.getWorld(), (double)maxX, (double)center.getBlockY(), (double)maxZ));
        return locs;
    }

    public static List<Location> getWalls(final Location center, final int radius) {
        final List<Location> locs = getSquare(center, radius);
        locs.removeAll(getSquare(center, radius - 1));
        return locs;
    }

    public static List<Location> getSquare(final Location center, final int radius, final int height) {
        final List<Location> locs = getSquare(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getSquare(new Location(center.getWorld(), (double)center.getBlockX(), (double)(center.getBlockY() + i), (double)center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> getCorners(final Location center, final int radius, final int height) {
        final List<Location> locs = getCorners(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getCorners(new Location(center.getWorld(), (double)center.getBlockX(), (double)(center.getBlockY() + i), (double)center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> getWallsOnGround(final Location center, final int radius) {
        final List<Location> locs = new ArrayList<Location>();
        for (final Location l : getWalls(center, radius)) {
            locs.add(l.getWorld().getHighestBlockAt(l).getLocation().add(0.0, 1.0, 0.0));
        }
        return locs;
    }

    public static List<Location> getWallsOnGround(final Location center, final int radius, final int height) {
        final List<Location> locs = new ArrayList<Location>();
        for (final Location l : getWalls(center, radius)) {
            for (int i = 0; i < height; ++i) {
                locs.add(l.getWorld().getHighestBlockAt(l).getLocation().add(0.0, (double)(1 + i), 0.0));
            }
        }
        return locs;
    }

    public static List<Location> getWalls(final Location center, final int radius, final int height) {
        final List<Location> locs = getWalls(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getWalls(new Location(center.getWorld(), (double)center.getBlockX(), (double)(center.getBlockY() + i), (double)center.getBlockZ()), radius));
        }
        return locs;
    }

    public static List<Location> sphere(final Location loc, final int radius, final int height, final boolean hollow, final boolean sphere, final int plusY) {
        final List<Location> circleblocks = new ArrayList<Location>();
        final int cx = loc.getBlockX();
        final int cy = loc.getBlockY();
        final int cz = loc.getBlockZ();
        for (int x = cx - radius; x <= cx + radius; ++x) {
            for (int z = cz - radius; z <= cz + radius; ++z) {
                for (int y = sphere ? (cy - radius) : cy; y < (sphere ? (cy + radius) : (cy + height)); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < radius * radius && (!hollow || dist >= (radius - 1) * (radius - 1))) {
                        final Location l = new Location(loc.getWorld(), (double)x, (double)(y + plusY), (double)z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
}

