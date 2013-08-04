/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.uberchat.commandexecutors;

import java.util.List;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.MessageHandler;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import net.daboross.bukkitdev.uberchat.UberChatUserFinder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class MsgExecutor implements CommandExecutor {

    private MessageHandler messageHandler;

    public MsgExecutor(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a user and a message.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <user> <message> (sends a private <message> to <user>)");
        } else if (args.length == 1) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <user> <message> (sends a private <message> to <user>)");
        } else {
            List<CommandSender> foundUsers = UberChatUserFinder.findCommandSenders(args[0]);
            if (foundUsers.isEmpty()) {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "User \"" + ChatColor.RED + args[0] + UberChatStatics.COLOR.MAIN + "\" not found or not online.");
            } else if (foundUsers.size() == 1) {
                String message = UberChatHelpers.arrayToString(1, args, " ");
                messageHandler.sendMessage(sender, foundUsers.get(0), message);
            } else {
                sender.sendMessage(UberChatStatics.COLOR.MAIN + "Multiple users matching \"" + args[0] + "\":");
                sender.sendMessage(getNameString(foundUsers));
            }
        }
        return true;
    }

    private static String getNameString(List<CommandSender> list) {
        StringBuilder resultBuilder = new StringBuilder(ChatColor.BLUE.toString()).append(list.get(0).getName());
        for (int i = 1; i < list.size(); i++) {
            resultBuilder.append(ChatColor.BLACK.toString()).append(", ").append(ChatColor.BLUE.toString()).append(list.get(i).getName());
        }
        return resultBuilder.toString();
    }
}