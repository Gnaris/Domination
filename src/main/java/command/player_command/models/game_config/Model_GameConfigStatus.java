package command.player_command.models.game_config;

import command.player_command.parent.CommandModel;
import command.player_command.parent.Configuration;
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
