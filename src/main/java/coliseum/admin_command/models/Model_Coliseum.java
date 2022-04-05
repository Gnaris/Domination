package coliseum.admin_command.models;

import coliseum.Coliseum;
import coliseum.admin_command.parent.AdminCmdModel;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;
import java.util.Objects;

public class Model_Coliseum extends AdminCmdModel {


    public Model_Coliseum(String map_name, String value_name, Location player_location, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list) {
        super(map_name, value_name, player_location, coliseum_config, coliseums_list);
    }

    public void createColiseum()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".name", this.map_name);
        this.coliseum_config.getConfig().set("map." + this.map_name + ".world", Objects.requireNonNull(player_location.getWorld()).toString().toLowerCase());
        this.coliseum_config.saveConfig();
        this.coliseums_list.put(this.map_name, new Coliseum(this.map_name, player_location.getWorld().toString().toLowerCase()));
    }

    public void deleteColiseum()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name, null);
        this.coliseum_config.saveConfig();
        this.coliseums_list.remove(this.map_name);
    }
}
