package de.cookieleaks.head;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class Head extends JavaPlugin {

    private static Head instance;

    public static Head getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        CommandMap commandMap = Bukkit.getCommandMap();
        commandMap.register("head", new HeadCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
