package command.models.gameconfig;

import command.parent.CommandConfigModel;
import org.bukkit.entity.Player;

public class Model_GameConfigMap extends CommandConfigModel {

    public Model_GameConfigMap(String game_name, Player sender) {
        super(game_name, sender);
    }

    @Override
    public void updateConfiguration() {

    }

}
