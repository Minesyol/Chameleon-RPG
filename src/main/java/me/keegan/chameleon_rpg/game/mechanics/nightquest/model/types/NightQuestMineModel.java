package me.keegan.chameleon_rpg.game.mechanics.nightquest.model.types;

import me.keegan.chameleon_rpg.game.mechanics.nightquest.NightQuest;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.classes.math.ChameleonRandom;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NightQuestMineModel extends NightQuestModel {
    @Override
    public Set<NightQuest<?>> getNightQuests() {
        return Set.of(new NightQuest<BlockType>(BlockType.DIAMOND_ORE, new ChameleonRandom(5, 10)));
    }

    public NightQuestMineModel(Player player) {
        super(player);
    }
}
