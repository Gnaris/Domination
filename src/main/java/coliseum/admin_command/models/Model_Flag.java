package coliseum.admin_command.models;

import coliseum.admin_command.parent.AdminCmdController;
import coliseum.admin_command.parent.AdminCmdModel;
import coliseum.core.Flag;

import java.util.stream.Collectors;

public class Model_Flag extends AdminCmdModel {


    public Model_Flag(AdminCmdController controller) {
        super(controller);
    }

    public void addFlag()
    {
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name, null);
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name + ".x", this.sender.getLocation().getX());
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name + ".y", this.sender.getLocation().getY());
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name + ".z", this.sender.getLocation().getZ());
        this.map_config.saveConfig();
        this.map.getFlag_list().add(new Flag(this.name, this.sender.getLocation()));
    }

    public void deleteFlag()
    {
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + name, null);
        this.map_config.saveConfig();
        this.map.getFlag_list().stream()
                .filter(flag_list -> flag_list.getName().equalsIgnoreCase(this.name))
                .collect(Collectors.toList())
                .forEach(flag_list -> this.map.getFlag_list().remove(flag_list));
    }
}
