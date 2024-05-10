package com.arkflame.ultrajoinitems.manager;

import com.arkflame.ultrajoinitems.UltraJoinItemsPlugin;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class HotbarManager {
    private UltraJoinItemsPlugin ultraJoinItemsPlugin;
    private final Map<String, InteractableHotbar> hotbars;

    public HotbarManager(UltraJoinItemsPlugin ultraJoinItemsPlugin) {
        this.ultraJoinItemsPlugin = ultraJoinItemsPlugin;
        this.hotbars = new HashMap<>();
    }

    public void loadHotbars(FileConfiguration config) {
        if (config == null) return;

        ConfigurationSection hotbarSection = config.getConfigurationSection("hotbars");
        if (hotbarSection == null) return;

        for (String hotbarName : hotbarSection.getKeys(false)) {
            InteractableHotbar hotbar = new InteractableHotbar(ultraJoinItemsPlugin, hotbarName);
            hotbar.loadItems(config.getConfigurationSection("hotbars." + hotbarName + ".items"));
            hotbars.put(hotbarName, hotbar);
        }
    }

    public InteractableHotbar getHotbar(String hotbarName) {
        return hotbars.get(hotbarName);
    }
}
