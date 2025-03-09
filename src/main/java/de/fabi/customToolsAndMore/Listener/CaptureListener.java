/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Listener;


import de.fabi.customToolsAndMore.Main;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaptureListener implements Listener {

    private static final List<EntityType> FORBIDDEN_MOBS = Arrays.asList(
            EntityType.WARDEN,
            EntityType.ENDER_DRAGON,
            EntityType.WITHER,
            EntityType.TNT,
            EntityType.TNT_MINECART,
            EntityType.MINECART,
            EntityType.CHEST_MINECART,
            EntityType.HOPPER_MINECART,
            EntityType.FURNACE_MINECART,
            EntityType.BOAT,
            EntityType.CHEST_BOAT,
            EntityType.ARMOR_STAND,
            EntityType.DRAGON_FIREBALL,
            EntityType.END_CRYSTAL,
            EntityType.ENDER_PEARL,
            EntityType.EGG,
            EntityType.EVOKER_FANGS,
            EntityType.EXPERIENCE_BOTTLE,
            EntityType.EXPERIENCE_ORB,
            EntityType.TEXT_DISPLAY,
            EntityType.FALLING_BLOCK,
            EntityType.FIREBALL,
            EntityType.FIREWORK_ROCKET,
            EntityType.SMALL_FIREBALL,
            EntityType.ITEM,
            EntityType.ITEM_FRAME,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.ITEM_DISPLAY,
            EntityType.LEASH_KNOT,
            EntityType.LIGHTNING_BOLT,
            EntityType.PAINTING,
            EntityType.POTION,
            EntityType.SHULKER_BULLET,
            EntityType.TRIDENT,
            EntityType.WITHER_SKULL,
            EntityType.RAVAGER
    );

    // Event-Handler-Methode bei Spieler-Interaktion mit einem Mob (left-click)
    @EventHandler
    public void onPlayerLeftClickEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // Überprüfen, ob der Spieler einen Pokeball benutzt
        if (player.isSneaking() && isPokeball(itemInHand)) {
            // Verhindern, dass verbotene Mobs gefangen werden
            if (FORBIDDEN_MOBS.contains(entity.getType())) {
                player.sendMessage(ChatColor.RED + "Du kannst diesen Mob nicht einfangen!");
                return;
            }

            // Update the pokeball lore
            applyMeta(itemInHand, entity);

            String entityName = entity.getCustomName() != null ? entity.getCustomName() : entity.getType().name();
            player.sendMessage(ChatColor.GREEN + "Du hast einen " + entityName + " gefangen!");

            entity.remove();
        }
    }

    // Überprüfen, ob ein Item ein Pokeball ist
    private boolean isPokeball(ItemStack item) {
        if (item == null || item.getType() != Material.PLAYER_HEAD) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName() || !meta.hasCustomModelData()) {
            return false;
        }

        return meta.getDisplayName().equals(ChatColor.RED + "Pokeball") && meta.getCustomModelData() == 8;
    }

    private void applyMeta(ItemStack pokeBall, Entity entity) {
        ItemMeta itemMeta = pokeBall.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Gefangener Mob: " + ChatColor.YELLOW + entity.getType().name());

        if (entity.getCustomName() != null) {
            lore.add(ChatColor.GRAY + "Name: " + ChatColor.YELLOW + entity.getCustomName());
        }

        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(Main.POKEBALL_KEY, PersistentDataType.STRING, entity.getType().name());
        pokeBall.setItemMeta(itemMeta);
    }
}
