package command.admin_command.controllers;

import command.admin_command.models.Model_Arena;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Arena extends AdministratorCommandController {

    public Controller_Arena(String map_name, String command_type, String name_value, Main plugin, Player sender) {
        super(map_name, command_type, name_value, plugin, sender);
    }

    @Override
    public void checkAndExecuteCommand() {
        switch(command_type)
        {
            case "create" :
            {
                if(this.plugin.getColiseum_list() == null) break;
                if(this.plugin.getColiseum_list().containsKey(this.map_name)){ this.sender.sendMessage("§cLe nom de cette map existe déjà"); return;}
                break;

            }
            case "delete" :
                if(this.plugin.getColiseum_list() == null){ this.sender.sendMessage("§cMap inconnu"); return;}
                if(!this.plugin.getColiseum_list().containsKey(this.map_name)){ this.sender.sendMessage("§cMap inconnu"); return;}
                break;
        }

        executeCommand();
    }

    @Override
    public void executeCommand() {

        Model_Arena model_arena = new Model_Arena(this.map_name, this.map_config, this.plugin.getColiseum_list());

        switch(this.command_type)
        {
            case "create" :
            {
                model_arena.createArena();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été crée");
                break;
            }

            case "delete" :
            {
                model_arena.deleteArena();
                this.sender.sendMessage("§aL'arène " + this.map_name + " a bien été supprimée");
                break;
            }
        }
    }
}
