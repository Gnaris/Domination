package command.player_command.models.classification;

import classification.kit.KitList;
import classification.team.TeamList;
import command.player_command.parent.CommandModel;
import command.player_command.parent.Configuration;
import main.Main;
import org.bukkit.entity.Player;

public class Model_Classification<T> extends CommandModel implements Configuration {

    private final T value;

    public Model_Classification(String game_name, Player sender, T value, Main plugin) {
        super(game_name, sender, plugin);
        this.value = value;
    }

    @Override
    public void updateConfiguration() {
        if(value.getClass().equals(TeamList.class))
        {
            this.game.getPlayerClassification().get(this.sender.getUniqueId()).setTeam((TeamList) this.value);
        }
        if(value.getClass().equals(KitList.class))
        {
            this.game.getPlayerClassification().get(this.sender.getUniqueId()).setKit((KitList) this.value);
        }
    }
}
