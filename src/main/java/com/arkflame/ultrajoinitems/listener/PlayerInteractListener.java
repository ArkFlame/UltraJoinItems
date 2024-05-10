package com.arkflame.ultrajoinitems.listener;

import com.arkflame.ultrajoinitems.manager.PlayerHotbarManager;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;
import com.arkflame.ultrajoinitems.model.InteractableItem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    private final PlayerHotbarManager hotbarManager;

    public PlayerInteractListener(PlayerHotbarManager hotbarManager) {
        this.hotbarManager = hotbarManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            InteractableHotbar hotbar = hotbarManager.getPlayerHotbar(player);
            if (hotbar != null) {
                int selectedSlot = player.getInventory().getHeldItemSlot();
                InteractableItem item = hotbar.getItem(selectedSlot);
                if (item != null) {
                    item.executeCommands(player);
                    event.setCancelled(true);
                }
            }
        }
    }
}
