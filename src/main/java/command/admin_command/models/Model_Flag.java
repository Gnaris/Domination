package command.admin_command.models;

import coliseum.Coliseum;
import coliseum.Flag;
import command.admin_command.parent.AdministratorCommandModel;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;

public class Model_Flag extends AdministratorCommandModel {

    protected String map_name;
    protected Location player_location;
    public Model_Flag(String map_name, String value, Location player_location, MapConfig map_config, Map<String, Coliseum> coliseum_list) {
        super(value, map_config, coliseum_list);
        this.map_name = map_name;
        this.player_location = player_location;
    }

    public void addFlag()
    {
        this.map_config.getConfig().set("map." + this.map_name + ".flag", this.value);
        this.map_config.getConfig().set("map." + this.map_name + ".flag." + this.value + ".x", this.player_location.getX());
        this.map_config.getConfig().set("map." + this.map_name + ".flag." + this.value + ".y", this.player_location.getY());
        this.map_config.getConfig().set("map." + this.map_name + ".flag." + this.value + ".z", this.player_location.getZ());
        this.coliseum_list.get(this.map_name).getFlag_list().put(this.value, new Flag(this.value, this.player_location));
    }

    public void deleteFlag()
    {
        this.map_config.getConfig().set("map." + this.map_name + ".flag." + this.value, null);
    }
}
