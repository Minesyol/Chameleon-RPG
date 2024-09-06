package me.keegan.chameleon_rpg.utils.objects.classes.singleton;

import com.google.gson.Gson;
import javassist.*;
import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import static net.bytebuddy.matcher.ElementMatchers.named;

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
        try {
            ChameleonSingleton<?> chameleonSingleton = new ByteBuddy()
                    //.subclass(ChameleonSingleton.class) // an anonymous class that has the ChameleonSingleton as its super
                    .rebase(ChameleonSingleton.class)// rebases the original class so that all new instances will have all the changes
                    .method(named("toString"))
                    .intercept(FixedValue.value("THIS WORKS WTTTF"))
                    .make()
                    .load(ChameleonSingleton.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor().newInstance();

            ChameleonRPG.info(chameleonSingleton.getClass().getName());
            ChameleonRPG.info(chameleonSingleton.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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