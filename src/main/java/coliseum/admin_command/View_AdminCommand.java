package coliseum.admin_command;

import coliseum.admin_command.factory.ControllerAdministorFactory;
import coliseum.admin_command.parent.AdminCmdController;
import main.Main;
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
public class View_AdminCommand implements CommandExecutor {

    private final Main plugin;

    public View_AdminCommand(Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {

        if(player instanceof Player && player.hasPermission("domination"))
        {
            Player sender = (Player) player;
            if(args.length < 2)
            {
                sender.sendMessage("§cCommande inconnu veuillez vous renseigner je ne sais pas ou mais quelques part en tout cas");
                return false;
            }

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
