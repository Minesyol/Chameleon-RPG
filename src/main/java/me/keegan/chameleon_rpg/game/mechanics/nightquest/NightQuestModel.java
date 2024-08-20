package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import org.bukkit.entity.Player;

public abstract class NightQuestModel {
    private int progress;
    private int requiredProgress;

    public NightQuestModel(Player player) {
        progress = 5;
    }
}
