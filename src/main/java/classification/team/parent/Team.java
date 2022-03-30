package classification.team.parent;

import classification.team.TeamList;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class Team{

    protected Player sender;
    protected TeamList team = null;

    public Team(Player sender)
    {
        this.sender = sender;
    }

    public abstract void sendMessage();

    public void setTeam()
    {
        for(Map.Entry game_name : Main.game_list.entrySet())
        {
            Game game = (Game) game_name.getValue();
            if(game.getPlayerList().contains(this.sender.getUniqueId()))
            {
                game.getPlayerClassification().get(this.sender.getUniqueId()).setTeam(this.team);
                break;
            }
        }
    }
}
