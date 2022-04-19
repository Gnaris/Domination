package map_command.models;

import map.Coliseum;
import map_command.parent.AdminCmdController;
import map_command.parent.AdminCmdModel;

public class Model_Map extends AdminCmdModel {

    private String map_name;

    public Model_Map(AdminCmdController controller, String map_name) {
        super(controller);
        this.map_name = map_name;
    }

    public void createColiseum()
    {
        //File Configuration
        this.map_config.getConfig().set("map." + this.map_name + ".name", this.map_name);
        this.map_config.getConfig().set("map." + this.map_name + ".world", this.sender.getWorld().getName());
        this.map_config.saveConfig();

        //Data Main Class
        this.maps_list.add(new Coliseum(this.map_name, this.sender.getWorld().getName()));
    }

    public void deleteColiseum()
    {
        //File Configuration
        this.map_config.getConfig().set("map." + this.map_name, null);
        this.map_config.saveConfig();

        //Data Main Class
        this.maps_list.remove(this.map);
    }
}
