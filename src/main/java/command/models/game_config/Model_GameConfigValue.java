package command.models.game_config;

import command.parent.CommandModel;
import command.parent.Configuration;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigValue extends CommandModel implements Configuration {

    private final int value;
    private final String config_key;

    public Model_GameConfigValue(String game_name, String config_key, int value, Player sender, Main config)
    {
        super(game_name, sender, config);
        this.value = value;
        this.config_key = config_key;
    }

    @Override
    public void updateConfiguration()
    {
        this.game.setGameCharacteristicValue(this.config_key, this.value);
    }
}
