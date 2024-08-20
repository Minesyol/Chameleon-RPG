package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class NightQuestFactory implements IChameleonPluginState {
    private static final Set<NightQuestModel> nightQuestTypes = new HashSet<>();

    public static NightQuestModel createRandomNightQuest(Player player) {

    }

    @Override
    public void onPluginEnable() {
        Registries.setRegistryPath(String.format("%s.game.mechanics.nightquest.types", ChameleonRPG.getMainDir()));
        Registries.registerReflections(Registries.Scanner.SUBTYPES, NightQuestModel.class, nightQuestTypes::add);
    }
}
