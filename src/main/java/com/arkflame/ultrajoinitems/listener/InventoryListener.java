package com.arkflame.ultrajoinitems.listener;

import com.arkflame.ultrajoinitems.manager.PlayerHotbarManager;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {
    private final PlayerHotbarManager hotbarManager;

    public InventoryListener(PlayerHotbarManager hotbarManager) {
        this.hotbarManager = hotbarManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        InteractableHotbar hotbar = hotbarManager.getPlayerHotbar(player);
        if (hotbar != null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        InteractableHotbar hotbar = hotbarManager.getPlayerHotbar(player);
        if (hotbar != null) {
            event.setCancelled(true);
        }
    }
}
