package gameplay.core;

import classification.team.TeamList;
import coliseum.core.Flag;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.*;
import java.util.stream.Collectors;

public class Statistic extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");
    private List<String> scores = new ArrayList<>();

    private final Game game;
    private int timer;

    public Statistic(Game game)
    {
        this.game = game;
        this.timer = (int) game.getGameCharacteristicValue("time");
    }

    private Scoreboard openScoreBoard()
    {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        fillScore();
        int size = this.scores.size();
        for(int i = 0; i < this.scores.size(); i ++)
        {
            this.objective.getScore(this.scores.get(i)).setScore(size);
            size--;
        }
        return this.board;
    }

    @Override
    public void run()
    {
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            Bukkit.getPlayer(playerUUID).setScoreboard(openScoreBoard());
        }
        this.objective.getScoreboard().resetScores("§eTemps : " + this.timer);
        this.timer--;
        this.objective.getScore("§eTemps : " + this.timer).setScore(0);
        if(this.timer <= -1)
        {
            this.cancel();
        }
    }

    private void fillScore()
    {
        this.scores.add("§a§lGameplay");
        this.scores.add("");
        this.scores.add("§7§lLes drapeaux :");
        for(Flag flag : this.game.getMap().getFlag_list())
        {
            if(flag.isCatched())
            {
                this.objective.getScoreboard().resetScores("§6 Drapeau " + flag.getName() + " : " + flag.getTeam_catched().getName());
                this.objective.getScoreboard().resetScores("§6 Drapeau " + flag.getName() + " : none");
            }
            this.scores.add(flag.isCatched() ?
                    "§6 Drapeau " + flag.getName() + " : " + flag.getTeam_catched().getName() :
                    "§6 Drapeau " + flag.getName() + " : none");
        }
        this.scores.add(" ");
        this.scores.add("§a§lPoints : ");
        for(TeamList team_point : this.game.getTeam_point().keySet())
        {
            this.objective.getScoreboard().resetScores(team_point.getName() + " : " + ((int) this.game.getTeam_point().get(team_point) -1));
            this.scores.add(team_point.getName() + " : " + (int) this.game.getTeam_point().get(team_point));
        }
    }
}
