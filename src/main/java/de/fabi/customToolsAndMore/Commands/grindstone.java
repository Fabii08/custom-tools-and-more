package de.fabi.customToolsAndMore.Commands;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class grindstone implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.openGrindstone(null, true);
            return true;
        }

        return false;

    }
}
