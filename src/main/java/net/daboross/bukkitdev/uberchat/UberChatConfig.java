/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bukkitdev.uberchat;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author daboross
 */
public class UberChatConfig {

    private final UberChatPlugin plugin;
    private String chatFormat;
    private List<String> swearWords;
    private List<String> wordOnlySwearWords;

    public UberChatConfig(UberChatPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        FileConfiguration config = plugin.getConfig();
        chatFormat = ChatColor.translateAlternateColorCodes('&', config.getString("chat-format"));
        swearWords = config.getStringList("swear-words");
        wordOnlySwearWords = config.getStringList("word-only-swear-words");
        plugin.getLogger().log(Level.INFO, "Chat format is ''{0}'', swear words are {1}, word-only swear words are {2}", new Object[]{chatFormat, swearWords, wordOnlySwearWords});
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public List<String> getSwearWords() {
        return Collections.unmodifiableList(swearWords);
    }

    public List<String> getWordOnlySwearWords() {
        return Collections.unmodifiableList(wordOnlySwearWords);
    }
}
