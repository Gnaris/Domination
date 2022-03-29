package command.parent;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandController {

    protected String command_type;
    protected Player sender;
    protected String game_name;
    protected Game game;


    public CommandController(String game_name, String command_type, Player sender)
    {
        this.command_type = command_type;
        this.sender = sender;
        this.game = Main.getGame(game_name);
        this.game_name = game_name;
    }

    public abstract void checkAndExecuteCommandType();
    public abstract void executeTypeCommand();

}
