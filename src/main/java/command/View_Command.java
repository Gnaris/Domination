package command;

import command.controllers.classification.Controller_Classification;
import command.controllers.gameconfig.Controller_GameConfigStatus;
import command.controllers.gameconfig.Controller_GameConfigValue;
import command.controllers.request.Controller_Request;
import command.parent.CommandController;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class View_Command implements CommandExecutor{



    @Override
    public boolean onCommand(@Nullable CommandSender sender,@Nullable org.bukkit.command.Command command,@Nullable String label,@Nullable String[] args)
    {
        CommandController controller;
        String game_name;
        String command_type;
        String value;

        if(sender instanceof Player && args != null && label != null)
        {
            if(label.equalsIgnoreCase("dt"))
            {

                if(args.length != 0)
                {
                    switch(args.length)
                    {
                        case 1 :
                            controller = new Controller_Request(null, "help", (Player) sender);
                            controller.checkAndExecuteCommand();
                            return true;
                        case 2 :
                            command_type = args[0];
                            if(command_type.equalsIgnoreCase("team") || command_type.equalsIgnoreCase("kit"))
                            {
                                Controller_Classification controller_classification = new Controller_Classification(command_type, args[1], (Player) sender);
                                controller_classification.checkAndExecuteCommandType();
                                return true;
                            }
                            if(command_type.equalsIgnoreCase("open") || command_type.equalsIgnoreCase("close"))
                            {
                                game_name = args[1];
                                command_type = args[0];
                                Controller_GameConfigStatus game_config_status = new Controller_GameConfigStatus(game_name, command_type, (Player) sender);
                                game_config_status.checkAndExecuteCommand();
                                return true;
                            }
                            game_name = args[1];
                            controller = new Controller_Request(game_name, command_type, (Player) sender);
                            controller.checkAndExecuteCommand();
                            return true;
                        case 3 :
                        {
                            game_name = args[0];
                            command_type = args[1];
                            if(command_type.equalsIgnoreCase("map"))
                            {
                                return true;
                            }
                            controller = new Controller_GameConfigValue(game_name, command_type, (Player) sender, Integer.parseInt(args[2]));
                            controller.checkAndExecuteCommand();
                            return true;
                        }
                    }
                }
                else
                {
                    Bukkit.dispatchCommand(sender, "dt help");
                }
            }
        }
        return false;
    }
}
