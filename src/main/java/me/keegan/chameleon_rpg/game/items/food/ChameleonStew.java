package me.keegan.chameleon_rpg.game.items.food;

import me.keegan.chameleon_rpg.game.items.ChameleonItemStackWrapper;
import me.keegan.chameleon_rpg.game.items.IChameleonItem;
import me.keegan.chameleon_rpg.game.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.game.tasks.TaskScheduler;
import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonIngredientWrapper;
import me.keegan.chameleon_rpg.utils.game.namespacedkeys.ChameleonNamespacedKeys;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipe;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipeShape;
import me.keegan.chameleon_rpg.utils.game.recipies.IChameleonRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ChameleonStew implements IChameleonRecipe, IChameleonItem, Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        ItemStack itemConsumed = e.getItem();
        Player player = e.getPlayer();

        String value = ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.CHAMELEON_ITEMSTACK_KEY,
                PersistentDataType.STRING,
                itemConsumed.getItemMeta().getPersistentDataContainer());
        if (value == null || !value.equals(getNamespacedValue())) { return; }

        if (PlayerUtils.getChameleonPlayerFromFile(player) != null) {
            e.setCancelled(true);
            return;
        }

        PlayerUtils.addMaxHearts(player, 2);
        PlayerUtils.saveChameleonPlayerToFile(new ChameleonPlayer(player));

        Consumer<String> consumer = (string) -> {
            ChameleonChat.sendMessage(player, string);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        };

        TaskScheduler taskScheduler = TaskScheduler.createTaskQueue();
        taskScheduler.addTask(consumer, "Welcome to the Chameleon RPG.", 45, false);
        taskScheduler.addTask(consumer, "Since you ate the Chameleon Stew,", 45, false);
        taskScheduler.addTask(consumer, "You can start your adventure.", 45, false);
        taskScheduler.addTask(consumer, "Good luck!", 65, false);
        taskScheduler.runTasks();
    }

    @Override
    public String getNamespacedValue() {
        return "chameleon_stew";
    }

    @Override
    public ChameleonItemStackWrapper createItemStackWrapper() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);

        SuspiciousStewMeta itemMeta = (SuspiciousStewMeta) itemStack.getItemMeta();
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60 * 20, 3), true);
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60 * 20, 14), true);
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS, 60 * 20, 4), true);

        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sBecome a chameleon", ChatColor.GRAY));

        itemMeta.setDisplayName(String.format("%sChameleon Stew", ChatColor.WHITE));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return new ChameleonItemStackWrapper(itemStack, getNamespacedValue());
    }

    @Override
    public ChameleonRecipe[] getChameleonRecipes() {
        return new ChameleonRecipe[]{
                ChameleonRecipe.builder()
                        .namespaceKey("Chameleon_stew")
                        .shaped()
                        .shape(new ChameleonRecipeShape("LLL", "LSL", "LLL"))
                        .mapIngredient('L', new ChameleonIngredientWrapper<>(Material.LIME_DYE))
                        .mapIngredient('S', new ChameleonIngredientWrapper<>(Material.MUSHROOM_STEW))
                        .next()
                        .result(createItemStackWrapper().getItemStack())
                        .build()};
    }
}
