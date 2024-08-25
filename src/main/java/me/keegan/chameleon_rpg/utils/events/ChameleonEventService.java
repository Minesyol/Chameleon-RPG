package me.keegan.chameleon_rpg.utils.events;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.interfaces.IChameleonPluginState;
import org.bukkit.event.EventHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ChameleonEventService implements IChameleonPluginState {
    private static final List<Method> eventMethods = new ArrayList<>();

    public static void callEvent(ChameleonEvent chameleonEvent) {
        if (chameleonEvent instanceof EntityDeathByPlayerEvent e) {
            ChameleonRPG.info(e.getVictim().getName());
            ChameleonRPG.info(e.getKiller().getName());
        }
    }

    @Override
    public void onPluginEnable() {
        Registries.registerReflections(Registries.Scanner.SUBTYPES, IChameleonListener.class, registry -> {
            for (Method method : registry.getClass().getMethods()) {
                if (Arrays.stream(method.getAnnotations()).noneMatch(annotation -> annotation instanceof EventHandler)
                        || method.getParameterCount() != 1
                        || !this.getClass().isAssignableFrom(method.getParameterTypes()[0])) {
                    continue;
                }

                eventMethods.add(method);
            }
        });
    }

}
