package command.player_command.factory;

import command.player_command.parent.CommandController;
import command.player_command.controllers.classification.Controller_Classification;
import command.player_command.controllers.game_config.Controller_GameConfigStatus;
import command.player_command.controllers.game_config.Controller_GameConfigValue;
import command.player_command.controllers.request.Controller_Request;
import main.Main;
import org.bukkit.entity.Player;

public class ControllerFactory
{

    public static CommandController getInstance(String type, String[] args, Player sender, Main plugin)
    {
        switch(type)
        {
            case "help" : return new Controller_Request(args[0], sender, plugin);
            case "classification" : return new Controller_Classification(args[0], sender, plugin, args[1]);
            case "status" : return new Controller_GameConfigStatus(args[1], args[0], sender, plugin);
            case "request" : return new Controller_Request(args[1], args[0], sender, plugin);
            case "map" : break;
            case "game_config" : return new Controller_GameConfigValue(args[0], args[1], sender, plugin, Integer.parseInt(args[2]));
        }
        return null;
    }
}
