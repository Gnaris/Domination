package map_command.models;

import coliseum.Coliseum;
import map_command.parent.AdminCmdController;
import map_command.parent.AdminCmdModel;

public class Model_Coliseum extends AdminCmdModel {


    public Model_Coliseum(AdminCmdController controller) {
        super(controller);
    }

    public void createColiseum()
    {
        this.map_config.getConfig().set("map." + this.map_name + ".name", this.map_name);
        this.map_config.getConfig().set("map." + this.map_name + ".world", this.sender.getWorld().getName());
        this.map_config.saveConfig();
        this.maps_list.add(new Coliseum(this.map_name, this.sender.getWorld().getName()));
    }

    public void deleteColiseum()
    {
        this.map_config.getConfig().set("map." + this.map_name, null);
        this.map_config.saveConfig();
        this.maps_list.remove(this.map);
    }
}
