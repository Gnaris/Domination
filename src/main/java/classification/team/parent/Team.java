package classification.team.parent;

import classification.team.TeamList;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class Team{

    protected Player sender;

    public Team(Player sender)
    {
        this.sender = sender;
    }

    public abstract void sendMessage();


}
