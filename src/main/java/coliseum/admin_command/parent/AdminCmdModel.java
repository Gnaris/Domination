package coliseum.admin_command.parent;

import classification.team.TeamList;
import coliseum.Coliseum;
import main.MapConfig;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AdminCmdModel {

    protected MapConfig map_config;
    protected List<Coliseum> maps_list;
    protected Coliseum map;
    protected String map_name;
    protected String name;
    protected Player sender;
    protected TeamList color_team;

    public AdminCmdModel(AdminCmdController controller)
    {
        this.map_config = controller.getMap_config();
        this.maps_list = controller.getMaps_list();
        this.map = controller.getMap();
        this.map_name = controller.getMap_name();
        this.name = controller.getName();
        this.sender = controller.getSender();
        this.color_team = controller.getColor_team();
    }
}
