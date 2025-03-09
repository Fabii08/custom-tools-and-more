/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Listener;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TreeFellerAxeListener implements Listener {

    private final Set<Material> LOG_TYPES = new HashSet<>(Arrays.asList(
            Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG,
            Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG,
            Material.CHERRY_LOG
    ));

    private static final int COOLDOWN_TIME = 40; // 2 Sekunden (20 Ticks pro Sekunde)
    private static final int MAX_BLOCKS = 1000;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!isHoldingTreecapitator(item)) {
            return;
        }

        if (player.hasCooldown(item.getType())) {
            return;
        }

        if (player.isSneaking()) {
            return;
        }

        Block block = event.getBlock();
        if (LOG_TYPES.contains(block.getType())) {
            int blocksBroken = fellTree(block);
            reduceDurability(item, blocksBroken);
            player.setCooldown(item.getType(), COOLDOWN_TIME);
        }
    }

    private boolean isHoldingTreecapitator(ItemStack item) {
        if (item == null || item.getType() != Material.NETHERITE_AXE || !item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        return "§2§lHolzfäller Axt".equals(meta.getDisplayName());
    }

    private int fellTree(Block startBlock) {
        Set<Block> toBreak = new HashSet<>();
        collectLogs(startBlock, toBreak);

        for (Block block : toBreak) {
            block.breakNaturally();
        }

        return toBreak.size();
    }

    private void collectLogs(Block block, Set<Block> toBreak) {
        if (!LOG_TYPES.contains(block.getType()) || toBreak.contains(block) || toBreak.size() >= MAX_BLOCKS) {
            return;
        }

        toBreak.add(block);

        for (BlockFace face : BlockFace.values()) {
            Block relative = block.getRelative(face);
            collectLogs(relative, toBreak);
        }

        for (BlockFace face1 : new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST}) {
            for (BlockFace face2 : new BlockFace[]{BlockFace.UP, BlockFace.DOWN}) {
                Block diagonal = block.getRelative(face1).getRelative(face2);
                collectLogs(diagonal, toBreak);
            }
        }
    }

    private void reduceDurability(ItemStack item, int blocksBroken) {
        int unbreakingLevel = item.getEnchantmentLevel(Enchantment.UNBREAKING);
        int durabilityLoss = blocksBroken;

        if (unbreakingLevel > 0) {
            durabilityLoss /= (unbreakingLevel + 1);
        }

        short newDurability = (short) (item.getDurability() + durabilityLoss);
        item.setDurability(newDurability);
    }
}
