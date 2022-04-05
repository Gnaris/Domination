package coliseum.admin_command.models;

import coliseum.Coliseum;
import coliseum.Flag;
import coliseum.admin_command.parent.AdminCmdModel;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;

public class Model_Flag extends AdminCmdModel {


    public Model_Flag(String map_name, String value_name, Location player_location, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list) {
        super(map_name, value_name, player_location, coliseum_config, coliseums_list);
    }

    public void addFlag()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name, null);
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".x", this.player_location.getX());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".y", this.player_location.getY());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".z", this.player_location.getZ());
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getFlag_list().put(this.value_name, new Flag(this.value_name, this.player_location));
    }

    public void deleteFlag()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name, null);
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getFlag_list().remove(this.value_name);
    }
}
