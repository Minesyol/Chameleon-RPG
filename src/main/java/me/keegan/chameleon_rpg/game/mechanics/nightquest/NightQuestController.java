package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.game.tasks.TaskScheduler;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public final class NightQuestController implements IChameleonPluginState {
    private static final HashMap<UUID, NightQuestModel> ongoingNightQuests = new HashMap<>();

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

                boolean isDayTime = world.getTime() < 13000 || world.getTime() > 23000;
                if (ongoingNightQuests.containsKey(uuid) || world.getEnvironment() != World.Environment.NORMAL) { continue; }

                if (!isDayTime || ongoingNightQuests.get(uuid).isComplete()) {
                    ongoingNightQuests.remove(uuid);
                    return;
                }

                ongoingNightQuests.put(uuid, NightQuestFactory.createRandomNightQuest(player));
            }
        };

        TaskScheduler.scheduleTaskTimer(runnable, 0, 20, false);
    }
}
