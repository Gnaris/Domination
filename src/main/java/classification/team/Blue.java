package classification.team;

import classification.team.parent.Team;
import org.bukkit.entity.Player;

public class Blue extends Team {

    public Blue(Player sender) {
        super(sender);
        this.team = TeamList.BlUE;
    }

    @Override
    public void sendMessage() {

    }
}
