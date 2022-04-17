package map_command.parent;

import classification.team.TeamList;
import map.Coliseum;
import lombok.Getter;
import main.Main;
import main.MapConfig;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public abstract class AdminCmdController {

    protected String map_name;
    protected String command_type;
    protected String name;
    protected Coliseum map;
    protected TeamList color_team;

    protected Player sender;
    protected String[] args;

    protected MapConfig map_config;
    protected List<Coliseum> maps_list;

    public AdminCmdController(String[] args, Player sender, Main plugin)
    {
        this.sender = sender;
        this.args = args;

        this.map_config = plugin.getMaps_config();
        this.maps_list = plugin.getMaps_list();
    }

    /**
     * Checks if all the conditions of the order are respected
     */
    public abstract void controlCmd();

    /**
     * Call the model to update the map if controlCmd method is true
     */
    protected abstract void executeCmd();
}
