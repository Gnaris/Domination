package command.parent;

import classification.kit.KitList;
import classification.team.TeamList;
import coliseum.Coliseum;
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

    public abstract void ControlCmd();

    protected abstract void executeCmd();
}
