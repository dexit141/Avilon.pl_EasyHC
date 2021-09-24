package pl.godzina.avilon.basic.ranking;

import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.guild.Guild;
import pl.godzina.avilon.basic.ranking.comparators.GuildComparator;
import pl.godzina.avilon.basic.ranking.comparators.UserComparator;
import pl.godzina.avilon.basic.user.User;

import java.util.LinkedList;
import java.util.List;

public class RankingManager {
    private final List<User> usersRankings;
    private final List<Guild> guildsRankings;
    private final AvilonPlugin plugin;

    public RankingManager(AvilonPlugin plugin) {
        this.plugin = plugin;
        this.usersRankings = new LinkedList<User>();
        this.guildsRankings = new LinkedList<Guild>();
    }
    
    public void addRanking(final User ranking) {
        this.usersRankings.add(ranking);
        this.sortUserRankings();
    }
    
    public void addRanking(final Guild ranking) {
        this.guildsRankings.add(ranking);
        this.sortGuildRankings();
    }


    public void removeRanking(final User ranking) {
        this.usersRankings.remove(ranking);
        this.sortUserRankings();
    }


    public void removeRanking(final Guild ranking) {
        this.guildsRankings.remove(ranking);
        this.sortGuildRankings();
    }


    public void sortUserRankings() {
        this.usersRankings.sort(new UserComparator());
    }


    public void sortGuildRankings() {
        this.guildsRankings.sort(new GuildComparator());
    }


    public int getPlaceUser(final User user) {
        for (int num = 0; num < this.usersRankings.size(); ++num) {
            if (this.usersRankings.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }


    public int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < this.usersRankings.size(); ++num) {
            if (this.guildsRankings.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }


    public List<User> getUsersRankings() {
        return this.usersRankings;
    }


    public List<Guild> getGuildsRankings() {
        return this.guildsRankings;
    }
}
