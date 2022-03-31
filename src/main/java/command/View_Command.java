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

    private final Main config;

    public View_Command(Main config)
    {
        this.config = config;
    }


    @Override
    public boolean onCommand(@Nullable CommandSender sender,@Nullable org.bukkit.command.Command command,@Nullable String label,@Nullable String[] args)
    {

        if(sender instanceof Player && args != null && label != null && label.equalsIgnoreCase("dt"))
        {
                if(args.length == 0){ Bukkit.dispatchCommand(sender, "dt help"); return false;}

                Player player = (Player) sender;
                CommandController controller;
                switch(args.length)
                {

                    case 1 :
                    {
                        // /dt help
                        controller = new Controller_Request(args[0], player, this.config);
                        controller.checkAndExecuteCommand();
                        return true;
                    }


                    case 2 :
                    {
                        // /dt team <value> OR /dt kit <value>
                        if(args[0].equalsIgnoreCase("team") || args[0].equalsIgnoreCase("kit"))
                        {
                            Controller_Classification controller = (Controller_Classification) new Controller_Classification.Builder(player)
                                    .setCommand_type(args[0])
                                    .setString_Value(args[1])
                                    .setConfig(this.config)
                                    .build();
                            controller.checkAndExecuteCommand();
                            return true;
                        }

                        // /dt open <game_name> OR /dt close <game_name>
                        if(args[0].equalsIgnoreCase("open") || args[0].equalsIgnoreCase("close"))
                        {
                            controller = new Controller_GameConfigStatus.Builder(player)
                            controller.checkAndExecuteCommand();
                            return true;
                        }

                        // dt <request> <game_name>
                        controller = new Controller_Request(args[1], args[0], player, this.config);
                        controller.checkAndExecuteCommand();
                        return true;
                    }


                    case 3 :
                    {
                        // /dt <game_name> <key map> <map name>
                        if(args[1].equalsIgnoreCase("map"))
                        {
                            return true;
                        }

                        // /dt <game_name> <key configuration> <value>
                        controller = new Controller_GameConfigValue(args[0], args[1], player, this.config, Integer.parseInt(args[2]));
                        controller.checkAndExecuteCommand();
                        return true;
                    }

                }
            }
        return false;
    }
}
