package map_command;

import main.Main;
import map_command.factory.ControllerAdministorFactory;
import map_command.parent.AdminCmdController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


// /dta create <name>
// /dta delete <name>

// /dta <map> setflag <name>
// /dta <map> deleteflag <name>

// /dta <map> setspawn <team color> <name>
// /dta <map> deletespawn <team color> <name>

// /dta show map
// /dta showflag <name>
// /dta showspawn <name>
public class View_MapCommand implements CommandExecutor {

    private final Main plugin;

    public View_MapCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {

        if(player instanceof Player && player.hasPermission("domination"))
        {
            Player sender = (Player) player;
            AdminCmdController controller = ControllerAdministorFactory.getInstance(sender, args, this.plugin);
            if(controller != null)
            {
                controller.controlCmd();
            }
            else
            {
                sender.sendMessage("§cCommande inconnu");
            }
        }
        return false;
    }
}
