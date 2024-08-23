package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.game.tasks.TaskScheduler;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public final class NightQuestController implements IChameleonPluginState {
    private static final HashMap<UUID, NightQuestModel> ongoingNightQuests = new HashMap<>();
    private static final HashSet<UUID> completedNightQuests = new HashSet<>();

    @Nullable
    public static NightQuestModel getOngoingNightQuestModel(Player player) {
        return ongoingNightQuests.get(player.getUniqueId());
    }

    @Override
    public void onPluginEnable() {
        Runnable runnable = () -> {
            for (Player player : ChameleonRPG.getPlugin().getServer().getOnlinePlayers()) {
                World world = player.getWorld();
                UUID uuid = player.getUniqueId();

                boolean isNightTime = world.getTime() > 13000 && world.getTime() < 23000;

                if (!isNightTime) {
                    completedNightQuests.remove(uuid);
                    continue;
                }

                if (completedNightQuests.contains(uuid) || world.getEnvironment() != World.Environment.NORMAL)
                    continue;

                if (ongoingNightQuests.containsKey(uuid)) {
                    if (ongoingNightQuests.get(uuid).isComplete()) {
                        ongoingNightQuests.remove(uuid);
                        completedNightQuests.add(uuid);
                    }

                    continue;
                }

                ongoingNightQuests.put(uuid, NightQuestFactory.createRandomNightQuest(player));
            }
        };

        TaskScheduler.scheduleTaskTimer(runnable, 0, 20, false);
    }
}
