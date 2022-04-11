package map_command.factory;

import main.Main;
import map_command.controllers.Controller_Coliseum;
import map_command.controllers.Controller_Flag;
import map_command.controllers.Controller_Spawn;
import map_command.parent.AdminCmdController;
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

        switch(args.length)
        {
            case 2 :
            {
                if(command_type.equalsIgnoreCase("create") || command_type.equalsIgnoreCase("delete"))
                {
                    return new Controller_Coliseum(args, sender, plugin);
                }
                break;
            }

            case 3 :
            {
                command_type = args[1];
                if(command_type.equalsIgnoreCase("setflag") || command_type.equalsIgnoreCase("deleteflag"))
                {
                    return new Controller_Flag(args, sender, plugin);
                }
                break;
            }

            case 4 :
            {
                command_type = args[1];
                if(command_type.equalsIgnoreCase("setspawn") || command_type.equalsIgnoreCase("deletespawn"))
                {
                    return new Controller_Spawn(args, sender, plugin);
                }
                break;
            }
        }
        return null;
    }
}