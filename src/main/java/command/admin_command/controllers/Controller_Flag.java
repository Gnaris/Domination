package command.admin_command.controllers;

import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Flag extends AdministratorCommandController {

    public Controller_Flag(String map_name, String command_type, String name_value, Main plugin, Player sender) {
        super(map_name, command_type, name_value, plugin, sender);
    }

    @Override
    public void checkAndExecuteCommand() {

    }

    @Override
    public void executeCommand() {

    }
}
