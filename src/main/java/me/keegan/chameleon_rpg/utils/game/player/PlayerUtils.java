package me.keegan.chameleon_rpg.utils.game.player;

import me.keegan.chameleon_rpg.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.files.FileUtils;
import me.keegan.chameleon_rpg.utils.files.SerializationUtils;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class PlayerUtils {
    private static final String PLAYER_DATA_DIR = "data/players";
    private static final String CHAMELEON_PLAYER_DATA_DIR = PLAYER_DATA_DIR + "/base";

    public static void saveChameleonPlayerToFile(@Nonnull ChameleonPlayer chameleonPlayer) {
        if (chameleonPlayer.getPlayer() == null) { return; }
        FileUtils.saveAsFile(CHAMELEON_PLAYER_DATA_DIR, chameleonPlayer.getPlayer().getUniqueId() + ".txt", SerializationUtils.encodeToBase64(chameleonPlayer), false);
    }

    public static ChameleonPlayer getChameleonPlayerFromFile(@Nonnull UUID uuid) {
        // get base64 encoded ChameleonPlayer object
        String encodedChameleonPlayer = FileUtils.getFromFile(CHAMELEON_PLAYER_DATA_DIR, uuid + ".txt");
        if (encodedChameleonPlayer  == null) { return null; }

        // return the decoded version
        return SerializationUtils.decodeFromBase64(encodedChameleonPlayer, ChameleonPlayer.class);
    }

    public static ChameleonPlayer getChameleonPlayerFromFile(@Nonnull Player player) {
        return getChameleonPlayerFromFile(player.getUniqueId());
    }
}
