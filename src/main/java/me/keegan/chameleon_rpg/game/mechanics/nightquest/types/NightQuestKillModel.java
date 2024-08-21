package me.keegan.chameleon_rpg.game.mechanics.nightquest.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuestModel;
import me.keegan.chameleon_rpg.utils.classes.math.ChameleonRandom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

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
