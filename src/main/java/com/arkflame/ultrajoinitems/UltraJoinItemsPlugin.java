package com.arkflame.ultrajoinitems;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.arkflame.ultrajoinitems.listener.InventoryListener;
import com.arkflame.ultrajoinitems.listener.PlayerDropItemListener;
import com.arkflame.ultrajoinitems.listener.PlayerInteractListener;
import com.arkflame.ultrajoinitems.listener.PlayerJoinListener;
import com.arkflame.ultrajoinitems.manager.HotbarManager;
import com.arkflame.ultrajoinitems.manager.PlayerHotbarManager;

public class UltraJoinItemsPlugin extends JavaPlugin {
    private static UltraJoinItemsPlugin instance;

    public static UltraJoinItemsPlugin getInstance() {
        return instance;
    }

    public static void setInstance(UltraJoinItemsPlugin instance) {
        UltraJoinItemsPlugin.instance = instance;
    }
    
    private PlayerHotbarManager playerHotbarManager;
    private HotbarManager hotbarManager;

    @Override
    public void onEnable() {
        setInstance(this);

        // Save default config
        saveDefaultConfig();

        // Register BungeeCord channel
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // Initialize the hotbar manager
        this.playerHotbarManager = new PlayerHotbarManager();
        this.hotbarManager = new HotbarManager(this);
        this.hotbarManager.loadHotbars(getConfig());

        registerListeners(
            new InventoryListener(playerHotbarManager), 
            new PlayerDropItemListener(playerHotbarManager), 
            new PlayerJoinListener(hotbarManager, playerHotbarManager), 
            new PlayerInteractListener(playerHotbarManager)
            );
    }

    public PlayerHotbarManager getPlayerHotbarManager() {
        return this.playerHotbarManager;
    }

    public HotbarManager getHotbarManager() {
        return this.hotbarManager;
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}

