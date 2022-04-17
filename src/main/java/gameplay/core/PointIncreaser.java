package gameplay.core;

import classification.team.TeamList;
import map.core.flag.Flag;
import game.Game;
import org.bukkit.scheduler.BukkitRunnable;

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
        //Increase score point to team
        for(Flag flag : this.game.getMap().getFlag_list())
        {
            if(flag.getTeam_catched().isPlayable())
            {
                this.game.getTeam_point().put(flag.getTeam_catched(), this.game.getTeam_point().get(flag.getTeam_catched()) + this.flag_point);
            }
        }

        //checks if the score is reached
        for(TeamList team_color : TeamList.values())
        {
            if(team_color.isPlayable())
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
