package me.keegan.chameleon_rpg.utils.objects.interfaces;

import com.google.errorprone.annotations.ForOverride;
import me.keegan.chameleon_rpg.utils.objects.annotations.Mixin;

/**
 * Implement the methods you want to use via manually overriding them
 */
@Mixin
public interface IChameleonPluginState {
    @ForOverride
    default void onPluginEnable() {}

    @ForOverride
    default void onPluginDisable() {}
}
