package pl.godzina.avilon.basic.ranking.comparators;

import pl.godzina.avilon.basic.guild.Guild;

import java.util.Comparator;

public class GuildComparator implements Comparator<Guild>
{
    @Override
    public int compare(final Guild g0, final Guild g1) {
        final Integer p0 = g0.getStars();
        final Integer p2 = g1.getStars();
        return p2.compareTo(p0);
    }
}

