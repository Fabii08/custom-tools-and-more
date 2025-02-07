package de.fabi.customToolsAndMore.Items;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TreeFellerAxeItem {
    public static ItemStack createTreeFellerAxe() {
        ItemStack treecapitator = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta meta = treecapitator.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§2§lHolzfäller Axt");
            meta.addEnchant(Enchantment.EFFICIENCY, 6, true);
            List<String> lore = new ArrayList<>();
            lore.add("§7Die Axt fällt ganze Bäume!");
            lore.add("§8Cooldown: §a2s");
            meta.setLore(lore);
            meta.setCustomModelData(2);

            treecapitator.setItemMeta(meta);
        }
        return treecapitator;
    }
}
