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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.ChatColor;

/**
 *
 * @author daboross
 */
public class SwearChecker {

    private static final String ANYWHERE_FORMAT = "(?i)%s";
    private static final String WORDONLY_FORMAT = "(?i)(^|[^a-zA-Z])%s([^a-zA-Z]|$)";
    private static final Pattern COLOR_PATTERN = Pattern.compile("(?i).*" + ChatColor.COLOR_CHAR + "[0-9A-FK-OR].*");
    private final Map<Pattern, String> swearRegex;

    public SwearChecker() {
        this.swearRegex = new HashMap<Pattern, String>();
    }

    public SwearChecker(Map<Pattern, String> swearRegex) {
        this.swearRegex = new HashMap<Pattern, String>(swearRegex);
    }

    public void addAnwhereSwearWord(String swear) {
        swearRegex.put(Pattern.compile(String.format(ANYWHERE_FORMAT, swear)), fillString(swear.length(), '*'));
    }

    public void addWordOnlySwearWord(String swear) {
        swearRegex.put(Pattern.compile(String.format(WORDONLY_FORMAT, swear)), fillString(swear.length(), '*'));
    }

    public String check(String input) {
        String result = input;
        boolean resultNonColor = COLOR_PATTERN.matcher(input).matches();
        for (Map.Entry<Pattern, String> swearEntry : swearRegex.entrySet()) {
            result = swearEntry.getKey().matcher(result).replaceAll(swearEntry.getValue());
            if (!resultNonColor) {
                String noColorMessage = ChatColor.stripColor(result);
                if (noColorMessage.equals(result)) {
                    resultNonColor = true;
                } else {
                    Matcher matcher = swearEntry.getKey().matcher(noColorMessage);
                    if (matcher.find()) {
                        result = matcher.replaceAll(swearEntry.getValue());
                        resultNonColor = true;
                    }
                }
            }
        }
        return result;
    }

    private static String fillString(int length, char filler) {
        char[] array = new char[length];
        Arrays.fill(array, filler);
        return String.valueOf(array);
    }
}
