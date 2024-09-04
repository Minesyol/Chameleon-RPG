package me.keegan.chameleon_rpg.game.mechanics.nightquest.model;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuestController;
import me.keegan.chameleon_rpg.utils.objects.classes.math.ChameleonRandom;
import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Set;

public abstract class NightQuestModel implements IChameleonListener {
    public abstract Set<NightQuest<?>> getNightQuests();
    public abstract <T extends NightQuest<?>> String getNightQuestAssignedMessage(T nightQuestTarget, int requiredProgress);

    private final String NIGHT_QUEST_MESSAGE_PREFIX = ChatColor.BLUE + ChatColor.BOLD.toString() + "NIGHT QUEST!";

    private boolean complete;
    private int progress;
    private final int requiredProgress;
    private final NightQuest<?> nightQuest;

    protected final Player player;

    public NightQuestModel(Player player) {
        this.player = player;

        nightQuest = ChameleonRandom.chooseRandom(getNightQuests());
        requiredProgress = nightQuest.getRequiredProgress();

        sendMessage(player, getNightQuestAssignedMessage(nightQuest, requiredProgress));
    }

    // should have probably been a logger interface/mixin
    public final void sendMessage(Player player, String message) {
        ChameleonChat.setMessagePrefix(NIGHT_QUEST_MESSAGE_PREFIX);
        ChameleonChat.sendMessage(player, ChatColor.GRAY + message);
    }

    public boolean isComplete() {
        return complete;
    }

    private void completeNightQuest() {
        complete = true;
        sendMessage(player, "Completed!");
    }

    protected final <T> void tryToAddProgress(@NonNull Player targetPlayer, @NonNull T nightQuestTarget) {
        NightQuestModel nightQuestModel = NightQuestController.getOngoingNightQuestModel(targetPlayer);
        if (nightQuestModel == null || !nightQuestModel.nightQuest.getTarget().equals(nightQuestTarget)) { return; }

        if (player == null) {
            nightQuestModel.tryToAddProgress(targetPlayer, nightQuestTarget);
            return;
        }

        progress++;
        sendMessage(player, String.format(ChatColor.BLUE + "(%s/%s)", progress, requiredProgress));
        if (progress < requiredProgress) { return; }

        completeNightQuest();
    }
}
