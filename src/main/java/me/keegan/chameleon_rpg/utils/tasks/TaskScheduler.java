package me.keegan.chameleon_rpg.utils.tasks;

import me.keegan.chameleon_rpg.ChameleonRPG;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public final class TaskScheduler {
    private final Queue<ChameleonTask> chameleonTaskQueue = new LinkedList<>();

    private TaskScheduler() {}

    public void runTasks() {
        ChameleonTask chameleonTask = chameleonTaskQueue.poll();
        if (chameleonTask == null) { return; }

        scheduleTask(chameleonTask.getBukkitRunnable(), chameleonTask.getTicks(), chameleonTask.isAsync());
    }

    public void addTask(Runnable runnable, long ticks, boolean async) {
        chameleonTaskQueue.add(new ChameleonTask(new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
                runTasks();
            }
        }, ticks, async));
    }

    public <T> void addTask(Consumer<T> consumer, T consumed, long ticks, boolean async) {
        chameleonTaskQueue.add(new ChameleonTask(new BukkitRunnable() {
            @Override
            public void run() {
                consumer.accept(consumed);
                runTasks();
            }
        }, ticks, async));
    }

    // STATIC METHODS

    public static TaskScheduler createTaskQueue() {
        return new TaskScheduler();
    }

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

    public static <T> void scheduleTask(Consumer<T> consumer, T consumed, long ticks, boolean async) {
        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                consumer.accept(consumed);
            }
        };

        if (async) {
            bukkitRunnable.runTaskLater(ChameleonRPG.getPlugin(), ticks);
        }else{
            bukkitRunnable.runTaskLaterAsynchronously(ChameleonRPG.getPlugin(), ticks);
        }
    }

    public static BukkitRunnable scheduleTaskTimer(Runnable runnable, long ticks, long period, boolean async) {
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

        return bukkitRunnable;
    }
}
