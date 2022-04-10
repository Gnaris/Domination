package gameplay;

import classification.team.TeamList;
import game.Game;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class PointImprover extends BukkitRunnable {

    private Game game;
    private Map<TeamList, Integer> teams_point;

    public PointImprover(Game game)
    {
        this.game = game;
        this.teams_point = game.getTeam_point();
    }

    @Override
    public void run() {

    }
}
