package command.models;

import command.parent.CommandModel;
import org.bukkit.entity.Player;

public class Model_GameConfigMap extends CommandModel {

    public Model_GameConfigMap(String game_name, Player sender) {
        super(game_name, sender);
    }

}
