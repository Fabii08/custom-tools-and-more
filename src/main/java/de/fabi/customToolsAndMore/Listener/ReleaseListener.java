package de.fabi.customToolsAndMore.Listener;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/


import de.fabi.customToolsAndMore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ReleaseListener implements Listener {


    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();


        if (event.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK
                && !player.isSneaking()
                && isPokeball(itemInHand)
                && hasCapturedMob(itemInHand)) {

            EntityType entityType = getCapturedMobTypeFromLore(itemInHand);
            Location spawnLocation = player.getTargetBlock(null, 5).getLocation().add(0.5, 1, 0.5);

            if (entityType != null) {
                Entity mob = player.getWorld().spawnEntity(spawnLocation, entityType);

                player.getInventory().removeItem(itemInHand);
                player.sendMessage("§aDu hast den Mob freigelassen und der §cPokeball§a ist kaputt gegangen!");
            }
        }
    }


    private boolean isPokeball(
            final ItemStack item
    ) {
        if (item == null || item.getType() != Material.PLAYER_HEAD) {
            return false;
        }

        var meta = item.getItemMeta();

        if (meta == null || !meta.hasDisplayName() || !meta.hasCustomModelData()) {
            return false;
        }

        return meta.getDisplayName().equals("§cPokeball") && meta.getCustomModelData() == 8;
    }

    private boolean hasCapturedMob(
            final ItemStack pokeBall
    ) {
        var meta = pokeBall.getItemMeta();
        return meta.getPersistentDataContainer().has(
                Main.POKEBALL_KEY,
                PersistentDataType.STRING
        );
    }

    private EntityType getCapturedMobTypeFromLore(
            final ItemStack pokeBall
    ) {
        var meta = pokeBall.getItemMeta();
        var mobValue = meta.getPersistentDataContainer().get(
                Main.POKEBALL_KEY,
                PersistentDataType.STRING
        );
        return EntityType.valueOf(mobValue);
    }
}
