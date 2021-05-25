package com.yiorno.staffchat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHub extends Command {
    public CommandHub(){
        super("sc","mofucraft.staff");
    }

    public void execute(CommandSender sender, String[] args) {

        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            if (!(p.hasPermission("mofucraft.staff"))) {
                TextComponent tc = new TextComponent((ChatColor.RED + "権限がありません"));
                p.sendMessage(tc);
                return;
            }

            if (args.length == 0) {
                Chat chat = new Chat();
                chat.toggleChat(p);

            } else {
                Chat chat = new Chat();
                chat.sendStaff(args[0], p);

            }

        }
    }
}
