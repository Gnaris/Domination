package command.controllers;

import command.parent.CommandController;
import org.bukkit.entity.Player;

public class Controller_Classification extends CommandController {
    public Controller_Classification(String game_name, String command_type, Player sender) {
        super(game_name, command_type, sender);
    }

    @Override
    public void checkAndExecuteCommandType() {

    }

    @Override
    public void executeTypeCommand() {

    }
}
