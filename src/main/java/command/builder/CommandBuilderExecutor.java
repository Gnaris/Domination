package command.builder;

import game.Game;
import org.bukkit.entity.Player;

public abstract class CommandBuilderExecutor {

    protected String game_name;
    protected String type_command;
    protected Player sender;
    protected Game game;

    public CommandBuilderExecutor(String game_name, String type_command, Player sender)
    {
        this.game_name = game_name;
        this.type_command = type_command;
        this.sender = sender;
    }

    protected abstract void checkAndExecuteCommandType();
    protected abstract void executeTypeCommand();

}
