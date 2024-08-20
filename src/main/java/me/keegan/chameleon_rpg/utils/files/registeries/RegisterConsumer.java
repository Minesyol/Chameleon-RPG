package me.keegan.chameleon_rpg.utils.files.registeries;

@FunctionalInterface
public interface RegisterConsumer<T> {
    void register(T registry);
}