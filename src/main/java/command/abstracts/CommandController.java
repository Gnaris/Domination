package command.abstracts;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandController {

    protected String type_command;
    protected Player sender;
    protected Game game;

    public CommandController(String game_name, String type_command, Player sender)
    {
        this.type_command = type_command;
        this.sender = sender;
        this.game = Main.getGame(game_name);
    }

    protected abstract void checkAndExecuteCommandType();
    protected abstract void executeTypeCommand();

}
