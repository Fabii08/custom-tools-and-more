package de.fabi.customToolsAndMore.Items;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ThreeByThreeShovelItem {

    public static ItemStack createThreeByThreeShovelItem() {

        ItemStack threeByThreeShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta meta = threeByThreeShovel.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6§l3x3 Schaufel");
            List<String> lore = new ArrayList<>();
            lore.add("§7Die Schaufel baut 3x3 ab!");
            meta.setLore(lore);
            meta.setCustomModelData(5);

            threeByThreeShovel.setItemMeta(meta);
        }
        return threeByThreeShovel;
    }
}
