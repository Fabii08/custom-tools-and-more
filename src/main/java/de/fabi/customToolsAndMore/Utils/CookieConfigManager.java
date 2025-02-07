package de.fabi.customToolsAndMore.Utils;



/*
Copyright © 2024 https://github.com/Fabii08?tab=repositories
All rights reserved.

Unauthorized copying, modification, or distribution of this file,
via any medium, is strictly prohibited.

DO NOT DISTRIBUTE.
*/

import de.fabi.customToolsAndMore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CookieConfigManager {

    private final Main plugin;
    private File cookieConfigFile;
    private FileConfiguration cookieConfig;

    public CookieConfigManager(Main plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void saveDefaultConfig() {
        cookieConfigFile = new File(plugin.getDataFolder(), "cookies.yml");
        if (!cookieConfigFile.exists()) {
            cookieConfigFile.getParentFile().mkdirs();
            try {
                cookieConfigFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cookieConfig = YamlConfiguration.loadConfiguration(cookieConfigFile);
    }

    public FileConfiguration getConfig() {
        return cookieConfig;
    }

    public void saveConfig() {
        try {
            cookieConfig.save(cookieConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
