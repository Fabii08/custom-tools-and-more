/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Items;


import de.fabi.customToolsAndMore.Utils.CustomHead;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Pokeballitem {

    public static ItemStack createPokeball() {




        ItemStack Pokeball = CustomHead.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZlNDc2NDA4NDM3NDRjZDU3OTY5NzlkMTE5NmZiOTM4MzE3ZWM0MmIwOWZjY2IyYzU0NWVlNGM5MjVhYzJiZCJ9fX0=");
        ItemMeta meta = Pokeball.getItemMeta();
        meta.setDisplayName("§cPokeball");
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "Fängt Mobs ein!"));
        meta.setMaxStackSize(1);
        meta.setCustomModelData(8);
        Pokeball.setItemMeta(meta);


        return Pokeball;
    }
}
