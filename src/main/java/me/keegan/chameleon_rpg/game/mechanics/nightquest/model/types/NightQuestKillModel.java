package me.keegan.chameleon_rpg.game.mechanics.nightquest.model.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashSet;
import java.util.Set;

public class NightQuestKillModel extends NightQuestModel {
    public NightQuestKillModel(Player player) {
        super(player);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        // example; will finish later
        if (!hasCurrentNightQuest(e.getEntity().getKiller())) { return; }
    }
}
