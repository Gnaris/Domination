package coliseum.admin_command.models;

import coliseum.Coliseum;
import coliseum.Flag;
import coliseum.admin_command.parent.AdminCmdModel;
import main.MapConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class Model_Flag extends AdminCmdModel {


    public Model_Flag(String map_name, String value_name, Player sender, MapConfig coliseum_config, Map<String, Coliseum> coliseums_list) {
        super(map_name, value_name, sender, coliseum_config, coliseums_list);
    }

    public void addFlag()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name, null);
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".x", this.sender.getLocation().getX());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".y", this.sender.getLocation().getY());
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name + ".z", this.sender.getLocation().getZ());
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getFlag_list().add(new Flag(this.value_name, this.sender.getLocation()));
    }

    public void deleteFlag()
    {
        this.coliseum_config.getConfig().set("map." + this.map_name + ".flags." + this.value_name, null);
        this.coliseum_config.saveConfig();
        this.coliseums_list.get(this.map_name).getFlag_list().stream()
                .filter(flag_list -> flag_list.getName().equalsIgnoreCase(this.value_name))
                .collect(Collectors.toList())
                .forEach(flag_list -> this.coliseums_list.get(this.map_name).getFlag_list().remove(flag_list));
    }
}
