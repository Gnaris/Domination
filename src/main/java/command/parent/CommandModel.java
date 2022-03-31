package command.parent;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandModel {

    protected String game_name;
    protected Game game;
    protected Player sender;
    protected Main config;

    public CommandModel(String game_name, Player sender, Main config)
    {
        this.game_name = game_name;
        this.game = Main.getGame(game_name);
        this.sender = sender;
        this.config = config;
    }
}
