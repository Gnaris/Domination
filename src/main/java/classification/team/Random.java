package classification.team;

import classification.team.parent.Team;
import org.bukkit.entity.Player;

public class Random extends Team {

    public Random(Player sender) {
        super(sender);
        this.team = TeamList.RANDOM;
    }

    @Override
    public void sendMessage() {

    }
}
