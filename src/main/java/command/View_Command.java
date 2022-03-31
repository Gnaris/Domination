package command;

import command.controllers.classification.Controller_Classification;
import command.controllers.gameconfig.Controller_GameConfigStatus;
import command.controllers.gameconfig.Controller_GameConfigValue;
import command.controllers.request.Controller_Request;
import command.parent.CommandController;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class View_Command implements CommandExecutor{

    private Main config;

    public View_Command(Main config)
    {
        this.config = config;
    }


    @Override
    public boolean onCommand(@Nullable CommandSender sender,@Nullable org.bukkit.command.Command command,@Nullable String label,@Nullable String[] args)
    {
        CommandController controller;

        if(sender instanceof Player && args != null && label != null && label.equalsIgnoreCase("dt"))
        {
                if(args.length == 0){ Bukkit.dispatchCommand((Player) sender, "dt help"); return false;}

                    Player player = (Player) sender;
                    switch(args.length)
                    {

                        case 1 :
                        {
                            // /dt help
                            controller = new Controller_Request.Builder(player)
                                    .setCommand_type("help")
                                    .build();
                            controller.checkAndExecuteCommand();
                            return true;
                        }


                        case 2 :
                        {
                            // /dt team OR /dt kit
                            if(args[0].equalsIgnoreCase("team") || args[0].equalsIgnoreCase("kit"))
                            {
                                Controller_Classification controller_classification = new Controller_Classification(args[0], args[1], (Player) sender);
                                controller_classification.checkAndExecuteCommandType();
                                return true;
                            }

                            // /dt open <game_name> OR /dt close <game_name>
                            if(args[0].equalsIgnoreCase("open") || args[0].equalsIgnoreCase("close"))
                            {
                                controller = new Controller_GameConfigStatus.Builder()
                                        .setCommand_type(args[0])
                                        .setGame_name(args[1])
                                        .setSender((Player) sender)
                                        .setConfig(this.config)
                                        .build();
                                controller.checkAndExecuteCommand();
                                return true;
                            }

                            // dt <request> <game_name>
                            controller = new CommandController.Builder()
                                    .setCommand_type(args[0])
                                    .setGame_name(args[1])
                                    .setSender((Player) sender)
                                    .setConfig(this.config)
                                    .build();
                            controller.checkAndExecuteCommand();
                            return true;
                        }


                        case 3 :
                        {
                            // /dt <game_name> map <map name>
                            if(command_type.equalsIgnoreCase("map"))
                            {
                                return true;
                            }

                            // /dt <game_name> <key configuration> <value>
                            controller = new Controller_GameConfigValue(game_name, command_type, (Player) sender, Integer.parseInt(args[2]), this.config);
                            controller.checkAndExecuteCommand();
                            return true;
                        }

                    }

                }
        return false;
    }
}
