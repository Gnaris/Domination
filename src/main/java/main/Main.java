package main;

import command.player_command.View_Command;
import game.Game;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    private Map<String, Game> game_list;
    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Initialization
        game_list = new HashMap<>();

        //COMMAND
        getCommand("dt").setExecutor(new View_Command(this));

        System.out.println("[DOMINATION] has been turn on");
    }

    @Override
    public void onDisable() {
        System.out.println("[DOMINATION] has been turn off");
    }

    public Map<String, Game> getGame_list() {
        return game_list;
    }
}
