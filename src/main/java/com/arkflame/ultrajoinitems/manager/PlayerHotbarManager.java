package com.arkflame.ultrajoinitems.manager;

import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerHotbarManager {
    private final Map<Player, InteractableHotbar> playerHotbars;

    public PlayerHotbarManager() {
        this.playerHotbars = new HashMap<>();
    }

    /**
     * Retrieves the hotbar associated with the specified player.
     *
     * @param player The player whose hotbar to retrieve.
     * @return The hotbar associated with the player.
     */
    public InteractableHotbar getPlayerHotbar(Player player) {
        return playerHotbars.get(player);
    }

    /**
     * Sets the hotbar for the specified player.
     *
     * @param player The player for whom to set the hotbar.
     * @param hotbar The hotbar to set for the player.
     */
    public void setPlayerHotbar(Player player, InteractableHotbar hotbar) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        if (hotbar != null) {
            hotbar.giveHotbarItems(player);
        }
        playerHotbars.put(player, hotbar);
    }

    /**
     * Removes the hotbar associated with the specified player.
     *
     * @param player The player whose hotbar to remove.
     */
    public void removePlayerHotbar(Player player) {
        playerHotbars.remove(player);
    }

    /**
     * Clears all player hotbars.
     */
    public void clearPlayerHotbars() {
        playerHotbars.clear();
    }
}
