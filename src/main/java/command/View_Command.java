package command;

import command.game_configuration.Controller_GameConfig;
import command.request.Controller_Request;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class View_Command<T> implements CommandExecutor{

    @Override
    public boolean onCommand(@Nullable CommandSender sender,@Nullable org.bukkit.command.Command command,@Nullable String label,@Nullable String[] args)
    {
        if(sender instanceof Player && args != null && label != null)
        {
            if(label.equalsIgnoreCase("dt"))
            {
                Controller_Request request_controller;
                String game_name;
                String command_type;
                if(args.length != 0)
                {
                    switch(args.length)
                    {
                        case 1 :
                            request_controller = new Controller_Request("", "help", (Player) sender);
                            request_controller.checkAndExecuteCommandType();
                            return true;
                        case 2 :
                            command_type = args[0];
                            game_name = args[1];
                            if(command_type.equalsIgnoreCase("team") || command_type.equalsIgnoreCase("class"))
                            {

                                return true;
                            }
                            if(command_type.equalsIgnoreCase("open") || command_type.equalsIgnoreCase("close"))
                            {
                                game_name = args[0];
                                command_type = args[1];

                                return true;
                            }
                            request_controller = new Controller_Request(game_name, command_type, (Player) sender);
                            request_controller.checkAndExecuteCommandType();
                            return true;
                        case 3 :
                        {
                            game_name = args[0];
                            command_type = args[1];
                            Controller_GameConfig game_config_controller = new Controller_GameConfig(game_name, command_type, (Player) sender, Integer.parseInt(args[2]));
                            game_config_controller.checkAndExecuteCommandType();
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
