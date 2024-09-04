package me.keegan.chameleon_rpg.utils.objects.classes.singleton;

import com.google.errorprone.annotations.ForOverride;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ChameleonSingleton<T> implements IChameleonPluginState {
    private final HashMap<String, T> instanceMap = new HashMap<>();

    public final <S extends T> S getInstance(Class<S> clazz) {
        if (!instanceMap.containsKey(clazz.getName())) {
            try {
                instanceMap.put(clazz.getName(), clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // this is impossible to be null since the generic states S must extend T
        return clazz.cast(instanceMap.get(clazz.getName()));
    }

    @Override
    public void onPluginEnable() {
        for (Field field : this.getClass().getFields()) {
            if (!field.isAnnotationPresent(StaticInstance.class)) { continue; }
            field.set(null, getInstance(this.getClass()));
        }
    }
}