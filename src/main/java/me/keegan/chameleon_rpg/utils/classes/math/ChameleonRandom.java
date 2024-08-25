package me.keegan.chameleon_rpg.utils.classes.math;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Random;
import java.util.Set;

public final class ChameleonRandom {
    private final int start, end;

    /**
     *
     * @param start Inclusive
     * @param end Inclusive
     */
    public ChameleonRandom(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int random() {
        return new Random().nextInt(start, end + 1);
    }

    @Nullable
    public static <T> T chooseRandom(@NonNull Set<T> tSet) {
        return tSet.stream().skip(new Random().nextInt(tSet.size())).findFirst().orElse(null);
    }
}
