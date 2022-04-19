package command;

import command.factory.ControllerFactory;
import command.parent.CommandController;
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
            Player sender = (Player) s;
            if(args.length == 0)
            {
                Bukkit.dispatchCommand(sender, "dt create Gnaris");
                Bukkit.dispatchCommand(sender, "dt join Gnaris");
                Bukkit.dispatchCommand(sender, "dt open Gnaris");
                Bukkit.dispatchCommand(sender, "dt Gnaris map a");
                Bukkit.dispatchCommand(sender, "dt load Gnaris");
                return false;
            }
            CommandController controller = ControllerFactory.getInstance(args, sender, this.plugin);
            if(controller != null)
            {
                controller.ControlCmd();
            }
            else
            {
                sender.sendMessage("§cCommande inconnu");
            }
        }
        return false;
    }
}
