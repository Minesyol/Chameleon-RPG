package me.keegan.chameleon_rpg.utils.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public class ChameleonTask {
    private final BukkitRunnable bukkitRunnable;
    private final long ticks;
    private final long period;
    private final boolean async;

    public ChameleonTask(BukkitRunnable bukkitRunnable, long ticks, boolean async) {
        this.bukkitRunnable = bukkitRunnable;
        this.ticks = ticks;
        this.period = -1;
        this.async = async;
    }

    public ChameleonTask(BukkitRunnable bukkitRunnable, long ticks, long period, boolean async) {
        this.bukkitRunnable = bukkitRunnable;
        this.ticks = ticks;
        this.period = period;
        this.async = async;
    }

    public BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }

    public long getTicks() {
        return ticks;
    }

    public long getPeriod() {
        return period;
    }

    public boolean isAsync() {
        return async;
    }
}
