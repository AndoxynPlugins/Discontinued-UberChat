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
package net.daboross.bukkitdev.uberchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class UberChatUserFinder {

    public static List<Player> findUsers(String partialUser) {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers();
        List<Player> result = new ArrayList<Player>();
        for (Player p : onlinePlayers) {
            if (p.getName().equals(partialUser)) {
                result.clear();
                result.add(p);
                return result;
            } else if (p.getName().contains(partialUser)) {
                result.add(p);
            } else {
                String display = ChatColor.stripColor(p.getDisplayName());
                if (display.contains(partialUser)) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    public static Player findUserExact(String username) {
        return Bukkit.getServer().getPlayerExact(username);
    }

    public static List<CommandSender> findCommandSenders(String partialUser) {
        String search = partialUser.toLowerCase(Locale.ENGLISH);
        Player[] onlinePlayers = Bukkit.getOnlinePlayers();
        List<CommandSender> result = new ArrayList<CommandSender>();
        if ("console".contains(search) || "server".contains(search)) {
            result.add(Bukkit.getConsoleSender());
        }
        for (Player p : onlinePlayers) {
            String username = p.getName().toLowerCase(Locale.ENGLISH);
            if (username.equals(search)) {
                result = new ArrayList<CommandSender>();
                result.add(p);
                return result;
            } else if (username.contains(search)) {
                result.add(p);
            } else {
                String displayname = ChatColor.stripColor(p.getDisplayName()).toLowerCase(Locale.ENGLISH);
                if (displayname.contains(search)) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    public static CommandSender findCommandSenderExact(String name) {
        if (name.equalsIgnoreCase("console") || name.equalsIgnoreCase("server")) {
            return Bukkit.getConsoleSender();
        } else {
            return Bukkit.getServer().getPlayerExact(name);
        }
    }
}
