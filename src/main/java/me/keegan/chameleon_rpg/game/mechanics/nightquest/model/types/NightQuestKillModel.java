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
    // todo: for some reason my intellij broke so EntityType does not appear. use that when can.
    @Override
    public <T> Set<NightQuest<? extends T>> getNightQuests() {
        return new HashSet<>(Set.of(
                new NightQuest<>(null, null)));
    }

    public NightQuestKillModel(Player player) {
        super(player);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        // example; will finish later
        if (!hasCurrentNightQuest(e.getEntity().getKiller())) { return; }
    }
}
