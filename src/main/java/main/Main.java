package main;

import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.Flag;
import coliseum.Spawn;
import coliseum.admin_command.View_AdminCommand;
import command.View_Command;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Main extends JavaPlugin {

    private Map<String, Game> game_list = new HashMap<>();
    private Map<String, Coliseum> coliseums_list = new HashMap<>();
    private MapConfig coliseum_config;
    private TeamList team;
    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Load All Map
        this.coliseum_config = new MapConfig(this);
        Flag flag;
        Spawn spawn;
        double x,y,z;
        float yaw, pitch;
        Location location;
        String world;
        if(this.coliseum_config.getConfig().getString("map") != null)
        {
            for(String coliseum_name : this.coliseum_config.getConfig().getConfigurationSection("map").getKeys(false))
            {
                this.coliseums_list.put(coliseum_name, new Coliseum(coliseum_name, this.coliseum_config.getConfig().getString("map." + coliseum_name + ".world")));
                if(this.coliseum_config.getConfig().getString("map." + coliseum_name + ".spawns") != null)
                {
                    for(String team_color : this.coliseum_config.getConfig().getConfigurationSection("map." + coliseum_name + ".spawns").getKeys(false)) {
                        {
                            if(this.coliseum_config.getConfig().getString("map." + coliseum_name + ".spawns." + team_color) != null)
                            {
                                for(String spawn_name : this.coliseum_config.getConfig().getConfigurationSection("map." + coliseum_name + ".spawns." + team_color).getKeys(false))
                                {
                                    x = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".spawns." + team_color + "." + spawn_name + ".x");
                                    y = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".spawns." + team_color + "." + spawn_name + ".y");
                                    z = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".spawns." + team_color + "." + spawn_name + ".z");
                                    yaw = (float) this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".spawns." + team_color + "." + spawn_name + ".yaw");
                                    pitch = (float) this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".spawns." + team_color + "." + spawn_name + ".pitch");
                                    world = this.coliseum_config.getConfig().getString("map." + coliseum_name + ".world");
                                    location = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);

                                    Arrays.stream(TeamList.values())
                                            .filter(teamlist -> team_color.equalsIgnoreCase(teamlist.toString().toLowerCase()))
                                            .collect(Collectors.toList())
                                            .forEach(teamlist -> this.team = teamlist);
                                    spawn = new Spawn(spawn_name, this.team, location);
                                    this.coliseums_list.get(coliseum_name).getSpawn_list().put(spawn_name, spawn);
                                }
                            }
                        }
                    }
                }
                if(this.coliseum_config.getConfig().getString("map." + coliseum_name + ".flags") != null)
                {
                    for(String flag_name : this.coliseum_config.getConfig().getConfigurationSection("map." + coliseum_name + ".flags").getKeys(false))
                    {
                        x = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".flags." + flag_name + ".x");
                        y = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".flags." + flag_name + ".y");
                        z = this.coliseum_config.getConfig().getDouble("map." + coliseum_name + ".flags." + flag_name + ".z");
                        world = this.coliseum_config.getConfig().getString("map." + coliseum_name + ".world");
                        location = new Location(Bukkit.getWorld(world), x,y,z);
                        flag = new Flag(flag_name, location);
                        this.coliseums_list.get(coliseum_name).getFlag_list().put(flag_name, flag);
                    }
                }
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
        return coliseums_list;
    }

    public MapConfig getColiseum_config() {
        return coliseum_config;
    }
}
