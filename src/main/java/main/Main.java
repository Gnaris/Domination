package main;

import command.Command;
import game.Game;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static Map<String, Game> game_list;

    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Initialization
        game_list = new HashMap<>();

        //COMMAND
        getCommand("dt").setExecutor(new Command());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
