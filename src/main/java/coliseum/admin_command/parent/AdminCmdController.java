package coliseum.admin_command.parent;

import coliseum.Coliseum;
import main.Main;
import main.MapConfig;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class AdminCmdController {

    protected Player sender;
    protected String map_name;
    protected String command_type;
    protected String value_name;

    protected MapConfig coliseum_config;
    protected Map<String, Coliseum> coliseums_list;

    public AdminCmdController(Player sender, String command_type, String map_name, String value_name, Main plugin)
    {
        this.sender = sender;
        this.command_type = command_type;
        this.map_name = map_name;
        this.value_name = value_name;

        this.coliseum_config = plugin.getColiseum_config();
        this.coliseums_list = plugin.getColiseum_list();

    }

    public abstract void controlCmd();

    protected abstract void executeCmd();
}
