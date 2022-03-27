package command;

import command.request.RequestController;
import game.Game;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

import java.util.Map;

import static main.Main.game_list;

public class Command implements CommandExecutor{
    @Override
    public boolean onCommand(@Nullable CommandSender sender,@Nullable org.bukkit.command.Command command,@Nullable String label,@Nullable String[] args)
    {
        if(sender instanceof Player && args != null && label != null)
        {
            if(label.equalsIgnoreCase("dt"))
            {
                if(args.length != 0)
                {
                    switch(args.length)
                    {
                        case 1 :
                            String argument = args[0];
                            switch(argument)
                            {
                                case "help" : break;
                                default : sender.sendMessage("§cCommande incorrecte, faites /dt help pour vous aidez"); break;
                            }
                            break;
                        case 2 :
                            RequestController request_controller = new RequestController(args[1], args[0], (Player) sender);
                            request_controller.checkAndExecuteCommandType();
                            sender.sendMessage("info : " + Main.game_list.get("caca").getOwner() + " size : " + game_list.size());
                        break;
                        case 3 :
                        {
                            String game_name = args[0];
                            String config_value = args[2];
                            switch (args[1])
                            {
                                case "player" : sender.sendMessage("player"); break;
                                case "time" : sender.sendMessage("time"); break;
                                case "flag" : sender.sendMessage("flag"); break;
                                case "radius" : sender.sendMessage("radius"); break;
                                case "spawn" : sender.sendMessage("spawn"); break;
                                case "respawntime" : sender.sendMessage("respawntime"); break;
                                case "killpoint" : sender.sendMessage("killpoint"); break;
                                case "flagpoint" : sender.sendMessage("flagpoint"); break;
                                case "catchtime" : sender.sendMessage("catchtime"); break;
                                case "catchspeed" : sender.sendMessage("catchspeed"); break;
                                case "public" : sender.sendMessage("public"); break;
                                case "map" : sender.sendMessage("map"); break;
                                default : sender.sendMessage("§cCommande incorrecte, faites /dt help pour vous aidez"); break;
                            }
                            break;
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
