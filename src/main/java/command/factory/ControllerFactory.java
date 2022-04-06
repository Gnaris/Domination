package command.factory;

import command.controllers.classification.Controller_Classification;
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
        String command_type = args[0];
        String value_team_kit;
        String game_name;
        String coliseum_name;
        int value;

        switch(args.length)
        {
            case 1 :
            {
                if(command_type.equalsIgnoreCase("help"))
                {
                    return new Controller_Request(command_type, sender, plugin);
                }
                break;
            }

            case 2 :
            {
                game_name = args[1];
                value_team_kit = args[1];
                String finalCommand_type = command_type;
                if(command_type.equalsIgnoreCase("team") || command_type.equalsIgnoreCase("kit"))
                {
                    return new Controller_Classification(command_type, value_team_kit, sender, plugin);
                }

                if(command_type.equalsIgnoreCase("open") || command_type.equalsIgnoreCase("close"))
                {
                    return new Controller_GameConfigStatus(command_type, game_name, sender, plugin);
                }

                if(Arrays.stream(RequestList.values()).anyMatch(request_list -> request_list.toString().toLowerCase().equalsIgnoreCase(finalCommand_type)))
                {
                    return new Controller_Request(command_type, game_name, sender, plugin);
                }
                break;
            }

            case 3 :
            {
                game_name = args[0];
                command_type = args[1];
                coliseum_name = args[2];

                if(command_type.equalsIgnoreCase("map"))
                {
                    return new Controller_GameConfigMap(game_name, command_type, coliseum_name, sender, plugin);
                }

                if(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("default_game_caracteristique.minimum")).getKeys(false).stream().anyMatch(command_type::equalsIgnoreCase))
                {
                    value = Integer.parseInt(args[2]);
                    return new Controller_GameConfigValue(game_name, command_type, value, sender, plugin);
                }
                break;
            }
        }
        return null;
    }
}
