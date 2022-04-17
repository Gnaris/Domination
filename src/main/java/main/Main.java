package main;

import classification.team.TeamList;
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

        // Load All Map
        this.maps_config = new MapConfig(this);

        Coliseum map;
        Flag flag;
        Spawn spawn;
        double x,y,z;
        float yaw, pitch;
        Location location;
        String world;
        if(this.maps_config.getConfig().getString("map") != null)
        {
            //get every map name
            for(String map_name : this.maps_config.getConfig().getConfigurationSection("map").getKeys(false))
            {
                map = new Coliseum(map_name, this.maps_config.getConfig().getString("map." + map_name + ".world"));
                if(this.maps_config.getConfig().getString("map." + map_name + ".spawns") != null)
                {
                    //get every spawn team color
                    for(String team_color : this.maps_config.getConfig().getConfigurationSection("map." + map_name + ".spawns").getKeys(false)) {
                        {
                            if(this.maps_config.getConfig().getString("map." + map_name + ".spawns." + team_color) != null)
                            {
                                //get every spawn team color position
                                for(String spawn_name : this.maps_config.getConfig().getConfigurationSection("map." + map_name + ".spawns." + team_color).getKeys(false))
                                {
                                    x = this.maps_config.getConfig().getDouble("map." + map_name + ".spawns." + team_color + "." + spawn_name + ".x");
                                    y = this.maps_config.getConfig().getDouble("map." + map_name + ".spawns." + team_color + "." + spawn_name + ".y");
                                    z = this.maps_config.getConfig().getDouble("map." + map_name + ".spawns." + team_color + "." + spawn_name + ".z");
                                    yaw = (float) this.maps_config.getConfig().getDouble("map." + map_name + ".spawns." + team_color + "." + spawn_name + ".yaw");
                                    pitch = (float) this.maps_config.getConfig().getDouble("map." + map_name + ".spawns." + team_color + "." + spawn_name + ".pitch");
                                    world = this.maps_config.getConfig().getString("map." + map_name + ".world");
                                    location = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
                                    Arrays.stream(TeamList.values())
                                            .filter(teamlist -> team_color.equalsIgnoreCase(teamlist.toString().toLowerCase()))
                                            .collect(Collectors.toList())
                                            .forEach(teamlist -> this.team = teamlist);
                                    spawn = new Spawn(spawn_name, this.team, location);
                                    map.getSpawn_list().add(spawn);
                                }
                            }
                        }
                    }
                }
                if(this.maps_config.getConfig().getString("map." + map_name + ".flags") != null)
                {
                    //Get every flag position
                    for(String flag_name : this.maps_config.getConfig().getConfigurationSection("map." + map_name + ".flags").getKeys(false))
                    {
                        x = this.maps_config.getConfig().getDouble("map." + map_name + ".flags." + flag_name + ".x");
                        y = this.maps_config.getConfig().getDouble("map." + map_name + ".flags." + flag_name + ".y");
                        z = this.maps_config.getConfig().getDouble("map." + map_name + ".flags." + flag_name + ".z");
                        world = this.maps_config.getConfig().getString("map." + map_name + ".world");
                        location = new Location(Bukkit.getWorld(world), x,y,z);
                        flag = new Flag(flag_name, location);
                        map.getFlag_list().add(flag);
                    }
                }
                this.maps_list.add(map);
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
