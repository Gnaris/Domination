package map_command.parent;

import classification.TeamList;
import map.Coliseum;
import main.MapConfig;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AdminCmdModel {

    protected MapConfig map_config;
    protected List<Coliseum> maps_list;
    protected Coliseum map;
    protected String name;
    protected Player sender;

    public AdminCmdModel(AdminCmdController controller)
    {
        this.map_config = controller.getMap_config();
        this.maps_list = controller.getMaps_list();
        this.map = controller.getMap();
        this.name = controller.getName();
        this.sender = controller.getSender();
    }
}
