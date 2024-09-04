package me.keegan.chameleon_rpg.utils.objects.classes;

import com.google.errorprone.annotations.ForOverride;

import java.util.HashMap;

public class ChameleonSingleton<T> {
    /**
     * TODO: Replace this thing with an annotation please
     * Use when defining a private static instance variable with the getInstance method.
     * <p>
     * Example:
     *
     * @Override
     * protected void defineInstance() {
     *     instance = super.getInstance(this.getClass());
     * }
     */
    @ForOverride
    protected void defineInstance() {}

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
}