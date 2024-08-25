package me.keegan.chameleon_rpg.utils.files.registeries;

import com.google.gson.Gson;
import me.keegan.chameleon_rpg.ChameleonRPG;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;

public final class Registries {
    private static String registryPath = ChameleonRPG.getMainDir();

    public enum Scanner {
        SUBTYPES {
            @Override
            protected <T> Set<Class<? extends T>> scanFor(Class<T> referenceClass) {
                return new Reflections(registryPath, Scanners.SubTypes).getSubTypesOf(referenceClass);
            }

            @Override
            protected <T> void scan(Class<T> referenceClass, RegisterConsumer<T> registerConsumer) {
                for (Class<? extends T> clazz : scanFor(referenceClass)) {
                    try {
                        if (!isAcceptableClass(clazz)) { continue; }
                        registerConsumer.register(hasDefaultConstructor(clazz) ? clazz.getDeclaredConstructor().newInstance() : new Gson().fromJson("{}", clazz));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };


        protected abstract <T> Set<?> scanFor(Class<T> referenceClass);
        protected abstract <T> void scan(Class<T> referenceClass, RegisterConsumer<T> registerConsumer);

        @SuppressWarnings("all")
        protected boolean isAcceptableClass(Class<?> clazz) {
            return !clazz.equals(ChameleonRPG.class) && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers());
        }

        protected <T> boolean hasDefaultConstructor(Class<T> clazz) {
            return Arrays.stream(clazz.getConstructors()).anyMatch(constructor -> constructor.getParameterCount() == 0);
        }
    }

    /**
     * The registry path automatically gets changed back to the default path
     * once the registerReflections method is finished scanning.
     * @param path The path where the registries you want to scan for reside
     */
    public static void setRegistryPath(String path) {
        registryPath = path;
    }

    /**
     *
     * @param scanner Scanner to scan for
     * @param referenceClass Class that the scanner will use as a reference
     * @param registerConsumer The functional interface responsible for registering the registries correctly
     * @param <T> Any type
     */
    public static <T> void registerReflections(Scanner scanner, Class<T> referenceClass, RegisterConsumer<T> registerConsumer) {
        scanner.scan(referenceClass, registerConsumer);
        registryPath = ChameleonRPG.getMainDir();
    }
}
