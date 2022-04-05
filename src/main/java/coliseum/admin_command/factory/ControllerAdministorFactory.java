package coliseum.admin_command.factory;

import coliseum.admin_command.controllers.Controller_Coliseum;
import coliseum.admin_command.controllers.Controller_Flag;
import coliseum.admin_command.controllers.Controller_Spawn;
import coliseum.admin_command.parent.AdminCmdController;
import main.Main;
import org.bukkit.entity.Player;

// /dta create <name>
// /dta delete <name>

// /dta <map> setflag <name>
// /dta <map> deleteflag <name>

// /dta <map> setspawn <team color> <name>
// /dta <map> deletespawn <team color> <name>

// /dta show map
// /dta showflag <name>
// /dta showspawn <name>

public class ControllerAdministorFactory {

    public static AdminCmdController getInstance(Player sender, String[] args, Main plugin)
    {
        String command_type = args[0];
        String map_name = args[1];
        String value_name;
        String team_color;

        switch(args.length)
        {
            case 2 :
            {
                if(command_type.equalsIgnoreCase("create") || command_type.equalsIgnoreCase("delete"))
                {
                    return new Controller_Coliseum(sender, command_type, map_name, null, plugin);
                }
                break;
            }

            case 3 :
            {
                command_type = args[1];
                map_name = args[0];
                value_name = args[2];
                if(command_type.equalsIgnoreCase("setflag") || command_type.equalsIgnoreCase("deleteflag"))
                {
                    return new Controller_Flag(sender, command_type, map_name, value_name, plugin);
                }
                break;
            }

            case 4 :
            {
                command_type = args[1];
                map_name = args[0];
                team_color = args[2];
                value_name = args[3];
                if(command_type.equalsIgnoreCase("setspawn") || command_type.equalsIgnoreCase("deletespawn"))
                {
                    return new Controller_Spawn(sender, command_type, map_name, team_color, value_name, plugin);
                }
                break;
            }
        }
        return null;
    }
}