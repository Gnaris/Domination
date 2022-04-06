package coliseum.admin_command.parent;

import coliseum.Coliseum;
import main.MapConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class AdminCmdModel {

    protected String map_name;
    protected String value_name;
    protected Player sender;
    protected MapConfig coliseum_config;
    protected Map<String, Coliseum> coliseums_list;

    public AdminCmdModel(String map_name, String value_name, Player sender, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list)
    {
        this.map_name = map_name;
        this.value_name = value_name;
        this.sender = sender;
        this.coliseum_config = coliseum_config;
        this.coliseums_list = coliseums_list;
    }
}
