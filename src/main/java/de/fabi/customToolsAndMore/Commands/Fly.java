package de.fabi.customToolsAndMore.Commands;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Du bist kein Spieler");
            return true;

        }
        Player player = (Player) commandSender;

        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatColor.RED + "Du Kannst nicht mehr fligen");
        }else{
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ChatColor.GREEN + "Du Kannst Jezt Fliegen");

        }
        return true;
    }
}


