/*
Copyright © 2025 https://github.com/Fabii08?tab=repositories  
All rights reserved.  
*/ 
package de.fabi.customToolsAndMore;


import de.fabi.customToolsAndMore.Commands.*;
import de.fabi.customToolsAndMore.Listener.*;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static final NamespacedKey POKEBALL_KEY = new NamespacedKey("pokeball", "entity");
    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.getCommand("rainbow").setExecutor(new RainbowCommand());
        this.getCommand("anvil").setExecutor(new anvil());
        this.getCommand("Items").setExecutor(new CustomItemsCommand());
        this.getCommand("Fly").setExecutor(new Fly());
        this.getCommand("grindstone").setExecutor(new grindstone());
        this.getCommand("Hat").setExecutor(new HatCommand());
        this.getCommand("loom").setExecutor(new loom());
        this.getCommand("smith").setExecutor(new smithingtable());
        this.getCommand("stonecutter").setExecutor(new stonecutter());
        this.getCommand("trash").setExecutor(new trash());
        this.getCommand("craft").setExecutor(new workbench());

        getServer().getPluginManager().registerEvents(new SimpleElevators(), this);
        getServer().getPluginManager().registerEvents(new RainbowArmorListener(this), this);
        getServer().getPluginManager().registerEvents(new CaptureListener(), this);
        getServer().getPluginManager().registerEvents(new ReleaseListener(), this);
        getServer().getPluginManager().registerEvents(new InfiniteWaterBucketListener(), this);
        getServer().getPluginManager().registerEvents(new ThreeByThreePickaxeListener(), this);
        getServer().getPluginManager().registerEvents(new ThreeByThreeShovelListener(), this);
        getServer().getPluginManager().registerEvents(new TreeFellerAxeListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
