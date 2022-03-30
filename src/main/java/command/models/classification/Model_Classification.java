package command.models.classification;

import classification.kit.KitList;
import classification.team.TeamList;
import command.parent.CommandConfigModel;
import org.bukkit.entity.Player;

public class Model_Classification<T> extends CommandConfigModel {

    private T value;

    public Model_Classification(String game_name, Player sender, T value) {
        super(game_name, sender);
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
