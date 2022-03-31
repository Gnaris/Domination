package command.parent;

import game.Game;
import main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandController{

    protected String game_name;
    protected String command_type;
    protected Player sender;
    protected Game game;
    protected Main config;

    public CommandController(String game_name, String command_type, Player sender, Main config)
    {
        this.game_name = game_name;
        this.game = Main.getGame(game_name);
        this.command_type = command_type;
        this.sender = sender;
        this.config = config;
    }

    public CommandController(String command_type, Player sender, Main config)
    {
        this.command_type = command_type;
        this.sender = sender;
        this.config = config;
    }

    public abstract void checkAndExecuteCommand();

    public abstract void executeCommand();
}
