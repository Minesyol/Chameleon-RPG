package me.keegan.chameleon_rpg;

import me.keegan.chameleon_rpg.utils.game.recipies.IChameleonRecipe;
import me.keegan.chameleon_rpg.utils.registeries.Registries;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Files are saved in .json formats.
 * Maybe in the future I'll learn SQLite JDBC
 * and save data onto there.
 */

public final class ChameleonRPG extends JavaPlugin {
    private static final String MAIN_DIR = "me.keegan.chameleon_rpg";
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static String getMainDir() {
        return MAIN_DIR;
    }

    public static void info(String info) {
        plugin.getLogger().info(info);
    }

    @Override
    public void onEnable() {
        plugin = this;

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // register listeners
        Registries.registerReflections(Registries.Scanner.SUBTYPES, Listener.class,
                registry -> getServer().getPluginManager().registerEvents(registry, this));

        // add recipes
        Registries.registerReflections(Registries.Scanner.SUBTYPES, IChameleonRecipe.class,
                registry -> Arrays.stream(registry.getChameleonRecipes())
                        .forEach(chameleonRecipe -> Bukkit.addRecipe(chameleonRecipe.getRecipe())));
    }

    @Override
    public void onDisable() {

    }
}
