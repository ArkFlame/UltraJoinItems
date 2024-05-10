package com.arkflame.ultrajoinitems.listener;

import com.arkflame.ultrajoinitems.manager.PlayerHotbarManager;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
    private final PlayerHotbarManager hotbarManager;

    public PlayerDropItemListener(PlayerHotbarManager hotbarManager) {
        this.hotbarManager = hotbarManager;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        InteractableHotbar hotbar = hotbarManager.getPlayerHotbar(player);
        if (hotbar != null) {
            event.setCancelled(true);
        }
    }
}
