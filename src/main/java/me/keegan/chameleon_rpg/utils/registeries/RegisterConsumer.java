package me.keegan.chameleon_rpg.utils.registeries;

@FunctionalInterface
public interface RegisterConsumer<T> {
    void register(T registry);
}