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

import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.ChatChecker;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class MeExecutor implements CommandExecutor {

    private ChatChecker chatChecker;

    public MeExecutor(ChatChecker chatChecker) {
        this.chatChecker = chatChecker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify an action to describe.");
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <action> (publicly describes you doing <action>)");
        } else {
            Bukkit.broadcastMessage(String.format(UberChatStatics.FORMAT.ME,
                    sender instanceof Player ? ((Player) sender).getDisplayName() : "Server",
                    chatChecker.check(UberChatHelpers.arrayToString(args, " "), sender)));
        }
        return true;
    }
}
