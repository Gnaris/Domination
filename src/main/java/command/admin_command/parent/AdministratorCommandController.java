package command.admin_command.parent;

import main.Main;
import main.MapConfig;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AdministratorCommandController {

    protected MapConfig map_config;
    protected String command_type;
    protected String map_name;
    protected String name_value;
    protected Player sender;
    protected Main plugin;

    public AdministratorCommandController(String map_name, String command_type, String name_value, Main plugin, Player sender)
    {
        this.map_name = map_name;
        this.command_type = command_type;
        this.map_config = new MapConfig(plugin);
        this.name_value = name_value;
        this.sender = sender;
        this.plugin = plugin;
    }

    public abstract void checkAndExecuteCommand();

    public abstract void executeCommand();
}
