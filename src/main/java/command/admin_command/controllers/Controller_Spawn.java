package command.admin_command.controllers;

import classification.team.TeamList;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Spawn extends AdministratorCommandController {

    private String team_color;

    public Controller_Spawn(String map_name, String command_type, String name_value, String team_color, Main plugin, Player sender) {
        super(map_name, command_type, name_value, plugin, sender);
        this.team_color = team_color;
    }

    @Override
    public void checkAndExecuteCommand() {

    }

    @Override
    public void executeCommand() {

    }
}
