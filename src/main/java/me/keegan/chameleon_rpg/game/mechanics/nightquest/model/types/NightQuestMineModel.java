package me.keegan.chameleon_rpg.game.mechanics.nightquest.model.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import org.bukkit.entity.Player;

import java.util.Set;

public class NightQuestMineModel extends NightQuestModel {
    @Override
    public <T> Set<NightQuest<? extends T>> getNightQuests() {
        return null;
    }

    public NightQuestMineModel(Player player) {
        super(player);
    }
}
