package me.keegan.chameleon_rpg.utils.interfaces;

import com.google.errorprone.annotations.ForOverride;

/**
 * Implement the methods you want to use via manually overriding them
 */
public interface IChameleonPluginState {
    @ForOverride
    default void onPluginEnable() {}

    @ForOverride
    default void onPluginDisable() {}
}
