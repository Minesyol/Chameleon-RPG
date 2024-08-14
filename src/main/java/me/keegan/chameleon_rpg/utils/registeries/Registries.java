package me.keegan.chameleon_rpg.utils.registeries;

import me.keegan.chameleon_rpg.ChameleonRPG;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Modifier;
import java.util.Set;

public final class Registries {
    public enum Scanner {
        SUBTYPES {
            @Override
            public <T> Set<Class<? extends T>> scanFor(Class<T> referenceClass) {
                return new Reflections(ChameleonRPG.getMainDir(), Scanners.SubTypes).getSubTypesOf(referenceClass);
            }
        };

        public abstract <T> Set<Class<? extends T>> scanFor(Class<T> referenceClass);
    }

    /**
     *
     * @param scanner Scanner to scan for
     * @param referenceClass Class that the scanner will use as a reference
     * @param registerConsumer The functional interface responsible for registering the registries correctly
     * @param <T> Any type
     */
    public static <T> void registerReflections(Scanner scanner, Class<T> referenceClass, RegisterConsumer<T> registerConsumer) {
        for (Class<? extends T> clazz : scanner.scanFor(referenceClass)) {
            try {
                if (clazz.equals(ChameleonRPG.class) || clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) { continue; }
                registerConsumer.register(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
