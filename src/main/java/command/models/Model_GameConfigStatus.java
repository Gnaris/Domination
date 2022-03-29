package command.models;

import command.parent.CommandModel;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigStatus extends CommandModel {

    public Model_GameConfigStatus(String game_name, Player sender) {
        super(game_name, sender);
    }

    public void open()
    {
        this.game.setOpen(true);
        Main.game_list.put(this.game_name, this.game);
    }

    public void close()
    {
        this.game.setOpen(false);
        Main.game_list.put(this.game_name, this.game);
    }
}
