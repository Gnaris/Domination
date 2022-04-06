package coliseum.admin_command.models;

import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.Spawn;
import coliseum.admin_command.parent.AdminCmdModel;
import main.MapConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class Model_Spawn extends AdminCmdModel {

    private final TeamList team_color;

    public Model_Spawn(String map_name, String value_name, Player sender, TeamList team_color, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list) {
        super(map_name, value_name, sender, coliseum_config, coliseums_list);
        this.team_color = team_color;
    }

    public void setSpawn()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name, null);
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".x", this.sender.getLocation().getX());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".y", this.sender.getLocation().getY());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".z", this.sender.getLocation().getZ());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".yaw", this.sender.getLocation().getYaw());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name + ".pitch", this.sender.getLocation().getPitch());
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getSpawn_list().add(new Spawn(this.value_name, this.team_color, this.sender.getLocation()));
    }

    public void deleteSpawn()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".spawns." + this.team_color.toString().toLowerCase() + "." + this.value_name, null);
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getSpawn_list().stream()
                .filter(spawn_list -> spawn_list.getName().equalsIgnoreCase(this.value_name))
                .collect(Collectors.toList())
                .forEach(spawn_list -> this.coliseums_list.get(this.map_name).getSpawn_list().remove(spawn_list));
    }
}
