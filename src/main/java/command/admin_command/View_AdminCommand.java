package command.admin_command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// /dta create map <name>
// /dta <map> setflag <name>
// /dta <map> setspawn <team color> <name>
public class View_AdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player && sender.isOp() || sender.hasPermission("domination") && label.equalsIgnoreCase("dta"))){ sender.sendMessage("§cSeul les animateurs ou les opérateurs ont accès à ces commandes"); return false;}
        if(args.length < 3){ sender.sendMessage("§cCommande inconnu veuillez vous renseigner je ne sais pas ou mais quelques part en tout cas"); return false;}

        switch (args.length)
        {
            case 3 :
            {
                if(args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("map"))
                {

                }
                if(args[1].equalsIgnoreCase("setflag"))
                {

                }
                break;
            }

            case 4 :
            {
                if(args[1].equalsIgnoreCase("setspawn"))
                {

                }
            }
        }
        return false;
    }
}
