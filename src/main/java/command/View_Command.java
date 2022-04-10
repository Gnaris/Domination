package command;

import classification.team.TeamList;
import command.factory.ControllerFactory;
import command.parent.CommandController;
import main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
