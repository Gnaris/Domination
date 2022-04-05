package command.admin_command.controllers;

import command.admin_command.models.Model_Flag;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Flag extends AdministratorCommandController {

    public Controller_Flag(String map_name, String command_type, String name_value, Main plugin, Player sender) {
        super(map_name, command_type, name_value, plugin, sender);
    }

    @Override
    public void checkAndExecuteCommand() {

        if(!this.plugin.getColiseum_list().containsKey(this.map_name) || this.plugin.getColiseum_list() == null)
        {
            this.sender.sendMessage("§cLe nom de la map est inexistant");
            return;
        }

        switch(this.command_type)
        {
            case "setflag" :
            {
                if(this.plugin.getColiseum_list().get(this.map_name).getFlag_list().get(this.name_value) != null)
                {
                    this.sender.sendMessage("§cLe nom de ce drapeau existe déjà");
                    return;
                }
                break;
            }

            case "deleteflag" :
            {
                if(this.plugin.getColiseum_list().get(this.map_name).getFlag_list().get(this.name_value) == null)
                {
                    this.sender.sendMessage("§cLe nom de ce drapeau est inexistant");
                    return;
                }
                break;
            }
        }

        executeCommand();
    }

    @Override
    public void executeCommand()
    {
        Model_Flag model_flag = new Model_Flag(this.map_name, this.name_value, this.sender.getLocation(), this.map_config, this.plugin.getColiseum_list());

        switch (this.command_type)
        {
            case "setflag" :
            {
                model_flag.addFlag();
                this.sender.sendMessage("§aUn nouveau drapeau à été ajouté");
            }
            case "deleteflag" :
            {
                model_flag.deleteFlag();
                this.sender.sendMessage("Ce drapeau a été supprimé");
            }
        }
    }
}
