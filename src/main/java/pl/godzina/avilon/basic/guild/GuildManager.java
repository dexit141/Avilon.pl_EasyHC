package pl.godzina.avilon.basic.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.SpaceHelper;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GuildManager {
    private final AvilonPlugin plugin;
    private final Map<String, Guild> guilds;

    public GuildManager(AvilonPlugin plugin) {
        this.guilds = new ConcurrentHashMap<String, Guild>();
        this.plugin = plugin;
    }

    
    public Guild createGuild(final String tag, final String name, final User owner, final Location home) {
        final Guild g = new Guild(tag, name, owner, home, this.plugin);
        this.guilds.put(tag, g);
        this.plugin.getRankingManager().addRanking(g);
        owner.getPlayer().teleport(g.getCuboid().getCenter().add(0.0, 1.0, 0.0));
        Bukkit.getScheduler().runTask(this.plugin, () -> this.createGuildRoom(g));
        return g;
    }

    
    public void deleteGuild(final Guild g) {
        for (final User user : g.getMembers()) {
//            user.setGuildPermissions(null);
        }
        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_deputies` WHERE `guild` = '" + g.getTag() + "'");
        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_guilds` WHERE `tag` = '" + g.getTag() + "'");
        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_members` WHERE `guild` = '" + g.getTag() + "'");
        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_allies` WHERE `guildA` = '" + g.getTag() + "'");
        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_allies` WHERE `guildB` = '" + g.getTag() + "'");
        for (final Guild ally : g.getAlly()) {
            if (ally != null) {
                ally.removeAlly(g);
                g.removeAlly(ally);
            }
        }
        Bukkit.getScheduler().runTask((Plugin)this.plugin, () -> this.deleteGuildRoom(g));
        this.plugin.getRankingManager().removeRanking(g);
        this.guilds.remove(g.getTag());
    }

    
    public Guild getGuild(final Player player) {
        for (final Guild g : this.guilds.values()) {
            if (g.isMember(player)) {
                return g;
            }
        }
        return null;
    }

    
    public Guild getGuild(final String name) {
        for (final Guild g : this.guilds.values()) {
            if (g.getName().equalsIgnoreCase(name) || g.getTag().equalsIgnoreCase(name)) {
                return g;
            }
        }
        return null;
    }

    
    public Guild getGuild(final Location loc) {
        for (final Guild g : this.guilds.values()) {
            if (g.getCuboid().isInCuboid(loc)) {
                return g;
            }
        }
        return null;
    }

    
    public Guild getGuildByLocation(final Location loc) {
        for (final Guild g : this.guilds.values()) {
            if (g.getCuboid().isInCuboidByLoc(loc)) {
                return g;
            }
        }
        return null;
    }

    
    public Guild getGuildByPlayerName(User name) {
        for (final Guild g : this.guilds.values()) {
            if (g.isMember(name)) {
                return g;
            }
        }
        return null;
    }

    
    public boolean canCreateGuildBySpawn(final Location loc) {
        final int spawnX = loc.getWorld().getSpawnLocation().getBlockX();
        final int spawnZ = loc.getWorld().getSpawnLocation().getBlockZ();
        return Math.abs(loc.getBlockX() - spawnX) >= 350 || Math.abs(loc.getBlockZ() - spawnZ) >= 350;
    }

    
    public boolean canCreateGuildByGuild(final Location loc) {
        final int minDistance = 150;
        for (final Guild g : this.guilds.values()) {
            if (Math.abs(g.getCuboid().getX() - loc.getBlockX()) <= minDistance && Math.abs(g.getCuboid().getZ() - loc.getBlockZ()) <= minDistance) {
                return false;
            }
        }
        return true;
    }

    
    public void deleteGuildRoom(final Guild g) {
        g.getCuboid().getCenter().add(0.0, 2.0, 0.0).getBlock().setType(Material.AIR);
        g.getCuboid().getCenter().add(0.0, 1.0, 0.0).getBlock().setType(Material.AIR);
        g.getCuboid().getCenter().getBlock().setType(Material.AIR);
    }

    
    public void createGuildRoom(final Guild g) {
        final Location c = g.getCuboid().getCenter();
        for (final Location loc : SpaceHelper.getSquare(c, 4, 4)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : SpaceHelper.getSquare(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        for (final Location loc : SpaceHelper.getCorners(c, 4, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        c.add(0.0, 5.0, 0.0);
        for (final Location loc : SpaceHelper.getWalls(c, 4)) {
            loc.getBlock().setType(Material.OBSIDIAN);
        }
        g.getCuboid().getCenter().add(0.0, 0.0, 0.0).getBlock().setType(Material.OBSIDIAN);
        g.getCuboid().getCenter().add(0.0, 1.0, 0.0).getBlock().setType(Material.BEDROCK);
    }

    
    public Collection<Guild> getGuilds() {
        return this.guilds.values();
    }

    
    public void loadGuildsDatabase() {
        try {
            final ResultSet rs = this.plugin.getDatabaseProvider().query("SELECT * FROM avilon_guilds");
            while (rs.next()) {
                try {
                    final Guild g = new Guild(rs, this.plugin);
                    this.guilds.put(g.getTag(), g);
                    this.plugin.getRankingManager().addRanking(g);
                }
                catch (Exception e) {
                    this.plugin.getLogger().info("Problem z zaladowaniem gildii " + rs.getString("tag"));
                    e.printStackTrace();
                }
            }
            rs.close();
            this.plugin.getLogger().info("Zaladowano " + this.guilds.size() + " gildii!");
            final ResultSet rsMembers = this.plugin.getDatabaseProvider().query("SELECT * FROM avilon_members");
            while (rsMembers.next()) {
                try {
                    final User user = this.plugin.getUserManager().getUser(UUID.fromString(rsMembers.getString("uuid")));
                    final Guild guild = this.getGuild(rsMembers.getString("guild"));
                    if (user == null) {
                        this.plugin.getLogger().info("User is null");
                        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_members` WHERE `uuid` ='" + rsMembers.getString("uuid") + "'");
                        return;
                    }
                    if (guild == null) {
                        this.plugin.getDatabaseProvider().executeUpdate("DELETE FROM `avilon_members` WHERE `uuid` ='" + rsMembers.getString("uuid") + "'");
                        this.plugin.getLogger().info("Guild is null");
                        return;
                    }
                    guild.addMember(user);
//                    user.setGuildPermissions(new GuildPermissions(rsMembers, this.plugin));
                }
                catch (Exception e2) {
                    this.plugin.getLogger().info("Problem z zaladowaniem czlonka gildii: " + rsMembers.getString("uuid") + " w gildii" + rsMembers.getString("guild"));
                }
            }
            rsMembers.close();
            final ResultSet rsAllies = this.plugin.getDatabaseProvider().query("SELECT * FROM `avilon_allies`");
            while (rsAllies.next()) {
                try {
                    final Guild g2 = this.getGuild(rsAllies.getString("guildA"));
                    final Guild g3 = this.getGuild(rsAllies.getString("guildB"));
                    g2.addAlly(g3);
                    g3.addAlly(g2);
                }
                catch (Exception e3) {
                    this.plugin.getLogger().info("Problem z zaladowaniem sojuszu gildii: " + rsAllies.getString("guildA") + " z: " + rsAllies.getString("guildB"));
                }
            }
            rsAllies.close();
            final ResultSet rsDeputies = this.plugin.getDatabaseProvider().query("SELECT * FROM `avilon_deputies`");
            while (rsDeputies.next()) {
                try {
                    final User user2 = this.plugin.getUserManager().getUser(UUID.fromString(rsDeputies.getString("uuid")));
                    final Guild g4 = this.getGuild(rsDeputies.getString("guild"));
                    g4.getDeputies().add(user2);
                }
                catch (Exception e4) {
                    this.plugin.getLogger().info("Problem z zaladowaniem zastepcy o uuid: " + rsDeputies.getString("uuid") + " z gildii: " + rsDeputies.getString("guild"));
                }
            }
            rsDeputies.close();
        }
        catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    
//    public Set<Guild> getNeedSaveGuilds() {
//        return this.guilds.values().stream().filter(Guild::isNeedSave).collect((Collector<? super Guild, ?, Set<Guild>>) Collectors.toSet());
//    }
}

