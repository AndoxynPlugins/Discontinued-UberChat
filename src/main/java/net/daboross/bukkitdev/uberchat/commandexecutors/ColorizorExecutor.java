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

import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class ColorizorExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Sorry, Players Only");
            return true;
        }
        Player p = (Player) sender;
        if (PlayerInfoTracker.getColormeEnabled(p.getName())) {
            PlayerInfoTracker.setColormeEnabled(p.getName(), false);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your chat messages will no longer be colorized.");
        } else {
            PlayerInfoTracker.setColormeEnabled(p.getName(), true);
            sender.sendMessage(UberChatStatics.COLOR.MAIN + "Your chat messages will now be colorized.");
        }
        return true;
    }
}
