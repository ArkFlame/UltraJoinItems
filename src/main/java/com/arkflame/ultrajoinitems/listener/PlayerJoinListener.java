package com.arkflame.ultrajoinitems.listener;

import com.arkflame.ultrajoinitems.manager.HotbarManager;
import com.arkflame.ultrajoinitems.manager.PlayerHotbarManager;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final HotbarManager hotbarManager;
    private final PlayerHotbarManager playerHotbarManager;

    public PlayerJoinListener(HotbarManager hotbarManager, PlayerHotbarManager playerHotbarManager) {
        this.hotbarManager = hotbarManager;
        this.playerHotbarManager = playerHotbarManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        InteractableHotbar defaultHotbar = hotbarManager.getHotbar("default_hotbar");
        if (defaultHotbar != null) {
            playerHotbarManager.setPlayerHotbar(player, defaultHotbar);
        }
    }
}
