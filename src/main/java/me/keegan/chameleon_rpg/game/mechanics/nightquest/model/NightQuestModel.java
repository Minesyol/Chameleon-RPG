package me.keegan.chameleon_rpg.game.mechanics.nightquest.model;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuestController;
import me.keegan.chameleon_rpg.utils.classes.math.ChameleonRandom;
import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Set;

public abstract class NightQuestModel implements Listener {
    public abstract Set<NightQuest<?>> getNightQuests();

    private boolean complete;
    private int progress;
    private final int requiredProgress;
    private final NightQuest<?> nightQuest;

    protected final Player player;

    public NightQuestModel(Player player) {
        this.player = player;

        nightQuest = ChameleonRandom.chooseRandom(getNightQuests());
        requiredProgress = nightQuest.getRequiredProgress();

        ChameleonChat.sendMessage(player, "Night quest: " + nightQuest.getRequiredProgress());
    }

    public boolean isComplete() {
        return complete;
    }

    private void completeNightQuest() {
        complete = true;
        ChameleonChat.sendMessage(player, "Night quest completed.");
    }

    protected final <T> void tryToAddProgress(@NonNull Player targetPlayer, @NonNull T nightQuestTarget) {
        NightQuestModel nightQuestModel = NightQuestController.getOngoingNightQuestModel(targetPlayer);
        progress += (nightQuestModel != null && nightQuestModel.nightQuest.getTarget().equals(nightQuestTarget)) ? 1 : 0;

        if (progress < requiredProgress) { return; }
        completeNightQuest();
    }
}
