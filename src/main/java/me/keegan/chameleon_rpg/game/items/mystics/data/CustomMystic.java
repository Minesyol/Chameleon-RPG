package me.keegan.chameleon_rpg.game.items.mystics.data;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
public final class CustomMystic implements Serializable {
    @Serial
    private static final long serialVersionUID = 94329603114413L;

    private HashMap<String, Integer> mysticEnchants = new HashMap<>();

    // left pair = Mystic Enum Name : right pair = Mystic Name
    private Pair<String, String> mysticIdentifier = new MutablePair<>();

    // left pair = lives : right pair = max lives
    private Pair<Integer, Integer> mysticLives = new MutablePair<>();

    // the chatcolor of the mystic if it was recolored
    @Nullable private String mysticChatColor;

    // the original chatcolor of the mystic if it was recolored
    @Nullable private String originalMysticChatColor;

    // the required chatcolor for the sword/bow to t3
    private String mysticRequiredColor;

    private int mysticTier;
    private boolean mysticGemmed;

    public CustomMystic() {
        mysticChatColor = null;
        originalMysticChatColor = null;
    }
}
