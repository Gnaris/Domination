package map_command.controllers;

import main.Main;
import utils.MapsUtils;
import map_command.models.Model_Map;
import map_command.parent.AdminCmdController;
import org.bukkit.entity.Player;

public class Controller_Map extends AdminCmdController {

    public Controller_Map(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dta [create, delete] <map name>
        this.command_type = args[0];
        this.map_name = args[1];
        this.map = MapsUtils.getMapByName(this.maps_list, this.map_name);
    }

    @Override
    public void controlCmd()
    {
        String error = null;
        switch(this.command_type)
        {
            case "create" :
            {
                error = this.maps_list.contains(this.map) ? "§cLe nom de cette map existe déjà" : null;
                break;
            }

            case "delete" :
            {
                error = !this.maps_list.contains(this.map) ? "§cMap inconnu" : null;
                break;
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
    protected void executeCmd() {

        Model_Map model_map = new Model_Map(this, this.map_name);

        switch(this.command_type)
        {
            case "create" :
            {
                model_map.createColiseum();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été crée");
                break;
            }

            case "delete" :
            {
                model_map.deleteColiseum();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été supprimée");
                break;
            }
        }
    }
}
