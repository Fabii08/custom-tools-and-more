/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Commands;



import de.fabi.customToolsAndMore.Items.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomItemsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().addItem(TreeFellerAxeItem.createTreeFellerAxe());
            ItemStack shovel = ThreeByThreeShovelItem.createThreeByThreeShovelItem();
            player.getInventory().addItem(shovel);
            ItemStack pickaxe = ThreeByThreePickaxeItem.createThreeByThreePickaxeItem();
            player.getInventory().addItem(pickaxe);
            player.getInventory().addItem(Pokeballitem.createPokeball());
            ItemStack Waterbucket = InfiniteWaterBucketItem.createInfiniteWaterBucketItem();

            player.getInventory().addItem(Waterbucket);
            return true;
        }
        return false;
    }
}
