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

import java.io.IOException;
import java.util.logging.Level;
import net.daboross.bukkitdev.uberchat.commandexecutors.ColorizorExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.MeExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.MsgExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.ReplyExecutor;
import net.daboross.bukkitdev.uberchat.commandexecutors.ShoutExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

/**
 * UberChatPlugin Plugin Made By DaboRoss
 *
 * @author daboross
 */
public final class UberChatPlugin extends JavaPlugin {

    private ChatChecker chatChecker;
    private MessageHandler messageHandler;

    @Override
    public void onEnable() {
        chatChecker = new ChatChecker();
        messageHandler = new MessageHandler(chatChecker);
        registerEvents();
        assignCommands();
        try {
            new MetricsLite(this).start();
        } catch (IOException ex) {
            getLogger().log(Level.WARNING, "Unable to create metrics");
        }
        getLogger().info("UberChat Fully Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("UberChat Fully Disabled");
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        UberChatListener uberChatListener = new UberChatListener(chatChecker);
        pm.registerEvents(uberChatListener, this);
    }

    private void assignCommands() {
        PluginCommand colorize = getCommand("colorize");
        if (colorize != null) {
            colorize.setExecutor(new ColorizorExecutor());
        }
        PluginCommand me = getCommand("me");
        if (me != null) {
            me.setExecutor(new MeExecutor(chatChecker));
        }
        PluginCommand msg = getCommand("msg");
        if (msg != null) {
            msg.setExecutor(new MsgExecutor(messageHandler));
        }
        PluginCommand reply = getCommand("reply");
        if (reply != null) {
            reply.setExecutor(new ReplyExecutor(messageHandler));
        }
        PluginCommand shout = getCommand("shout");
        if (shout != null) {
            shout.setExecutor(new ShoutExecutor(chatChecker));
        }
    }
}
