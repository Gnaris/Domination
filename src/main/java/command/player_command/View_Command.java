package command.player_command;

import command.parent.CommandController;
import command.factory.ControllerFactory;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class View_Command implements CommandExecutor{

    private final Main plugin;

    public View_Command(Main plugin)
    {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@Nullable CommandSender s,@Nullable Command command,@Nullable String label,@Nullable String[] args)
    {

        if(s instanceof Player && args != null && label != null && label.equalsIgnoreCase("dt"))
        {
                if(args.length == 0){ Bukkit.dispatchCommand(s, "dt help"); return false;}

                Player sender = (Player) s;
                CommandController controller;
                switch(args.length)
                {

                    case 1 :
                    {
                        // /dt help
                        controller = ControllerFactory.getInstance("help", args, sender, this.plugin);
                        controller.checkAndExecuteCommand();
                        return true;
                    }


                    case 2 :
                    {
                        // /dt team <value> OR /dt kit <value>
                        if(args[0].equalsIgnoreCase("team") || args[0].equalsIgnoreCase("kit"))
                        {
                           controller = ControllerFactory.getInstance("classification", args, sender, this.plugin);
                           controller.checkAndExecuteCommand();
                           return true;
                        }

                        // /dt open <game_name> OR /dt close <game_name>
                        if(args[0].equalsIgnoreCase("open") || args[0].equalsIgnoreCase("close"))
                        {
                            controller = ControllerFactory.getInstance("status", args, sender, this.plugin);
                            controller.checkAndExecuteCommand();
                            return true;
                        }

                        // dt <request> <game_name>
                        controller = ControllerFactory.getInstance("request", args, sender, this.plugin);
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
                        controller = ControllerFactory.getInstance("game_config", args, sender, this.plugin);
                        controller.checkAndExecuteCommand();
                        return true;
                    }
                }
            }
        return false;
    }
}
