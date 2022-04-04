package command.admin_command;

import coliseum.Coliseum;
import command.admin_command.factory.ControllerAdministorFactory;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;


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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player && sender.isOp() || sender.hasPermission("domination") && label.equalsIgnoreCase("dta"))){ sender.sendMessage("§cSeul les animateurs ou les opérateurs ont accès à ces commandes"); return false;}
        if(args.length < 3){ sender.sendMessage("§cCommande inconnu veuillez vous renseigner je ne sais pas ou mais quelques part en tout cas"); return false;}


        AdministratorCommandController controller;
        switch (args.length)
        {
            case 3 :
            {
                if(args[0].equalsIgnoreCase("aze"))
                {
                    for(Map.Entry a : this.plugin.getColiseum_list().entrySet())
                    {
                        Coliseum coliseum = (Coliseum) a.getValue();
                         sender.sendMessage("La list des arènes : " + coliseum.getName());
                    }
                }
                if((args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("delete")) && args[1].equalsIgnoreCase("map"))
                {
                    assert sender instanceof Player;
                    controller = ControllerAdministorFactory.getInstance(args, (Player) sender, this.plugin);
                    assert controller != null;
                    controller.checkAndExecuteCommand();
                    return true;
                }
                if(args[1].equalsIgnoreCase("setflag") || args[0].equalsIgnoreCase("deleteflag"))
                {
                    assert sender instanceof Player;
                    controller = ControllerAdministorFactory.getInstance(args, (Player) sender, this.plugin);
                    assert controller != null;
                    controller.checkAndExecuteCommand();
                    return true;
                }
                break;
            }

            case 4 :
            {
                if(args[1].equalsIgnoreCase("setspawn") || args[1].equalsIgnoreCase("deletespawn"))
                {
                    assert sender instanceof Player;
                    controller = ControllerAdministorFactory.getInstance(args, (Player) sender, this.plugin);
                    assert controller != null;
                    controller.checkAndExecuteCommand();
                    return true;
                }
            }
        }
        return false;
    }
}
