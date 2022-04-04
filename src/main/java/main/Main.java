package main;

import coliseum.Coliseum;
import command.admin_command.View_AdminCommand;
import command.player_command.View_Command;
import game.Game;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private Map<String, Game> game_list = new HashMap<>();
    private Map<String, Coliseum> coliseum_list = new HashMap<>();
    private MapConfig map_config;
    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Initialization
        map_config = new MapConfig(this);
        if(this.map_config.getConfig().getString("map") != null)
        {
            for(String coliseum : this.map_config.getConfig().getConfigurationSection("map").getKeys(false))
            {
                this.coliseum_list.put(coliseum, new Coliseum(coliseum));
            }
        }

        //COMMAND
        getCommand("dt").setExecutor(new View_Command(this));
        getCommand("dta").setExecutor(new View_AdminCommand(this));

        System.out.println("[DOMINATION] has been turn on");
    }

    @Override
    public void onDisable() {
        System.out.println("[DOMINATION] has been turn off");
    }

    public Map<String, Game> getGame_list() {
        return game_list;
    }

    public Map<String, Coliseum> getColiseum_list() {
        return coliseum_list;
    }
}
