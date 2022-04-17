package command.parent;

import classification.kit.KitList;
import classification.team.TeamList;
import map.Coliseum;
import game.Game;
import lombok.Getter;
import main.Main;
import org.bukkit.entity.Player;

@Getter
public abstract class CommandController{

    protected String[] args;
    protected Player sender;
    protected Main plugin;

    protected String game_name;
    protected String map_name;
    protected String command_type;
    protected Game game;
    protected Coliseum map;
    protected KitList kit;
    protected TeamList team;
    protected String name;
    protected int value;
    protected boolean status;


    public CommandController(String args[], Player sender, Main plugin)
    {
        this.args = args;
        this.sender = sender;
        this.plugin = plugin;
    }

    /**
     * Checks if all the conditions of the order are respected
     */
    public abstract void ControlCmd();

    /**
     * Call the model to update the game if controlCmd method is true
     */
    protected abstract void executeCmd();
}
