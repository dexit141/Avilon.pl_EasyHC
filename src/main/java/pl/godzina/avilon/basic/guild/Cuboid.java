package pl.godzina.avilon.basic.guild;

import org.bukkit.*;

public class Cuboid
{
    private int x;
    private final int y;
    private int z;
    private int size;
    Guild guild;

    public Cuboid(final int x, final int z, final int size, final Guild guild) {
        this.x = x;
        this.y = 30;
        this.z = z;
        this.guild = guild;
        this.size = size;
    }

    public boolean isInCuboid(final Location loc) {
        if (!loc.getWorld().equals(Bukkit.getWorld("world"))) {
            return false;
        }
        final int distanceX = Math.abs(loc.getBlockX() - this.x);
        final int distanceZ = Math.abs(loc.getBlockZ() - this.z);
        return distanceX <= this.getSize() && distanceZ <= this.getSize();
    }

    public boolean isInCuboidByLoc(final Location loc) {
        if (!loc.getWorld().equals(Bukkit.getWorld("world"))) {
            return false;
        }
        final int distanceX = Math.abs(loc.getBlockX() - this.getX());
        final int distancez = Math.abs(loc.getBlockZ() - this.getZ());
        return distanceX - 1 <= this.getSize() && distancez - 1 <= this.getSize();
    }

    public Location getCenter() {
        return new Location(this.getLocation().getWorld(), (double)this.x, (double)this.y, (double)this.z);
    }

    public int getX() {
        return this.x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(final int z) {
        this.z = z;
    }

    public int getSize() {
        return this.size;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld("world"), (double)this.getX(), 40.0, (double)this.getZ());
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public void addSize(final int size) {
        this.size += size;
    }
}

