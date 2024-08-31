package me.keegan.chameleon_rpg.game.items.mystics.types;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;

public enum Mystic implements IMystic {
    FRESH {
        @Override
        public ItemStack setFreshData(ItemStack itemStack) {
            return null;
        }

        @Override
        public List<String> getFreshLore(@Nullable ChatColor chatColor) {
            return List.of();
        }

        @Override
        public List<String> getEnchantedLore(@Nullable ChatColor chatColor) {
            return List.of();
        }

        @Override
        public Material getMaterial() {
            return null;
        }

        @Override
        public String getFreshPrefix() {
            return "";
        }

        @Override
        public String getName(ChatColor chatColor) {
            return "";
        }

        @Override
        public String getEnumName() {
            return "";
        }

        @Override
        public MysticRequiredPantColor getRequiredPantColor() {
            return null;
        }

        @Override
        public LinkedHashMap<ChatColor, Color> getPantColors() {
            return null;
        }

        @Override
        public List<ItemStack> getMysticWellPaneColors() {
            return List.of();
        }

        @Override
        public List<ChatColor> getTierColors(@Nullable ChatColor chatColor) {
            return List.of();
        }

        @Override
        public List<Integer> getMysticWellCost() {
            return List.of();
        }

        @Override
        public int getMaxTier() {
            return 0;
        }

        @Override
        public boolean requireSacrificeOnMaxTier() {
            return false;
        }

        @Override
        public boolean isStrongAsIron() {
            return false;
        }
    };
}
