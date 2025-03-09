/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Listener;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class ThreeByThreePickaxeListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            return;
        }

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (item == null || item.getType() != Material.NETHERITE_PICKAXE) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasCustomModelData() || meta.getCustomModelData() != 4) {
            return;
        }

        if (!meta.hasDisplayName() || !meta.getDisplayName().equals("§6§l3x3 Pickaxe")) {
            return;
        }
        if (item.getEnchantmentLevel(Enchantment.UNBREAKING) == 3) {
            item.setDurability((short) (item.getDurability() + 2));
        } else {
            item.setDurability((short) (item.getDurability() + 8));
        }


        Location blockLocation = event.getBlock().getLocation();
        Vector direction = event.getPlayer().getLocation().getDirection().normalize();


        String dominantAxis = getDominantAxis(direction);


        int[] xOffset = {0};
        int[] yOffset = {0};
        int[] zOffset = {0};

        switch (dominantAxis) {
            case "X":
                zOffset = new int[]{-1, 0, 1};
                yOffset = new int[]{-1, 0, 1};
                break;

            case "Y":
                xOffset = new int[]{-1, 0, 1};
                zOffset = new int[]{-1, 0, 1};
                break;

            case "Z":
                xOffset = new int[]{-1, 0, 1};
                yOffset = new int[]{-1, 0, 1};
                break;
        }

        for (int x : xOffset) {
            for (int y : yOffset) {
                for (int z : zOffset) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    Location loc = blockLocation.clone().add(x, y, z);
                    Material type = loc.getBlock().getType();

                    if (isValidBlock(type)) {
                        loc.getBlock().breakNaturally(item);
                    }
                }
            }
        }
    }


    private String getDominantAxis(Vector direction) {
        double x = Math.abs(direction.getX());
        double y = Math.abs(direction.getY());
        double z = Math.abs(direction.getZ());

        if (x > y && x > z) {
            return "X";
        } else if (y > x && y > z) {
            return "Y";
        } else {
            return "Z";
        }
    }


    private boolean isValidBlock(Material type) {
        switch (type) {
            case STONE, COBBLESTONE, COAL_ORE, IRON_ORE, GOLD_ORE,
                 DIAMOND_ORE, EMERALD_ORE, REDSTONE_ORE, LAPIS_ORE,
                 DEEPSLATE, DEEPSLATE_COAL_ORE, DEEPSLATE_IRON_ORE,
                 DEEPSLATE_GOLD_ORE, DEEPSLATE_DIAMOND_ORE,
                 DEEPSLATE_EMERALD_ORE, DEEPSLATE_REDSTONE_ORE,
                 DEEPSLATE_LAPIS_ORE, NETHERRACK, NETHER_QUARTZ_ORE,
                 NETHER_GOLD_ORE, ANCIENT_DEBRIS, BASALT, BLACKSTONE,
                 ANDESITE, DIORITE, GRANITE, DEEPSLATE_COPPER_ORE,
                 COPPER_ORE, TUFF, COBBLED_DEEPSLATE:
                return true;
            default:
                return false;
        }
    }
}
