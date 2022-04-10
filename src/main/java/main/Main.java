package main;

import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.core.Flag;
import coliseum.core.Spawn;
import coliseum.admin_command.View_AdminCommand;
import command.View_Command;
import game.Game;
import gameplay.event.FlagsArea;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public final class Main extends JavaPlugin {

    private final List<Game> games_list = new ArrayList<>();
    private final List<Coliseum> maps_list = new ArrayList<>();
    private MapConfig coliseum_config;
    private TeamList team;
    @Override
    public void onEnable() {
        //CONFIG
        saveDefaultConfig();
        reloadConfig();

        // Load All Map
        this.coliseum_config = new MapConfig(this);

        Coliseum map;
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
                map = new Coliseum(coliseum_name, this.coliseum_config.getConfig().getString("map." + coliseum_name + ".world"));
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
                                    map.getSpawn_list().add(spawn);
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
                        map.getFlag_list().add(flag);
                    }
                }
                this.maps_list.add(map);
            }
        }

        //COMMAND
        getCommand("dt").setExecutor(new View_Command(this));
        getCommand("dta").setExecutor(new View_AdminCommand(this));

        //EVENT
        getServer().getPluginManager().registerEvents(new FlagsArea(this), this);

        System.out.println("[DOMINATION] has been turn on");
    }

    @Override
    public void onDisable() {
        System.out.println("[DOMINATION] has been turn off");
    }
}
