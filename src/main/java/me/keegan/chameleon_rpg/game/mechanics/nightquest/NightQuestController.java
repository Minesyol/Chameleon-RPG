package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.game.tasks.TaskScheduler;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public final class NightQuestController implements IChameleonPluginState {
    private static final HashMap<UUID, NightQuestModel> ongoingNightQuests = new HashMap<>();

    @Override
    public void onPluginEnable() {
        Runnable runnable = () -> {
            for (Player player : ChameleonRPG.getPlugin().getServer().getOnlinePlayers()) {
                World world = player.getWorld();
                boolean isDayTime = world.getTime() < 13000 || world.getTime() > 23000;

                if (world.getEnvironment() != World.Environment.NORMAL || !isDayTime) { continue; }

                // todo: everything lol
            }
        };

        BukkitRunnable bukkitRunnableTimer = TaskScheduler.scheduleTaskTimer(runnable, 0, 20, false);
    }
}
