package command.admin_command.factory;

import command.admin_command.controllers.Controller_Arena;
import command.admin_command.controllers.Controller_Flag;
import command.admin_command.controllers.Controller_Spawn;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class ControllerAdministorFactory {

    public static AdministratorCommandController getInstance(String[] args, Player sender, Main plugin)
    {
        String command_type = args[0];
        String map_name = args[2];
        String name_value;
        String team_color;

        if(command_type.equalsIgnoreCase("create") || command_type.equalsIgnoreCase("delete"))
        {
            return new Controller_Arena(map_name, command_type, null, plugin, sender);
        }

        command_type = args[1];
        map_name = args[0];
        name_value = args[2];
        if(command_type.equalsIgnoreCase("setflag") || command_type.equalsIgnoreCase("deleteflag"))
        {
            return new Controller_Flag(map_name, command_type, name_value, plugin, sender);
        }

        team_color = args[2];
        name_value = args[3];
        if(command_type.equalsIgnoreCase("setspawn") || command_type.equalsIgnoreCase("deletespawn"))
        {
            return new Controller_Spawn(map_name, command_type, name_value, team_color, plugin, sender);
        }
        return null;
    }
}
