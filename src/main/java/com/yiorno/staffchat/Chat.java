package com.yiorno.staffchat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Chat {

    public void toggleChat(ProxiedPlayer p){
        if(StaffChat.enableChat.contains(p)){

            StaffChat.enableChat.remove(p);
            TextComponent tc = new TextComponent(ChatColor.GRAY + "スタッフチャットを無効にしました");
            p.sendMessage(tc);

        }else{

            StaffChat.enableChat.add(p);
            TextComponent tc = new TextComponent(ChatColor.AQUA + "スタッフチャットを有効にしました");
            p.sendMessage(tc);

        }
    }

    public void sendStaff(String msg, ProxiedPlayer player){
        String playerStr = player.getName();
        TextComponent tc = new TextComponent(StaffChat.prefix+ player + ChatColor.LIGHT_PURPLE + " → " + ChatColor.WHITE + msg);
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sc"));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "クリックで切り替え！")));

        for(ProxiedPlayer p: ProxyServer.getInstance().getPlayers()){
            if(p.hasPermission("mofucraft.staff")){
                p.sendMessage(tc);
            }
        }
    }
}
