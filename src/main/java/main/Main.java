package main;

import classification.TeamList;
import gameplay.event.Respawn;
import map.Coliseum;
import map.core.flag.Flag;
import map.core.spawn.Spawn;
import command.View_Command;
import game.Game;
import gameplay.event.FlagsArea;
import lombok.Getter;
import map_command.View_MapCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public final class Main extends JavaPlugin {

    private final List<Game> games_list = new ArrayList<>();
    private final List<Coliseum> maps_list = new ArrayList<>();
    private MapConfig maps_config;
    private TeamList team;
    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Load Map
        this.maps_config = new MapConfig(this);
        if(this.maps_config.getConfig().getString("map") != null)
        {
            for(String map_name : this.maps_config.getConfig().getConfigurationSection("map").getKeys(false))
            {
                if(this.maps_config.getConfig().getString("map." + map_name) != null)
                {
                    String world = this.maps_config.getConfig().getString("map." + map_name + ".world");
                    Coliseum map = new Coliseum(map_name, world);
                    List<Double> coordinate;
                    Location location;
                    //Get All Spawn
                    if(this.maps_config.getConfig().getString("map." + map_name + ".spawns_teams") != null)
                    {
                        for(TeamList team_color : TeamList.values())
                        {
                            if(team_color.isSpawnable())
                            {
                                String team = team_color.toString().toLowerCase();
                                if(this.maps_config.getConfig().getString("map." + map_name + ".spawns_teams." + team) != null)
                                {
                                    for(String spawn_name : this.maps_config.getConfig().getConfigurationSection("map." + map_name + ".spawns_teams." + team).getKeys(false))
                                    {
                                        coordinate = this.maps_config.getConfig().getDoubleList("map." + map_name + ".spawns_teams." + team + "." + spawn_name);
                                        float yaw = Float.parseFloat(String.valueOf(coordinate.get(3)));
                                        float pitch = Float.parseFloat(String.valueOf(coordinate.get(4)));
                                        location = new Location(Bukkit.getWorld(world),
                                                coordinate.get(0),
                                                coordinate.get(1),
                                                coordinate.get(2),
                                                yaw,
                                                pitch);
                                        map.getSpawn_list().add(new Spawn(spawn_name, team_color, location));
                                    }
                                }
                            }
                        }
                        if(this.maps_config.getConfig().getString("map." + map_name + ".endspawn.location") != null)
                        {
                            coordinate = this.maps_config.getConfig().getDoubleList("map." + map_name + ".endspawn.location");
                            float yaw = Float.parseFloat(String.valueOf(coordinate.get(3)));
                            float pitch = Float.parseFloat(String.valueOf(coordinate.get(4)));
                            location = new Location(Bukkit.getWorld(world),
                                    coordinate.get(0),
                                    coordinate.get(1),
                                    coordinate.get(2),
                                    yaw,
                                    pitch);
                            map.setEnd_spawn(location);
                        }
                    }
                    //Get All Flag
                    if(this.maps_config.getConfig().getString("map." + map_name + ".flags") != null)
                    {
                        for(String flag_name : this.maps_config.getConfig().getConfigurationSection("map." + map_name + ".flags").getKeys(false))
                        {
                            coordinate = this.maps_config.getConfig().getDoubleList("map." + map_name + ".flags." + flag_name);
                            location = new Location(Bukkit.getWorld(world), coordinate.get(0), coordinate.get(1), coordinate.get(2));
                            map.getFlag_list().add(new Flag(flag_name, location));
                        }
                    }
                    this.maps_list.add(map);
                }
            }
        }

        //COMMAND
        getCommand("dt").setExecutor(new View_Command(this));
        getCommand("dta").setExecutor(new View_MapCommand(this));

        //EVENT
        getServer().getPluginManager().registerEvents(new FlagsArea(this), this);
        getServer().getPluginManager().registerEvents(new Respawn(this), this);

        System.out.println("[DOMINATION] has been turn on");
    }

    @Override
    public void onDisable() {
        System.out.println("[DOMINATION] has been turn off");
    }
}
