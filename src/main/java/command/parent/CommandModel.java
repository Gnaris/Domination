package command.parent;

import classification.KitList;
import classification.TeamList;
import map.Coliseum;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandModel {

    protected String game_name;
    protected Game game;
    protected Player sender;
    protected Main plugin;
    protected TeamList team;
    protected KitList kit;
    protected Coliseum coliseum;
    protected boolean status;
    protected int value;
    protected String name;
    protected String command_type;

    public CommandModel(CommandController controller)
    {
        this.game_name = controller.getGame_name();
        this.game = controller.getGame();
        this.sender = controller.getSender();
        this.plugin = controller.getPlugin();
        this.team = controller.getTeam();
        this.kit = controller.getKit();
        this.coliseum = controller.getMap();
        this.status = controller.isStatus();
        this.value = controller.getValue();
        this.name = controller.getName();
        this.command_type = controller.getCommand_type();
    }
}
