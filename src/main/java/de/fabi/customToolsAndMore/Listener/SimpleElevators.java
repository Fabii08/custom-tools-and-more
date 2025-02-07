package de.fabi.customToolsAndMore.Listener;
/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/




import de.fabi.customToolsAndMore.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.DaylightDetector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public final class SimpleElevators implements Listener {

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Block block = location.getBlock();

        if (event.getFrom().getY() < event.getTo().getY() && isDaylightDetectorNormal(block)) {
            for (int i = 1; i + location.getY() <= 319; ++i) {
                Block relativeBlock = block.getRelative(BlockFace.UP, i);

                if (isDaylightDetectorNormal(relativeBlock)
                        && relativeBlock.getRelative(BlockFace.UP, 1).getType() == Material.AIR
                        && relativeBlock.getRelative(BlockFace.UP, 2).getType() == Material.AIR) {

                    Location target = player.getLocation();
                    target.setY(location.getBlockY() + i + 0.375);
                    smoothTeleport(player, target);
                    player.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Block block = location.getBlock();

        if (event.isSneaking() && isDaylightDetectorNormal(block)) {
            for (int i = 1; i <= location.getBlockY() + 500; ++i) {
                Block relativeBlock = block.getRelative(BlockFace.DOWN, i);

                if (isDaylightDetectorNormal(relativeBlock)
                        && relativeBlock.getRelative(BlockFace.UP, 1).getType() == Material.AIR
                        && relativeBlock.getRelative(BlockFace.UP, 2).getType() == Material.AIR) {

                    Location target = player.getLocation();
                    target.setY(location.getBlockY() - i + 0.375);
                    smoothTeleport(player, target);
                    player.playSound(target, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                    break;
                }
            }
        }
    }

    private boolean isDaylightDetectorNormal(Block block) {
        if (block.getType() != Material.DAYLIGHT_DETECTOR) {
            return false;
        }
        DaylightDetector detector = (DaylightDetector) block.getBlockData();
        return !detector.isInverted();
    }

    private void smoothTeleport(Player player, Location target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(target);
            }
        }.runTaskLater(Main.getInstance(), 1L);
    }
}
