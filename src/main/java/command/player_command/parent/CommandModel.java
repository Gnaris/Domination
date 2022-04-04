package command.player_command.parent;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandModel {

    protected String game_name;
    protected Game game;
    protected Player sender;
    protected Main plugin;

    public CommandModel(String game_name, Player sender, Main plugin)
    {
        this.game_name = game_name;
        this.game = plugin.getGame_list().get(game_name);
        this.sender = sender;
        this.plugin = plugin;
    }
}
