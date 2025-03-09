/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore.Listener;




import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class RainbowArmorListener implements Listener {

    private final de.fabi.customToolsAndMore.Main plugin;
    private float hue = 0.0f;

    public RainbowArmorListener(de.fabi.customToolsAndMore.Main plugin) {
        this.plugin = plugin;
        startColorChangeTask();
    }

    private void startColorChangeTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                hue += 0.01f;
                if (hue > 1.0f) {
                    hue = 0.0f;
                }


                java.awt.Color awtColor = java.awt.Color.getHSBColor(hue, 1.0f, 1.0f);
                Color nextColor = Color.fromRGB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());

                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack[] armor = player.getInventory().getArmorContents();

                    for (ItemStack item : armor) {
                        if (isRainbowArmor(item)) {
                            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                            if (meta != null) {
                                meta.setColor(nextColor);
                                item.setItemMeta(meta);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 5L);
    }

    private boolean isRainbowArmor(ItemStack item) {
        if (item == null || !(item.getItemMeta() instanceof LeatherArmorMeta)) {
            return false;
        }
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        return meta != null && meta.hasDisplayName() && meta.getDisplayName().contains("Regenbogen");
    }
}
