package coliseum.admin_command.models;

import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.Spawn;
import coliseum.admin_command.parent.AdminCmdModel;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;

public class Model_Spawn extends AdminCmdModel {

    private final TeamList team_color;

    public Model_Spawn(String map_name, String value_name, Location player_location, TeamList team_color, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list) {
        super(map_name, value_name, player_location, coliseum_config, coliseums_list);
        this.team_color = team_color;
    }

    public void setSpawn()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name, null);
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".x", this.player_location.getX());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".y", this.player_location.getY());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".z", this.player_location.getZ());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".yaw", this.player_location.getYaw());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".pitch", this.player_location.getPitch());
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getSpawn_list().put(this.value_name, new Spawn(this.value_name, this.team_color, this.player_location));
    }

    public void deleteSpawn()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name, null);
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getSpawn_list().remove(this.value_name);
    }
}
