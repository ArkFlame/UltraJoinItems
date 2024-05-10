package com.arkflame.ultrajoinitems.model;

import com.arkflame.modernlib.utils.ChatColors;
import com.arkflame.ultrajoinitems.UltraJoinItemsPlugin;
import com.arkflame.ultrajoinitems.model.InteractableHotbar;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractableHotbar {
    private UltraJoinItemsPlugin plugin;
    private String hotbarName;
    private Map<Integer, InteractableItem> items;

    public InteractableHotbar(UltraJoinItemsPlugin plugin, String hotbarName) {
        this.plugin = plugin;
        this.hotbarName = hotbarName;
        this.items = new HashMap<>();
    }

    public void addItem(int slot, InteractableItem item) {
        items.put(slot, item);
    }

    public void removeItem(int slot) {
        items.remove(slot);
    }

    public InteractableItem getItem(int slot) {
        return items.get(slot);
    }

    public void executeItemCommand(Player player, int slot) {
        InteractableItem item = getItem(slot);
        if (item != null) {
            item.executeCommands(player);
        }
    }

    public void loadItems(ConfigurationSection itemsSection) {
        if (itemsSection == null)
            return;

        for (String itemName : itemsSection.getKeys(false)) {
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(itemName);
            if (itemSection == null)
                continue;

            int slot = itemSection.getInt("slot");
            Material material = Material.valueOf(itemSection.getString("material"));
            short damage = (short) itemSection.getInt("damage");
            int amount = itemSection.getInt("amount");
            String name = ChatColors.color(itemSection.getString("name"));
            List<String> lore = ChatColors.color(itemSection.getStringList("lore"));
            List<String> commands = itemSection.getStringList("commands");

            InteractableItem item = new InteractableItem(slot, material, damage, amount, name, lore, commands);
            addItem(slot, item);
        }
    }

    public void giveHotbarItems(Player player) {
        for (InteractableItem item : items.values()) {
            player.getInventory().setItem(item.getSlot(), item.getItemStack());
        }
    }

    public void giveItem(Player player, int slot) {
        InteractableItem item = getItem(slot);
        if (item != null) {
            player.getInventory().setItem(slot, item.getItemStack());
        }
    }
}
