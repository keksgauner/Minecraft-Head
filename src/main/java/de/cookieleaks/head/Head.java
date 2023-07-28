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
        synchronized (this) {
            instance = this;
        }
        CommandMap commandMap = Bukkit.getCommandMap();
        commandMap.register("skull", new HeadCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
