package me.keegan.chameleon_rpg.game.mechanics.nightquest.model.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.classes.math.ChameleonRandom;
import me.keegan.chameleon_rpg.utils.classes.string.Inflector;
import me.keegan.chameleon_rpg.utils.events.model.types.EntityDeathByPlayerCEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

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

    @Override
    public <T extends NightQuest<?>> String getNightQuestAssignedMessage(T nightQuestTarget, int requiredProgress) {
        return String.format("Kill %s%s %s!", ChatColor.RED, String.valueOf(requiredProgress) + ChatColor.GRAY, Inflector.getInstance().pluralize(((EntityType) nightQuestTarget.getTarget()).name().toLowerCase()));
    }

    public NightQuestKillModel(Player player) {
        super(player);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathByPlayerCEvent e) {
        tryToAddProgress(e.getKiller(), e.getVictim().getType());
    }
}
