package de.cookieleaks.skull;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skull extends JavaPlugin {

    private static Skull instance;

    public static Skull getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        synchronized (this) {
            instance = this;
        }
        CommandMap commandMap = Bukkit.getCommandMap();
        commandMap.register("skull", new SkullCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
