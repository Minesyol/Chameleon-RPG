package me.keegan.chameleon_rpg.game.items.mystics;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public final class CustomMystic implements Serializable {
    @Serial
    private static final long serialVersionUID = 94329603114413L;

    private final HashMap<String, Integer> mysticEnchants = new HashMap<>();

    // left pair = Mystic Enum Name : right pair = Mystic Name
    private final Pair<String, String> mysticIdentifier = new MutablePair<>();

    // left pair = lives : right pair = max lives
    private final Pair<Integer, Integer> mysticLives = new MutablePair<>();

    // the chatcolor of the mystic if it was recolored
    private String mysticChatColor;

    // the required color for the sword/bow to t3
    private String mysticRequiredColor;

    private int mysticTier;
    private boolean mysticGemmed;

    public static CustomMystic builder() {
        return new Builder();
    }

    public static class Builder {

    }
}
