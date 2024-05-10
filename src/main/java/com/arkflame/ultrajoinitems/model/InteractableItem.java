package com.arkflame.ultrajoinitems.model;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.arkflame.utils.PluginMessages;

import java.util.List;

public class InteractableItem {
    private int slot;
    private Material material;
    private short damage;
    private int amount;
    private String name;
    private List<String> lore;
    private List<String> commands;

    public InteractableItem(int slot, Material material, short damage, int amount, String name, List<String> lore, List<String> commands) {
        this.slot = slot;
        this.material = material;
        this.damage = damage;
        this.amount = amount;
        this.name = name;
        this.lore = lore;
        this.commands = commands;
    }

    public void executeCommands(Player player) {
        for (String command : commands) {
            if (command.startsWith("server:")) {
                // Send to Bungee server
                String serverName = command.substring(7);
                PluginMessages.sendPlayerToServer(player, serverName);
            } else {
                // Execute Bukkit command
                player.chat("/" + command);
            }
        }
    }
    

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material, amount, damage);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public Material getMaterial() {
        return material;
    }

    public short getDamage() {
        return damage;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getCommands() {
        return commands;
    }
}
