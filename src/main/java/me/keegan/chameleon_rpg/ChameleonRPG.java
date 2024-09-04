package me.keegan.chameleon_rpg;

import me.keegan.chameleon_rpg.game.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import me.keegan.chameleon_rpg.utils.game.recipies.IChameleonRecipe;
import me.keegan.chameleon_rpg.utils.files.registeries.Registries;
import me.keegan.chameleon_rpg.utils.objects.interfaces.IChameleonPluginState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

        // call all IChameleonPluginState onPluginEnable methods
        Registries.registerReflections(Registries.Scanner.SUBTYPES, IChameleonPluginState.class, IChameleonPluginState::onPluginEnable);
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            ChameleonPlayer chameleonPlayer = PlayerUtils.getChameleonPlayerFromFile(player);
            if (chameleonPlayer == null) { continue; }

            PlayerUtils.saveChameleonPlayerToFile(chameleonPlayer);
        }

        // call all IChameleonPluginState onPluginDisable methods
        Registries.registerReflections(Registries.Scanner.SUBTYPES, IChameleonPluginState.class, IChameleonPluginState::onPluginDisable);
    }
}
