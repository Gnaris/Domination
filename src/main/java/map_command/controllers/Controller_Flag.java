package map_command.controllers;

import main.Main;
import utils.MapsUtils;
import map_command.models.Model_Flag;
import map_command.parent.AdminCmdController;
import org.bukkit.entity.Player;

public class Controller_Flag extends AdminCmdController {


    public Controller_Flag(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dta <map name> [setflag, deleteflag] <name of flag>
        this.map_name = args[0];
        this.command_type = args[1];
        this.name = args[2];
        this.map = MapsUtils.getMapByName(this.maps_list, this.map_name);
    }

    @Override
    public void controlCmd() {

        String error;
        error = this.map == null ? "§cLe nom de la map est inexistant" : null;
        if(error == null)
        {
            switch(this.command_type)
            {
                case "setflag" :
                {
                    error = this.map.getFlag_list().stream()
                            .anyMatch(flag_list -> flag_list.getName().equalsIgnoreCase(this.name)) ?
                            "§cLe nom de ce drapeau existe déjà" : null;
                    break;
                }

                case "deleteflag" :
                {
                    error = this.map.getFlag_list().stream()
                            .noneMatch(flag_list -> flag_list.getName().equalsIgnoreCase(this.name)) ?
                            "§cLe nom de ce drapeau n'existe pas" : null;
                    break;
                }
            }
        }

        if(error == null)
        {
            executeCmd();
        }
        else
        {
            this.sender.sendMessage(error);
        }
    }

    @Override
    protected void executeCmd()
    {
        Model_Flag model_flag = new Model_Flag(this);

        switch (this.command_type)
        {
            case "setflag" :
            {
                model_flag.addFlag();
                this.sender.sendMessage("§aUn nouveau drapeau à été ajouté");
                break;
            }
            case "deleteflag" :
            {
                model_flag.deleteFlag();
                this.sender.sendMessage("§aCe drapeau a été supprimé");
                break;
            }
        }
    }
}
