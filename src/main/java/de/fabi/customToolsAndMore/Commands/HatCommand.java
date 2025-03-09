/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand == null || itemInHand.getType().isAir()) {
            player.sendMessage("You must be holding an item to use this command.");
            return true;
        }

        ItemStack helmet = player.getInventory().getHelmet();
        player.getInventory().setHelmet(itemInHand);
        player.getInventory().setItemInMainHand(helmet);
        player.sendMessage("You have placed the item on your head!");

        return true;
    }
}
