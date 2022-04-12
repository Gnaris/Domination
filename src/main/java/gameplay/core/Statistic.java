package gameplay.core;

import classification.team.TeamList;
import coliseum.core.Flag;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Statistic extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

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
        int total_score = 15;
        this.objective.getScore("        §a§l▶ Gameplay ◀").setScore(total_score);
        total_score--;
        this.objective.getScore("").setScore(total_score);
        total_score--;
        this.objective.getScore("■ Drapeaux :").setScore(total_score);
        total_score = showFlags(total_score--);
        this.objective.getScore(" ").setScore(total_score);
        total_score--;
        this.objective.getScore("■ Objectif : " + (int) this.game.getGameCharacteristicValue("point") + " points").setScore(total_score);
        total_score = showTeamScore(total_score--);
        this.objective.getScore("  ").setScore(total_score);
        return this.board;
    }

    @Override
    public void run() {

        //Give scoreboard to every player on game
        for (UUID playerUUID : this.game.getPlayer_list().keySet()) {
            Player player = Bukkit.getPlayer(playerUUID);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.setScoreboard(openScoreBoard());
        }

        //Update game timer
        DecimalFormat decimal_format = new DecimalFormat("###");
        this.objective.getScoreboard().resetScores("§eFin de la partie dans : -1s");
        this.objective.getScoreboard().resetScores("§eFin de la partie dans : 0s");
        this.objective.getScoreboard().resetScores("§eFin de la partie dans : " + decimal_format.format((this.game.getTimer())) + "s");
        this.objective.getScoreboard().resetScores("§eFin de la partie dans : " + decimal_format.format((this.game.getTimer() + 1)) + "s");
        this.game.setTimer(this.game.getTimer() - 1);
        this.objective.getScore("§eFin de la partie dans : " + decimal_format.format(this.game.getTimer()) + "s").setScore(0);

        if (this.game.getTimer() <= 0) {
            this.cancel();
        }
    }

    private int showFlags(int total_score)
    {
        int score = total_score;
        for (Flag flag : this.game.getMap().getFlag_list())
        {
            if (flag.isCatched())
            {
                this.objective.getScoreboard().resetScores("§6→ " + flag.getName() + " : none");
                Arrays.stream(TeamList.values())
                        .filter(team_list -> team_list != TeamList.RANDOM)
                        .collect(Collectors.toList())
                        .forEach(team_list -> this.objective.getScoreboard().resetScores("§6→ " + flag.getName() + " : " + team_list.getName()));
                this.objective.getScore("§6→ " + flag.getName() + " : " + flag.getTeam_catched().getName()).setScore(score);
            }
            else
            {
                this.objective.getScore("§6→ " + flag.getName() + " : none").setScore(score);
            }
            score--;
        }
        return score;
    }

    private int showTeamScore(int total_score)
    {
        int score = total_score;
        for (Map.Entry team_point : this.game.getTeam_point().entrySet())
        {
            TeamList team_color = (TeamList) team_point.getKey();
            for(int i = 0; i < this.game.getMap().getFlag_list().size() + 1; i++)
            {
                this.objective.getScoreboard().resetScores(team_color.getName() + " : " + ((int) team_point.getValue() - i));
            }
            this.objective.getScore(team_color.getName() + " : " + team_point.getValue()).setScore(score);
            score--;
        }
        return score;
    }
}