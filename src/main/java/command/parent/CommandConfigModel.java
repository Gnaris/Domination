package command.parent;

import main.Main;
import org.bukkit.entity.Player;

public abstract class CommandConfigModel extends CommandModel {

    public CommandConfigModel(String game_name, Player sender, Main config) {
        super(game_name, sender, config);
    }

    public abstract void updateConfiguration();
}
