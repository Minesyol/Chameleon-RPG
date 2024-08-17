package me.keegan.chameleon_rpg.items;

import me.keegan.chameleon_rpg.utils.game.namespacedkeys.IChameleonNamespaced;

public interface IChameleonItem extends IChameleonNamespaced<String> {
    ChameleonItemStackWrapper createItemStackWrapper();
}