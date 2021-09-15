package pl.godzina.avilon.helpers;

import java.util.Random;
import org.apache.commons.lang.Validate;

public final class RandomHelper {
    private static final Random rand = new Random();

    public static boolean getChance(double chance) {
        return (chance >= 100.0D || chance >= getRandDouble(0.0D, 100.0D).doubleValue());
    }

    private static Double getRandDouble(double min, double max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return Double.valueOf(rand.nextDouble() * (max - min) + min);
    }

    public static Float getRandFloat(float min, float max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return Float.valueOf(rand.nextFloat() * (max - min) + min);
    }

    public static int getRandInt(int min, int max) throws IllegalArgumentException {
        Validate.isTrue((max > min), "Max can't be smaller than min!");
        return rand.nextInt(max - min + 1) + min;
    }

    public static int nextInt(int i) {
        return rand.nextInt(i);
    }
}
