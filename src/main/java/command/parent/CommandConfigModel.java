package command.parent;

import org.bukkit.entity.Player;

public abstract class CommandConfigModel extends CommandModel {

    public CommandConfigModel(String game_name, Player sender) {
        super(game_name, sender);
    }

    public abstract void updateConfiguration();
}
