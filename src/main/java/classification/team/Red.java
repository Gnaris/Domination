package classification.team;

import classification.team.parent.Team;
import org.bukkit.entity.Player;

public class Red extends Team {

    public Red(Player sender) {
        super(sender);
        this.team = TeamList.RED;
    }

    @Override
    public void sendMessage() {

    }
}
