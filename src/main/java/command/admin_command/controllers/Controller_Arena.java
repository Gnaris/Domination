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
                if(this.arena_lists.contains(this.map_name)){ this.sender.sendMessage("§cLe nom de cette map existe déjà"); return; }
                break;
            }
            case "delete" :
                if(!this.arena_lists.contains(this.map_name)){ this.sender.sendMessage("§cMap inconnu"); return; }
                break;
        }

        executeCommand();
    }

    @Override
    public void executeCommand() {

        Model_Arena model_arena = new Model_Arena();

        switch(this.command_type)
        {
            case "create" :
            {

            }

            case "delete" :
            {

            }
        }
    }
}
