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

import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Dabo Ross
 */
public class UberChatListener implements Listener {

    private final ChatChecker chatChecker;
    private final UberChatConfig config;

    public UberChatListener(ChatChecker chatChecker, UberChatConfig config) {
        this.chatChecker = chatChecker;
        this.config = config;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerChatEvent(AsyncPlayerChatEvent apce) {
        format(apce);
        if (!checkForBack(apce)) {
            apce.setMessage(chatChecker.check(apce.getMessage(), apce.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChatEventHigh(AsyncPlayerChatEvent apce) {
        UberChatHelpers.formatPlayerDisplayname(apce.getPlayer());
    }

    private void format(AsyncPlayerChatEvent evt) {
        evt.setFormat(config.getChatFormat());
    }

    private boolean checkForBack(AsyncPlayerChatEvent evt) {
        String msg = evt.getMessage().replaceAll("(?i)&[0-9A-FLMO]", "").trim().toLowerCase(Locale.ENGLISH);
        if (msg.equals("back") || msg.equals("im back") || msg.equals("i'm back")) {
            String fullDisplay = evt.getPlayer().getDisplayName();
            String[] nameSplit = fullDisplay.split(" ");
            String name = nameSplit[nameSplit.length - 1];
            Bukkit.getServer().broadcastMessage(String.format(UberChatStatics.FORMAT.ANNOUNCER, "UC") + ChatColor.BLUE + name + ChatColor.GRAY + " Is Back" + ChatColor.DARK_GRAY + "!");
            evt.setCancelled(true);
            return true;
        } else {
            return false;
        }
    }
}
