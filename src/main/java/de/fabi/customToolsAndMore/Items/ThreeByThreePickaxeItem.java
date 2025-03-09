/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Items;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ThreeByThreePickaxeItem {

    public static ItemStack createThreeByThreePickaxeItem() {

        ItemStack threeByThreePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = threeByThreePickaxe.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6§l3x3 Pickaxe");
            List<String> lore = new ArrayList<>();
            lore.add("§7Die Pickaxe baut 3x3 ab!");
            meta.setLore(lore);
            meta.setCustomModelData(4);

            threeByThreePickaxe.setItemMeta(meta);
        }
        return threeByThreePickaxe;
    }
}
