package coliseum.admin_command.parent;

import coliseum.Coliseum;
import main.MapConfig;
import org.bukkit.Location;

import java.util.Map;

public abstract class AdminCmdModel {

    protected String map_name;
    protected String value_name;
    protected Location player_location;
    protected MapConfig coliseum_config;
    protected Map<String, Coliseum> coliseums_list;

    public AdminCmdModel(String map_name, String value_name, Location player_location, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list)
    {
        this.map_name = map_name;
        this.value_name = value_name;
        this.player_location = player_location;
        this.coliseum_config = coliseum_config;
        this.coliseums_list = coliseums_list;
    }
}
