/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Listener;




import de.fabi.customToolsAndMore.Items.InfiniteWaterBucketItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InfiniteWaterBucketListener implements Listener {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item != null && isInfiniteWaterBucket(item)) {
            // Wasser platzieren
            Block block = e.getBlockClicked().getRelative(e.getBlockFace());
            block.setType(Material.WATER);

            // Eimer ersetzen
            e.setCancelled(true);
            player.getInventory().setItemInMainHand(InfiniteWaterBucketItem.createInfiniteWaterBucketItem());
        }
    }

    private boolean isInfiniteWaterBucket(ItemStack item) {
        if (item.getType() != Material.WATER_BUCKET) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        Component displayName = miniMessage.deserialize("<b><gradient:#00aaff:#0055ff>Unendlicher Wassereimer</gradient></b>");
        return meta != null && meta.displayName() != null && meta.displayName().equals(displayName);
    }
}
