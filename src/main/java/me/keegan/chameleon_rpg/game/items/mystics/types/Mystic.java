package me.keegan.chameleon_rpg.game.items.mystics.types;

import me.keegan.chameleon_rpg.utils.classes.builders.LoreBuilder;
import me.keegan.chameleon_rpg.utils.classes.string.Inflector;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public enum Mystic implements IMystic {
    DEFAULT {
        @Override
        public ItemStack setFreshData(ItemStack itemStack) {
            return itemStack;
        }

        @Override
        public List<String> getFreshLore(ChatColor chatColor) {
            return new LoreBuilder()
                    .write("Kept on death")
                    .newLine()
                    .newLine().setColor(chatColor).write("Used in the mystic well")
                    .newLine().write("Also, a fashion statement")
                    .build();
        }

        @Override
        public List<String> getEnchantedLore(ChatColor chatColor) {
            return new LoreBuilder()
                    .setColor(chatColor).write("As strong as iron")
                    .build();
        }

        @Override
        public Material getMaterial() {
            return Material.LEATHER_LEGGINGS;
        }

        @Override
        public String getFreshPrefix() {
            return "Fresh";
        }

        @Override
        public String getName(ChatColor chatColor) {
            return (this.getPantColors().containsKey(chatColor))
                    ? Inflector.getInstance().titleCase(chatColor.name().replace("GOLD", "ORANGE")) + " Pants"
                    : "";
        }

        @Override
        public String getEnumName() {
            return this.name();
        }

        @Override
        public MysticRequiredPantColor getRequiredPantColor() {
            return MysticRequiredPantColor.SAME_COLOR;
        }

        @Override
        public LinkedHashMap<ChatColor, Color> getPantColors() {
            return new LinkedHashMap<>() {{
                put(ChatColor.RED, Color.fromRGB(255, 85, 85));
                put(ChatColor.GOLD, Color.fromRGB(255, 170, 0));
                put(ChatColor.YELLOW, Color.fromRGB(255, 255, 85));
                put(ChatColor.GREEN, Color.fromRGB(85, 255, 85));
                put(ChatColor.BLUE, Color.fromRGB(85, 85, 255));
            }};
        }

        @Override
        public List<ItemStack> getMysticWellPaneColors() {
            return Arrays.asList(
                    new ItemStack(Material.PINK_STAINED_GLASS),
                    new ItemStack(Material.LIME_STAINED_GLASS),
                    new ItemStack(Material.YELLOW_STAINED_GLASS),
                    new ItemStack(Material.RED_STAINED_GLASS));
        }

        @Override
        public List<ChatColor> getTierColors(ChatColor chatColor) {
            return Arrays.asList(
                    chatColor,
                    chatColor,
                    chatColor,
                    chatColor
            );
        }

        @Override
        public List<Integer> getMysticWellCost() {
            return Arrays.asList(
                    1000,
                    4000,
                    8000
            );
        }

        @Override
        public int getMaxTier() {
            return 3;
        }

        @Override
        public boolean requireSacrificeOnMaxTier() {
            return true;
        }

        @Override
        public boolean isStrongAsIron() {
            return true;
        }
    },
}
