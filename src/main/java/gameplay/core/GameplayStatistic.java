package gameplay.core;

import classification.team.TeamList;
import coliseum.core.Flag;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameplayStatistic extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

    private final Game game;
    private int timer;

    public GameplayStatistic(Game game)
    {
        this.game = game;
        this.timer = (int) game.getGameCharacteristicValue("time");
    }

    private Scoreboard openScoreBoard()
    {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        Score score1 = this.objective.getScore("§a§lGameplay");
        score1.setScore(10);
        Score score2 = this.objective.getScore("");
        score2.setScore(9);
        Score score3 = this.objective.getScore("§7§lLes drapeaux :");
        score3.setScore(8);
        int i = 7;
        for(Flag flag : this.game.getMap().getFlag_list())
        {
            if(flag.isCatched())
            {
                this.objective.getScoreboard().resetScores("§6 Drapeau " + flag.getName() + " : none");
                Arrays.stream(TeamList.values())
                        .filter(team_list -> team_list != TeamList.RANDOM)
                        .collect(Collectors.toList())
                        .forEach(team_list -> this.objective.getScoreboard().resetScores("§6 Drapeau " + flag.getName() + " : " + team_list.toString().toLowerCase()));
                this.objective.getScore("§6 Drapeau " + flag.getName() + " : " + flag.getTeam_catched().toString().toLowerCase()).setScore(i);
            }
            else
            {
                this.objective.getScore("§6 Drapeau " + flag.getName() + " : none").setScore(i);
            }
            i--;
        }
        this.objective.getScore(" ").setScore(i);
        i--;
        this.objective.getScore("§a§lPoints : ").setScore(i);
        for(Map.Entry team_point : this.game.getTeam_point().entrySet())
        {
            i--;
            this.objective.getScoreboard().resetScores("§7Équipe " + team_point.getKey().toString().toLowerCase() + " : " + ((int) team_point.getValue() - 1));
            this.objective.getScore("§7Équipe " + team_point.getKey().toString().toLowerCase() + " : " + team_point.getValue()).setScore(i);

        }
        this.objective.getScore("§eTemps : " + this.timer).setScore(0);
        return this.board;
    }

    @Override
    public void run() {
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            Player player = Bukkit.getPlayer(playerUUID);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.setScoreboard(openScoreBoard());
        }
        this.objective.getScoreboard().resetScores("§eTemps : " + this.timer);
        this.timer--;
        this.objective.getScore("§eTemps : " + this.timer).setScore(0);

        if(this.timer <= -1)
        {
            this.cancel();

        }
    }
}
