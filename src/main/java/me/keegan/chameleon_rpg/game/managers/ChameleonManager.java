package me.keegan.chameleon_rpg.game.managers;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import me.keegan.chameleon_rpg.utils.objects.classes.singleton.ChameleonSingleton;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.HashMap;

public class ChameleonManager extends ChameleonSingleton<ChameleonManager> implements IChameleonListener {
    protected final void test() {
        try {
            ChameleonManager customManager = new ByteBuddy()
                    .subclass(ChameleonManager.class)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Works. klb"))
                    .make()
                    .load(ChameleonManager.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor().newInstance();

            ChameleonRPG.info(customManager.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            ChameleonManager customManager = new ByteBuddy()
                    .subclass(ChameleonManager.class)
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("klbfsdfdsfdsf"))
                    .make()
                    .load(ChameleonManager.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor().newInstance();

            ChameleonRPG.info(customManager.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
