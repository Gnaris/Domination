package gameplay.core;

import classification.team.TeamList;
import coliseum.core.Flag;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class PointIncreaser extends BukkitRunnable {

    private Game game;
    private int flag_point;

    public PointIncreaser(Game game)
    {
        this.game = game;
        flag_point = (int) game.getGameCharacteristicValue("flagpoint");
    }

    @Override
    public void run() {
        for(Flag flag : this.game.getMap().getFlag_list())
        {
            if(flag.isCatched())
            {
                this.game.getTeam_point().put(flag.getTeam_catched(), this.game.getTeam_point().get(flag.getTeam_catched()) + this.flag_point);
                Bukkit.broadcastMessage("Point : " + this.game.getTeam_point().get(flag.getTeam_catched()));
            }
        }
        for(TeamList team_color : TeamList.values())
        {
            if(team_color != TeamList.RANDOM)
            {
                if(this.game.getTeam_point().get(team_color) >= this.game.getGameCharacteristicValue("point"))
                {
                    this.game.setTimer((double) 0);
                    this.cancel();
                }
            }
        }
    }
}
