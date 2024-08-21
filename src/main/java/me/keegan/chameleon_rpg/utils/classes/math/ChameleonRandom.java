package me.keegan.chameleon_rpg.utils.classes.math;

import java.util.Random;

public class ChameleonRandom {
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
}
