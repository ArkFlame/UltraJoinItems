package com.arkflame.utils;

import org.bukkit.entity.Player;

import com.arkflame.ultrajoinitems.UltraJoinItemsPlugin;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class PluginMessages {
    public static void sendPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(UltraJoinItemsPlugin.getInstance(), "BungeeCord", out.toByteArray());
    }
}
