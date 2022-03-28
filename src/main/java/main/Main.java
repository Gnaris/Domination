package main;

import command.View_Command;
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
        Config.Init(this);

        // Initialization
        game_list = new HashMap<>();

        //COMMAND
        getCommand("dt").setExecutor(new View_Command());

        System.out.println("[DOMINATION] has been turn on");
    }

    @Override
    public void onDisable() {
        System.out.println("[DOMINATION] has been turn off");
    }

    public static Game getGame(String game_name)
    {
        return game_list.get(game_name);
    }
}
