package pl.godzina.avilon.basic.guild;

import com.mysql.jdbc.TimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;
import pl.godzina.avilon.helpers.LocationHelper;
import pl.godzina.avilon.helpers.TimeHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Guild {
    private AvilonPlugin plugin;
    private final String tag;
    private final String name;
    private User owner;
    private Set<User> deputies;
    private final Cuboid cuboid;
    private Location guildHome;
    private final Set<Guild> ally;
    private final Set<User> members;
    private final Set<Guild> allyInvites;
    private final Set<Player> invites;
    private int lives;
    private int stars;
    private int kills;
    private int deaths;
    private long expireDate;
    private long createDate;
    private long lastAttackDate;
    private long lastExplodeDate;
    private boolean pvpInGuild;
    private boolean pvpInAlly;
    private boolean needSave;

    public Guild(final String tag, final String name, final User owner, final Location home, final AvilonPlugin plugin) {
        this.plugin = plugin;
        this.tag = tag;
        this.name = name;
        this.owner = owner;
        this.guildHome = home;
        this.cuboid = new Cuboid(owner.getPlayer().getLocation().getBlockX(), owner.getPlayer().getLocation().getBlockZ(), 40, this);
        this.lives = 3;
        this.stars = owner.getStars();
        this.kills = 0;
        this.deaths = 0;
        this.expireDate = System.currentTimeMillis() + TimeHelper.DAY.getTime(3);
        this.createDate = System.currentTimeMillis();
        this.lastAttackDate = System.currentTimeMillis() + TimeHelper.HOUR.getTime(24);
        this.lastExplodeDate = 0L;
        this.pvpInGuild = false;
        this.pvpInAlly = false;
        this.needSave = false;
        this.deputies = new HashSet<User>();
        this.members = new HashSet<User>();
        this.invites = new HashSet<Player>();
        this.ally = new HashSet<Guild>();
        this.allyInvites = new HashSet<Guild>();
        this.addMember(owner);
        this.insert();
    }

    public Guild(final ResultSet rs, final AvilonPlugin plugin) throws SQLException {
        this.plugin = plugin;
        this.members = new HashSet<User>();
        this.invites = new HashSet<Player>();
        this.ally = new HashSet<Guild>();
        this.allyInvites = new HashSet<Guild>();
        this.deputies = new HashSet<User>();
        this.tag = rs.getString("tag");
        this.name = rs.getString("name");
        this.owner = plugin.getUserManager().getUser(UUID.fromString(rs.getString("owner")));
        this.guildHome = LocationHelper.locFromString(rs.getString("guildHome"));
        this.cuboid = new Cuboid(rs.getInt("cuboidX"), rs.getInt("cuboidZ"), rs.getInt("cuboidSize"), this);
        this.lives = rs.getInt("lives");
        this.stars = rs.getInt("stars");
        this.kills = rs.getInt("kills");
        this.deaths = rs.getInt("deaths");
        this.expireDate = rs.getLong("expireDate");
        this.createDate = rs.getLong("createDate");
        this.lastAttackDate = rs.getLong("lastAttackDate");
    }

    private void insert() {
        this.plugin.getDatabaseProvider().executeUpdate("INSERT INTO `avilon_guilds`(`id`, `tag`, `name`, `owner`, `guildHome`, `cuboidX`, `cuboidZ`, `cuboidSize`, `lives`, `stars`, `kills`, `deaths`,`expireDate`, `createDate`, `lastAttackDate`) VALUES (NULL, '" + this.getTag() + "','" + this.getName() + "','" + this.getOwner().getUuid() + "','" + LocationHelper.locToString(this.getGuildHome()) + "','" + this.getCuboid().getX() + "','" + this.getCuboid().getZ() + "','" + this.getCuboid().getSize() + "','" + this.getLives() + "','" + this.getStars() + "','" + this.getKills() + "','" + this.getDeaths() + "','" + this.getExpireDate() + "','" + this.getCreateDate() + "','" + this.getLastAttackDate() + "');");
    }

    public int refreshPoints() {
        final Set<User> users = new HashSet<User>(this.getMembers());
        int allPoints = 0;
        for (final User u2 : users) {
            allPoints += u2.getStars();
        }
        return allPoints / this.getMembers().size();
    }


    public boolean isOwner(final User user) {
        return this.getOwner().equals(user);
    }


    public void setGuildHome(final Location guildHome) {
        this.guildHome = guildHome;
    }


    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }

    public void removeStars(final int index) {
        this.setStars(this.getStars() - index);
    }

    public void addKills(final int index) {
        this.setKills(this.getKills() + index);
    }

    public void removeKills(final int index) {
        this.setKills(this.getKills() - index);
    }

    public void addDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }

    public void removeDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }

    public void removeAlly(final Guild guild) {
        this.ally.remove(guild);
    }

    public String getALlyList() {
        final StringBuilder s = new StringBuilder("&8>> &fSojusze: ");
        if (this.getAlly().size() == 0) {
            s.append("&fBrak.");
        } else {
            for (final Guild name : this.getAlly()) {
                s.append("&8, &f").append(name.getTag());
            }
        }
        return s.toString();
    }

    public void addAlly(final Guild g) {
        this.ally.add(g);
    }

    public Set<User> getMembers() {
        return this.members;
    }

    public boolean isMember(final Player name) {
        return this.getMembers().contains(this.plugin.getUserManager().getUser(name.getUniqueId()));
    }


    public boolean isMember(final User user) {
        return this.getMembers().contains(user);
    }

    public void addMember(final User user) {
        this.members.add(user);
    }

    public void removeMember(final User user) {
        this.members.remove(user);
    }

    public Set<User> getDeputies() {
        return this.deputies;
    }

    public boolean isDeputy(final User user) {
        return this.deputies.contains(user) || this.getOwner().equals(user);
    }

    public String getDeputiesList() {
        final StringBuilder s = new StringBuilder("&8>> &fZastepcy: ");
        if (this.getDeputies().size() == 0) {
            s.append("&fBrak.");
        } else {
            for (final User user : this.getDeputies()) {
                s.append("&8, &f").append(user.getName());
            }
        }
        return s.toString();
    }

    public Set<Guild> getAllyInvites() {
        return this.allyInvites;
    }

    public Set<Player> getInvites() {
        return this.invites;
    }

    public String[] getMemberList() {
        final String[] s = new String[this.members.size()];
        int i = 0;
        for (final User u : this.members) {
            final OfflinePlayer op = Bukkit.getOfflinePlayer(u.getName());
            s[i] = "&c" + (op.isOnline() ? "&a" : "&c") + op.getName();
            ++i;
        }
        return s;
    }

    public double getKd() {
        if (this.getKills() == 0 && this.getDeaths() == 0) {
            return 0.0;
        }
        if (this.getKills() > 0 && this.getDeaths() == 0) {
            return this.getKills();
        }
        if (this.getDeaths() > 0 && this.getKills() == 0) {
            return -this.getDeaths();
        }
        return ChatHelper.round(this.getKills() / (double) this.getDeaths(), 2);
    }

    public void sendMessageToMembers(final String msg) {
        for (final Player p : this.getOnlineMembers()) {
            ChatHelper.sendMessage(p, msg);
        }
    }

    public Set<Player> getOnlineMembers() {
        final Set<Player> online = new HashSet<Player>();
        for (final User u : this.members) {
            final OfflinePlayer op = Bukkit.getOfflinePlayer(u.getName());
            if (op.isOnline()) {
                online.add(op.getPlayer());
            }
        }
        return online;
    }

    public void addSize(final int size) {
        this.getCuboid().addSize(size);
    }

    public void setNeedSave(final boolean needSave) {
        this.needSave = needSave;
    }

    public boolean isNeedSave() {
        return this.needSave;
    }
}
