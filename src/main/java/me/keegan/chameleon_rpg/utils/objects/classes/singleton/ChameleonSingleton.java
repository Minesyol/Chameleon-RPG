package me.keegan.chameleon_rpg.utils.objects.classes.singleton;

import com.google.gson.Gson;
import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class ChameleonSingleton<T> implements IChameleonPluginState {
    private final HashMap<String, T> instanceMap = new HashMap<>();

    public final <S extends T> S getInstance(Class<S> clazz) {
        if (!instanceMap.containsKey(clazz.getName())) {
            try {
                instanceMap.put(clazz.getName(), Registries.hasDefaultConstructor(clazz) ? clazz.getDeclaredConstructor().newInstance() : new Gson().fromJson("{}", clazz));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // this is impossible to be null since the generic states S must extend T
        return clazz.cast(instanceMap.get(clazz.getName()));
    }

    // call this with super when overriding
    @Override
    public void onPluginEnable() {
        for (Field field : getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(StaticInstance.class) || !Modifier.isStatic(field.getModifiers())) { continue; }
            field.setAccessible(true);

            try {
                // just pray please it's not possible to fix unless I pass the T generic as a param
                field.set(null, getInstance((Class<? extends T>) field.getDeclaringClass()));
            }catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}