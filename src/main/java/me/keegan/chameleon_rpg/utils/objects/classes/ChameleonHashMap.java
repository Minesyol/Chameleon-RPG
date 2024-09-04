package me.keegan.chameleon_rpg.utils.objects.classes;

import java.util.HashMap;

public final class ChameleonHashMap<K, V> extends HashMap<K, V> {
    public ChameleonHashMap() {}

    public void putIfExists(K key, V value) {
        if (super.containsKey(key))
            super.put(key, value);
    }
}
