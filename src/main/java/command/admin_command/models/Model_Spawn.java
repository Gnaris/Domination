package command.admin_command.models;

import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.Spawn;
import command.admin_command.parent.AdministratorCommandModel;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;

public class Model_Spawn extends AdministratorCommandModel {

    private String map_name;
    private TeamList team_color;
    private Location spawn_location;

    public Model_Spawn(String map_name, String value, TeamList team_color, Location spawn_location, MapConfig map_config, Map<String, Coliseum> coliseum_list) {
        super(value, map_config, coliseum_list);
        this.team_color = team_color;
        this.spawn_location = spawn_location;
        this.map_name = map_name;
    }

    public void setSpawn()
    {
        this.map_config.getConfig().set("map." + this.map_name + ".spawn." + this.team_color.toString().toLowerCase() + ".x", this.spawn_location.getX());
        this.map_config.getConfig().set("map." + this.map_name + ".spawn." + this.team_color.toString().toLowerCase() + ".y", this.spawn_location.getY());
        this.map_config.getConfig().set("map." + this.map_name + ".spawn." + this.team_color.toString().toLowerCase() + ".z", this.spawn_location.getZ());
        this.map_config.getConfig().set("map." + this.map_name + ".spawn." + this.team_color.toString().toLowerCase() + ".yaw", this.spawn_location.getYaw());
        this.map_config.getConfig().set("map." + this.map_name + ".spawn." + this.team_color.toString().toLowerCase() + ".pitch", this.spawn_location.getPitch());
        this.coliseum_list.get(this.map_name).getSpawn_list().put(this.team_color, new Spawn(this.value, this.team_color, this.spawn_location));
    }

    public void deleteSpawn()
    {

    }
}
