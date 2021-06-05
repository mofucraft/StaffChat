package com.yiorno.staffchat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public final class StaffChat extends Plugin implements Listener {

    public static List<ProxiedPlayer> enableChat = new ArrayList<>();
    public static String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE +
            "S" + ChatColor.WHITE + "] ";
    StaffChat instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, this);
        getProxy().getPluginManager().registerCommand(this, new CommandHub());
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer sender = (ProxiedPlayer) e.getSender();

        if(!(enableChat.contains(sender))){
            return;
        }

        if((e.getMessage().startsWith("/")) || (e.getMessage().startsWith("!"))){
            return;
        }

        Chat chat = new Chat();
        chat.sendStaff(e.getMessage(), sender);
        e.setCancelled(true);
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e){
        if(enableChat.contains(e.getPlayer())){
            enableChat.remove(e.getPlayer());
        }
    }
}
