package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.entity.Player;

import java.util.*;

public class NightQuestFactory implements IChameleonPluginState {
    private static final List<NightQuestModel> nightQuestTypes = new ArrayList<>();

    public static NightQuestModel createRandomNightQuest(Player player) {
        return nightQuestTypes.get(new Random().nextInt(0, nightQuestTypes.size()));
    }

    @Override
    public void onPluginEnable() {
        Registries.setRegistryPath(String.format("%s.game.mechanics.nightquest.types", ChameleonRPG.getMainDir()));
        Registries.registerReflections(Registries.Scanner.SUBTYPES, NightQuestModel.class, nightQuestTypes::add);
    }
}
