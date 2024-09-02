package me.keegan.chameleon_rpg.utils.game.entity.player;

import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.game.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.files.FileUtils;
import me.keegan.chameleon_rpg.utils.files.SerializationUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

public final class PlayerUtils {
    private static final String PLAYER_DATA_DIR = "data/players";
    private static final String CHAMELEON_PLAYER_DATA_DIR = PLAYER_DATA_DIR + "/base";

    private static final HashMap<UUID, ChameleonPlayer> chameleonPlayerCache = new HashMap<>();

    private PlayerUtils() {}

    public static void saveChameleonPlayerToFile(@Nonnull ChameleonPlayer chameleonPlayer) {
        FileUtils.saveAsFile(CHAMELEON_PLAYER_DATA_DIR, chameleonPlayer.getPlayer().getUniqueId() + ".txt", SerializationUtils.encodeToBase64(chameleonPlayer), false);
    }

    @Nullable
    public static ChameleonPlayer getChameleonPlayerFromFile(@Nonnull UUID uuid) {
        if (chameleonPlayerCache.containsKey(uuid)) { return chameleonPlayerCache.get(uuid); }

        // get base64 encoded ChameleonPlayer object
        String encodedChameleonPlayer = FileUtils.getFromFile(CHAMELEON_PLAYER_DATA_DIR, uuid + ".txt");
        if (encodedChameleonPlayer == null) { return null; }

        // decode and cast to a ChameleonPlayer class
        ChameleonPlayer chameleonPlayer = SerializationUtils.decodeFromBase64(encodedChameleonPlayer, ChameleonPlayer.class);
        chameleonPlayer.setPlayer(ChameleonRPG.getPlugin().getServer().getPlayer(uuid));

        chameleonPlayerCache.put(uuid, chameleonPlayer);

        return chameleonPlayer;
    }

    @Nullable
    public static ChameleonPlayer getChameleonPlayerFromFile(@Nonnull Player player) {
        return getChameleonPlayerFromFile(player.getUniqueId());
    }

    public static void removeChameleonPlayerFromCache(@NonNull ChameleonPlayer chameleonPlayer) {
        chameleonPlayerCache.remove(chameleonPlayer.getPlayer().getUniqueId());
    }

    public static void addMaxHearts(Player player, double hearts) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + hearts * 2);
    }
}
