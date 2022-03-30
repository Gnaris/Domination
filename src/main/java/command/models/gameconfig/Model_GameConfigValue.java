package command.models.gameconfig;

import command.parent.CommandConfigModel;
import org.bukkit.entity.Player;

public class Model_GameConfigValue extends CommandConfigModel {

    private final int value;
    private final String key;

    public Model_GameConfigValue(String game_name, Player sender,String key, int value)
    {
        super(game_name, sender);
        this.value = value;
        this.key = key;
    }

    @Override
    public void updateConfiguration()
    {
        this.game.setGameCharacteristicValue(this.key, this.value);
    }
}
