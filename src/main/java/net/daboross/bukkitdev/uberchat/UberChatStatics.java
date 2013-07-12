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

import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class UberChatStatics {

    public static class FORMAT {

        /**
         * FORMAT for chat. First %s is the player's name, second %s is chat
         * message.
         */
        public static final String CHAT = ChatColor.BLACK + "#" + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
        /**
         * FORMAT for /msg. First %s is from player's name, second %s is to
         * player's name, third %s is message.s
         */
        public static final String MSG = ChatColor.DARK_BLUE + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_BLUE + "]" + ChatColor.WHITE + " %s";
        /**
         * FORMAT for /msg shown to spies. First %s is from player's name,
         * second %s is to player's name, third %s is message.
         */
        public static final String MSG_SPY = ChatColor.DARK_RED + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_RED + " -> " + ChatColor.BLUE + "%s" + ChatColor.DARK_RED + "]" + ChatColor.AQUA + " %s";
        /**
         * FORMAT for announcer name. First %s is the announcer's name.
         */
        public static final String ANNOUNCER = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "%s" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
        /**
         * FORMAT for /me. First %s is player's name, second %s is action.
         */
        public static final String ME = ChatColor.BLACK + " * " + ChatColor.BLUE + "%s" + ChatColor.GRAY + " %s";
        public static final String SHOUT = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "%s" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " %s";
    }

    public static class COLOR {

        /**
         * Main Color.
         */
        public static final String MAIN = ChatColor.AQUA.toString();
    }

    public static class STRINGS {

        public static final String MSG_YOU_REPRESENTATION = ChatColor.GOLD + "You";
        public static final String SERVER_NAME = ChatColor.GOLD + "Server";
    }

    public static class PERMISSION {

        public static final String MSG_SPY = "uberchat.msgspy";
    }
}
