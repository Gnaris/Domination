package command.models.game_config;

import command.parent.CommandModel;
import command.parent.Configuration;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigStatus extends CommandModel implements Configuration {

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
