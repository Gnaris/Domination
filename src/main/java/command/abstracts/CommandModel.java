package command.abstracts;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandModel {

    protected Game game;
    protected Player sender;

    public CommandModel(Game game, Player sender)
    {
        this.game = game;
        this.sender = sender;
    }
}
