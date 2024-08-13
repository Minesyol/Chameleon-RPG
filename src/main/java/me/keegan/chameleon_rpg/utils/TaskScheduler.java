package me.keegan.chameleon_rpg.utils;

import me.keegan.chameleon_rpg.ChameleonRPG;
import org.bukkit.scheduler.BukkitRunnable;

public final class TaskScheduler {
    public static void scheduleTask(Runnable runnable, long ticks, boolean async) {
        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        };

        if (async) {
            bukkitRunnable.runTaskLater(ChameleonRPG.getPlugin(), ticks);
        }else{
            bukkitRunnable.runTaskLaterAsynchronously(ChameleonRPG.getPlugin(), ticks);
        }
    }

    public static void scheduleTaskTimer(Runnable runnable, long ticks, long period, boolean async) {
        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        };

        if (async) {
            bukkitRunnable.runTaskTimer(ChameleonRPG.getPlugin(), ticks, period);
        }else{
            bukkitRunnable.runTaskTimerAsynchronously(ChameleonRPG.getPlugin(), ticks, period);
        }
    }
}
