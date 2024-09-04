package me.keegan.chameleon_rpg.game.mechanics.nightquest;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.game.mechanics.nightquest.model.NightQuestModel;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;
import org.bukkit.entity.Player;

import java.util.*;

public final class NightQuestFactory implements IChameleonPluginState {
    private static final List<Class<? extends NightQuestModel>> nightQuestTypes = new ArrayList<>();

    public static NightQuestModel createRandomNightQuest(Player player) {
        try {
           return nightQuestTypes.get(new Random().nextInt(0, nightQuestTypes.size())).getConstructor(Player.class).newInstance(player);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onPluginEnable() {
        Registries.setRegistryPath(String.format("%s.game.mechanics.nightquest.model.types", ChameleonRPG.getMainDir()));
        Registries.registerReflections(Registries.Scanner.SUBTYPES, NightQuestModel.class, registry -> nightQuestTypes.add(registry.getClass()));
    }
}
