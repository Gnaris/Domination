package coliseum.admin_command.models;

import coliseum.admin_command.parent.AdminCmdController;
import coliseum.admin_command.parent.AdminCmdModel;
import coliseum.core.Spawn;

import java.util.stream.Collectors;

public class Model_Spawn extends AdminCmdModel {

    public Model_Spawn(AdminCmdController controller) {
        super(controller);
    }

    public void setSpawn()
    {
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name, null);
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name + ".x", this.sender.getLocation().getX());
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name + ".y", this.sender.getLocation().getY());
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name + ".z", this.sender.getLocation().getZ());
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name + ".yaw", this.sender.getLocation().getYaw());
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name + ".pitch", this.sender.getLocation().getPitch());
        this.map_config.saveConfig();
        this.map.getSpawn_list().add(new Spawn(this.name, this.color_team, this.sender.getLocation()));
    }

    public void deleteSpawn()
    {
        this.map_config.getConfig().set("map." + this.map.getName() + ".spawns." + this.color_team.toString().toLowerCase() + "." + this.name, null);
        this.map_config.saveConfig();
        this.map.getSpawn_list().stream()
                .filter(spawn_list -> spawn_list.getName().equalsIgnoreCase(this.name))
                .collect(Collectors.toList())
                .forEach(spawn_list -> this.map.getSpawn_list().remove(spawn_list));
    }
}
