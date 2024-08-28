package me.keegan.chameleon_rpg.game.items.mystics;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public final class CustomMystic implements Serializable {
    @Serial
    private static final long serialVersionUID = 94329603114413L;

    HashMap<String, Integer> mysticEnchants = new HashMap<>();

    // left pair = Mystic Enum Name : right pair = Mystic Name
    Pair<String, String> mysticIdentifier = new MutablePair<>();

    // left pair = lives : right pair = max lives
    Pair<Integer, Integer> mysticLives = new MutablePair<>();

    // the chatcolor of the mystic if it was recolored
    String mysticChatColor;

    // the required color for the sword/bow to t3
    String mysticRequiredColor;

    int mysticTier;
    boolean mysticGemmed;
}
