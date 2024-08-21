package me.keegan.chameleon_rpg.game.mechanics.nightquest.model;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuestController;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Set;

public abstract class NightQuestModel implements Listener {
    public abstract <T> Set<NightQuest<? extends T>> getNightQuests();

    private int progress;
    private int requiredProgress;

    protected final Player player;

    public NightQuestModel(Player player) {
        this.player = player;
    }

    public final boolean hasCurrentNightQuest(Player targetPlayer) {
        NightQuestModel nightQuestModel = NightQuestController.getOngoingNightQuestModel(targetPlayer);
        return nightQuestModel != null && nightQuestModel.equals(this);
    }
}
