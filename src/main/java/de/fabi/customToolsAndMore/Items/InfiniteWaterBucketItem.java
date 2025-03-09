/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Items;




import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class InfiniteWaterBucketItem {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static ItemStack createInfiniteWaterBucketItem() {

        ItemStack InfiniteWaterBucketItem = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = InfiniteWaterBucketItem.getItemMeta();

        if (meta != null) {
            Component displayName = miniMessage.deserialize("<b><gradient:#00aaff:#0055ff>Unendlicher Wassereimer</gradient></b>");
            meta.displayName(displayName);
            meta.setEnchantmentGlintOverride(true);

            InfiniteWaterBucketItem.setItemMeta(meta);
        }
        return InfiniteWaterBucketItem;
    }
}
