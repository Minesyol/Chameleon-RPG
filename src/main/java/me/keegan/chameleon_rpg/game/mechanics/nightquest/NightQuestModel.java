package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class NightQuestModel implements Listener {
    private int progress;
    private int requiredProgress;

    protected final Player player;

    public NightQuestModel(Player player) {
        this.player = player;
    }

    public final boolean hasCurrentNightQuest(Player player) {
        NightQuestModel nightQuestModel = NightQuestController.getOngoingNightQuestModel(player);
        return nightQuestModel != null && nightQuestModel.equals(this);
    }
}
