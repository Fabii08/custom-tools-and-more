/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Commands;



import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class RainbowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Befehl kann nur von Spielern verwendet werden.");
            return true;
        }

        Player player = (Player) sender;

        ItemStack[] armor = new ItemStack[4];
        armor[0] = createColoredArmor(Material.LEATHER_HELMET, ChatColor.RED + "Regenbogenhelm", Color.RED);
        armor[1] = createColoredArmor(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Regenbogenbrustplatte", Color.GREEN);
        armor[2] = createColoredArmor(Material.LEATHER_LEGGINGS, ChatColor.BLUE + "Regenbogenhose", Color.BLUE);
        armor[3] = createColoredArmor(Material.LEATHER_BOOTS, ChatColor.YELLOW + "Regenbogenschuhe", Color.YELLOW);

        player.getInventory().addItem(armor);
        player.sendMessage(ChatColor.GOLD + "Regenbogenrüstung wurde hinzugefügt!");

        return true;
    }

    private ItemStack createColoredArmor(Material material, String name, Color color) {
        ItemStack item = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setColor(color);
            item.setItemMeta(meta);
        }
        return item;
    }

}
