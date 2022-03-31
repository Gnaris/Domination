package command.models.gameconfig;

import command.parent.CommandConfigModel;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigStatus extends CommandConfigModel {

    private boolean status;
    public Model_GameConfigStatus(String game_name, Player sender, boolean status, Main config) {
        super(game_name, sender, config);
        this.status = status;
    }

    @Override
    public void updateConfiguration()
    {
        this.game.setOpen(this.status);
    }
}
