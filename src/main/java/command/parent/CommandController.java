package command.parent;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandController{

    protected String game_name;
    protected String command_type;
    protected Player sender;
    protected Game game;
    protected Main plugin;

    public CommandController(String game_name, String command_type, Player sender, Main plugin)
    {
        this.game_name = game_name;
        this.game = plugin.getGame_list().get(game_name);
        this.command_type = command_type;
        this.sender = sender;
        this.plugin = plugin;
    }

    public CommandController(String command_type, Player sender, Main plugin)
    {
        this.command_type = command_type;
        this.sender = sender;
        this.plugin = plugin;
    }

    public abstract void checkAndExecuteCommand();

    public abstract void executeCommand();
}
