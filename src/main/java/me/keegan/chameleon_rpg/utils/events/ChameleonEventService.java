package me.keegan.chameleon_rpg.utils.events;

import com.google.gson.Gson;
import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;
import org.bukkit.event.EventHandler;

import java.lang.reflect.Method;
import java.util.*;

public final class ChameleonEventService implements IChameleonPluginState {
    private static final List<Method> eventMethods = new ArrayList<>();

    public static void callEvent(ChameleonEvent<?> chameleonEvent) {
        List<Method> chameleonEventMethods = new ArrayList<>();

        eventMethods.stream()
                .filter(method -> chameleonEvent.getClass().isAssignableFrom(method.getParameterTypes()[0]))
                .forEach(chameleonEventMethods::add);

        chameleonEventMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(EventHandler.class).priority().getSlot()));

        for (Method method : chameleonEventMethods) {
            try {
                Class<?> clazz = method.getDeclaringClass();

                method.invoke(Registries.hasDefaultConstructor(clazz)
                        ? clazz.getDeclaredConstructor().newInstance()
                        : new Gson().fromJson("{}", method.getDeclaringClass()), chameleonEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onPluginEnable() {
        ChameleonEvent<?> chameleonEvent = new ChameleonEvent<>(null);

        Registries.registerReflections(Registries.Scanner.SUBTYPES, IChameleonListener.class, registry -> {
            for (Method method : registry.getClass().getMethods()) {
                if (Arrays.stream(method.getAnnotations()).noneMatch(annotation -> annotation instanceof EventHandler)
                        || method.getParameterCount() != 1
                        || !chameleonEvent.getClass().isAssignableFrom(method.getParameterTypes()[0])) {
                    continue;
                }

                eventMethods.add(method);
            }
        });
    }

}
