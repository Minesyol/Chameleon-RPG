package me.keegan.chameleon_rpg.game.items.mystics.types;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public interface IMystic {
    ItemStack setFreshData(ItemStack itemStack); // executes when mystic is created
    List<String> getFreshLore(@Nullable ChatColor chatColor); // fresh lore
    List<String> getEnchantedLore(@Nullable ChatColor chatColor); // when mystic is enchanted lore
    Material getMaterial();
    String getFreshPrefix(); // fresh, mystic
    String getName(ChatColor chatColor); // blue, red, sword, etc.
    String getEnumName(); // the mystic enum name
    MysticRequiredPantColor getRequiredPantColor();
    LinkedHashMap<ChatColor, Color> getPantColors();
    List<ItemStack> getMysticWellPaneColors();
    List<ChatColor> getTierColors(@Nullable ChatColor chatColor); // itemstack's display name color
    List<Integer> getMysticWellCost();
    int getMaxTier();
    boolean requireSacrificeOnMaxTier();
    boolean isStrongAsIron();

    /*
     * 8/28/24
     * - This is old and outdated. Please use the CustomMystic class
     * - which contains all the mystic information.
     *
     * - The CustomMystic class should be encoded to base64
     * - and be saved in the mystic's persistent data container
     * - under the key "mystic"
     *
     * nbt tag gets written like this
     * mystic = <ENUM_NAME>:<Mystic_Name>
     * mystic_tier = <MYSTIC_TIER>
     *
     * colored nbt tag
     * mystic_is_colored = <ChatColor>
     *
     * swords and bows nbt tag
     * mystic_required_color = <ENUM_NAME>:<Mystic_Name>
     *
     * enchanted mystics nbt tag
     * mystic_lives = <Current_Lives:Max_Lives>
     * mystic_enchant_nbt_key = <Enchant_Level>
     * mystic_offset_length = <Integer>
     *
     * could contain
     * mystic_gemmed = "TRUE"
     */

    enum MysticRequiredPantColor {
        RANDOM_FRESH,
        RANDOM_ANY, // TODO: Implement
        SAME_COLOR
    }

    @Getter
    enum MysticTier {
        FRESH(0),
        TIER_ONE(1),
        TIER_TWO(2),
        TIER_THREE(3);

        private final int tier;

        MysticTier(int tier) {
            this.tier = tier;
        }

        @Nullable
        public String getTierName(int tier) {
            MysticTier mysticTier = Arrays.stream(MysticTier.values())
                    .filter(e -> e.getTier() == tier)
                    .findFirst().orElse(null);

            return mysticTier != null ? mysticTier.name() : null;
        }
    }
}
