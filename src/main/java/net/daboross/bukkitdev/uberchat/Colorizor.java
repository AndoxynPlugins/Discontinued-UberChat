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
 * @author Dabo Ross <Dabo.Ross at daboross.net>
 */
public class Colorizor {

    private static int currentColorNum = 0;

    public static String getColorString(String input) {
        char[] list = ChatColor.stripColor(input).toCharArray();
        if (list.length == 0) {
            return "";
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != ' ') {
                resultBuilder.append(getNextColor());
            }
            resultBuilder.append(list[i]);
        }
        return resultBuilder.toString();
    }
    private static final String[] colorList = {ChatColor.AQUA.toString(), ChatColor.BLUE.toString(), ChatColor.DARK_BLUE.toString(), ChatColor.DARK_PURPLE.toString(), ChatColor.DARK_RED.toString(), ChatColor.RED.toString(), ChatColor.GOLD.toString(), ChatColor.GREEN.toString(), ChatColor.DARK_GREEN.toString()};

    private static String getNextColor() {
        currentColorNum = (currentColorNum + 1) % 8;
        return colorList[currentColorNum];
    }
}
