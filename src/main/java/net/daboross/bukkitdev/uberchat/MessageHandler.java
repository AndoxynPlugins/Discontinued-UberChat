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

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author daboross
 */
public class MessageHandler {

	private final ChatChecker chatChecker;

	public MessageHandler(ChatChecker chatChecker) {
		this.chatChecker = chatChecker;
	}

	public void sendMessage(CommandSender sender, CommandSender receiver, String message) {
		if (sender instanceof Player) {
			UberChatHelpers.formatPlayerDisplayname((Player) sender);
		}
		if (receiver instanceof Player) {
			UberChatHelpers.formatPlayerDisplayname((Player) receiver);
		}
		String sensoredMessage = chatChecker.check(message, sender);
		String senderName = sender instanceof Player ? ((Player) sender).getDisplayName() : (sender instanceof ConsoleCommandSender ? UberChatStatics.STRINGS.SERVER_NAME : sender.getName());
		String receiverName = receiver instanceof Player ? ((Player) receiver).getDisplayName() : (receiver instanceof ConsoleCommandSender ? UberChatStatics.STRINGS.SERVER_NAME : receiver.getName());
		String messageForSender = String.format(UberChatStatics.FORMAT.MSG, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, receiverName, sensoredMessage);
		String messageForReceiver = String.format(UberChatStatics.FORMAT.MSG, senderName, UberChatStatics.STRINGS.MSG_YOU_REPRESENTATION, sensoredMessage);
		String messageForSpy = String.format(UberChatStatics.FORMAT.MSG_SPY, senderName, receiverName, sensoredMessage);
		sender.sendMessage(messageForSender);
		receiver.sendMessage(messageForReceiver);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission(UberChatStatics.PERMISSION.MSG_SPY) && p != sender && p != receiver) {
				p.sendMessage(messageForSpy);
			}
		}
		CommandSender console = Bukkit.getConsoleSender();
		if (console != sender && console != receiver) {
			console.sendMessage(messageForSpy);
		}
		PlayerInfoTracker.setReplyto(receiver.getName(), sender.getName());
		PlayerInfoTracker.setReplyto(sender.getName(), receiver.getName());
	}
}
