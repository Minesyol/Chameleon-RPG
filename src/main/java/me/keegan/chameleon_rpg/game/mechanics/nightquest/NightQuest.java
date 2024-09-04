package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.utils.objects.classes.math.ChameleonRandom;

/**
 * The real quest class; not a model to control
 */
public final class NightQuest<T> {
    private final ChameleonRandom randomRequiredProgress;
    private final T target;

    public NightQuest(T target, ChameleonRandom randomRequiredProgress) {
        this.target = target;
        this.randomRequiredProgress = randomRequiredProgress;
    }

    public T getTarget() {
        return target;
    }

    public int getRequiredProgress() {
        return randomRequiredProgress.random();
    }
}
