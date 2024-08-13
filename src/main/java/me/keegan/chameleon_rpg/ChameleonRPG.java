package me.keegan.chameleon_rpg;

import me.keegan.chameleon_rpg.utils.registeries.Registries;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChameleonRPG extends JavaPlugin {
    private static final String MAIN_DIR = "me.keegan.chameleon_rpg";
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static String getMainDir() {
        return MAIN_DIR;
    }

    @Override
    public void onEnable() {
        plugin = this;

        Registries.registerReflections(Registries.Scanner.SUBTYPES,
                Listener.class,
                (registry -> getServer().getPluginManager().registerEvents(registry, this)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
