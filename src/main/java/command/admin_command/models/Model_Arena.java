package command.admin_command.models;

import coliseum.Coliseum;
import command.admin_command.parent.AdministratorCommandModel;
import main.MapConfig;

import java.util.List;
import java.util.Map;

public class Model_Arena extends AdministratorCommandModel {

    public Model_Arena(String map_name, MapConfig map_config, Map<String, Coliseum> coliseum_list) {
        super(map_name, map_config, coliseum_list);
    }

    public void createArena()
    {
        this.map_config.getConfig().set("map." + this.value + ".name", this.value);
        this.map_config.saveConfig();
        this.coliseum_list.put(this.value, new Coliseum(this.value));
    }

    public void deleteArena()
    {
        this.map_config.getConfig().set("map." + this.value, null);
        this.map_config.saveConfig();
        this.coliseum_list.remove(this.value);
    }
}
