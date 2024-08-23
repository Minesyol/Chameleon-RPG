package me.keegan.chameleon_rpg.game.mechanics.nightquest.model.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.classes.math.ChameleonRandom;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashSet;
import java.util.Set;

public class NightQuestKillModel extends NightQuestModel {
    @Override
    public Set<NightQuest<?>> getNightQuests() {
        return Set.of(
                new NightQuest<>(EntityType.ZOMBIE, new ChameleonRandom(10, 15)),
                new NightQuest<>(EntityType.CREEPER, new ChameleonRandom(5, 10)),
                new NightQuest<>(EntityType.SKELETON, new ChameleonRandom(10, 15)),
                new NightQuest<>(EntityType.ENDERMAN, new ChameleonRandom(2, 5)));
    }

    public NightQuestKillModel(Player player) {
        super(player);
    }

    // todo: make a chameleon event
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) { return; }
        tryToAddProgress(e.getEntity().getKiller(), e.getEntity().getType());
    }
}
