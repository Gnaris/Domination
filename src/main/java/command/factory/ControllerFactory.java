package command.factory;

import command.controllers.classification.Controller_Classification;
//import command.controllers.game_config.Controller_GameConfigMap;
import command.controllers.game_config.Controller_GameConfigMap;
import command.controllers.game_config.Controller_GameConfigStatus;
import command.controllers.game_config.Controller_GameConfigValue;
import command.controllers.request.Controller_Request;
import command.controllers.request.RequestList;
import command.parent.CommandController;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

// /dt help
// /dt team/kit <value>
// /dt open/close <game_name>
// /dt <request> <game_name>
// /dt <game_name> map <map_name>
// /dt <game_name> <config key> <value>

public class ControllerFactory
{

    public static CommandController getInstance(String[] args, Player sender, Main plugin)
    {
        CommandController controller = null;
        String command_type = args[0];
        switch(args.length)
        {
            case 1 :
            {
                if(command_type.equalsIgnoreCase("help"))
                {
                    controller = new Controller_Request(args, sender, plugin);
                }
                break;
            }

            case 2 :
            {
                if(command_type.equalsIgnoreCase("team") || command_type.equalsIgnoreCase("kit"))
                {
                    controller = new Controller_Classification(args, sender, plugin);
                }

                if(command_type.equalsIgnoreCase("open") || command_type.equalsIgnoreCase("close"))
                {
                    controller = new Controller_GameConfigStatus(args, sender, plugin);
                }

                String finalCommand_type = command_type;
                if(Arrays.stream(RequestList.values()).anyMatch(request_list -> request_list.toString().toLowerCase().equalsIgnoreCase(finalCommand_type)))
                {
                    controller = new Controller_Request(args, sender, plugin);
                }
                break;
            }

            case 3 :
            {

                command_type = args[1];
                if(command_type.equalsIgnoreCase("map"))
                {
                    controller = new Controller_GameConfigMap(args, sender, plugin);
                }

                if(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("default_game_caracteristique.minimum")).getKeys(false).stream().anyMatch(command_type::equalsIgnoreCase))
                {
                    controller = new Controller_GameConfigValue(args, sender, plugin);
                }
                break;
            }
        }
        return controller;
    }
}
