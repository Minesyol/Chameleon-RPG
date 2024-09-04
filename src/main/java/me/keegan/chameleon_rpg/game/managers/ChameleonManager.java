package me.keegan.chameleon_rpg.game.managers;

import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import me.keegan.chameleon_rpg.utils.objects.classes.singleton.ChameleonSingleton;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;

public class ChameleonManager extends ChameleonSingleton<ChameleonManager> implements IChameleonListener {
    @Override
    public void onPluginEnable() {
        try {
            this.getClass().getDeclaredConstructor().newInstance();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
