package command;

import coliseum.Flag;
import command.factory.ControllerFactory;
import command.parent.CommandController;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
                sender.sendMessage("world" + sender.getWorld());
                sender.sendMessage("world " + sender.getWorld().getName());
                sender.sendMessage("world " + sender.getWorld().toString());
                return true;
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
