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

import net.daboross.bukkitdev.uberchat.ChatChecker;
import net.daboross.bukkitdev.uberchat.PlayerInfoTracker;
import net.daboross.bukkitdev.uberchat.UberChatHelpers;
import net.daboross.bukkitdev.uberchat.MessageHandler;
import net.daboross.bukkitdev.uberchat.UberChatStatics;
import net.daboross.bukkitdev.uberchat.UberChatUserFinder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author daboross
 */
public class ReplyExecutor implements CommandExecutor {

	private MessageHandler messageHandler;

	public ReplyExecutor(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(UberChatStatics.COLOR.MAIN + "Please specify a message to send.");
			sender.sendMessage(UberChatStatics.COLOR.MAIN + "Usage: /" + label + " <message> (Sends <message> to the last person who messaged you.)");
		} else {
			String replyToName = PlayerInfoTracker.getReplyto(sender.getName());
			CommandSender replyTo = replyToName == null ? null : UberChatUserFinder.findCommandSenderExact(replyToName);
			if (replyTo == null) {
				sender.sendMessage(UberChatStatics.COLOR.MAIN + "No user found to reply to.");
			} else {
				String message = UberChatHelpers.arrayToString(args, " ");
				messageHandler.sendMessage(sender, replyTo, message);
			}
		}
		return true;
	}
}