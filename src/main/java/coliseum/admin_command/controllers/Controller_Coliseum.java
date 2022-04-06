package coliseum.admin_command.controllers;

import coliseum.admin_command.models.Model_Coliseum;
import coliseum.admin_command.parent.AdminCmdController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Coliseum extends AdminCmdController {


    public Controller_Coliseum(Player sender, String command_type, String map_name, String value_name, Main plugin) {
        super(sender, command_type, map_name, value_name, plugin);
    }

    @Override
    public void controlCmd()
    {
        boolean response = true;

        switch(command_type)
        {
            case "create" :
            {
                if(this.coliseums_list.containsKey(this.map_name))
                {
                    this.sender.sendMessage("§cLe nom de cette map existe déjà");
                    response = false;
                }
                break;
            }

            case "delete" :
            {
                if(!this.coliseums_list.containsKey(this.map_name))
                {
                    this.sender.sendMessage("§cMap inconnu");
                    response = false;
                }
                break;
            }
        }

        if(response)
        {
            executeCmd();
        }
    }

    @Override
    protected void executeCmd() {

        Model_Coliseum model_coliseum = new Model_Coliseum(this.map_name, this.value_name, this.sender, this.coliseum_config, this.coliseums_list);

        switch(this.command_type)
        {
            case "create" :
            {
                model_coliseum.createColiseum();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été crée");
                break;
            }

            case "delete" :
            {
                model_coliseum.deleteColiseum();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été supprimée");
                break;
            }
        }
    }
}
