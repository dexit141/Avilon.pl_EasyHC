package pl.godzina.avilon.basic.ranking.comparators;

import pl.godzina.avilon.basic.user.User;

import java.util.Comparator;

public class UserComparator implements Comparator<User>
{
    @Override
    public int compare(final User g0, final User g1) {
        final Integer p0 = g0.getStars();
        final Integer p2 = g1.getStars();
        return p2.compareTo(p0);
    }
}

