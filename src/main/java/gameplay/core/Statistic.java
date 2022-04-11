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

public class Statistic extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

    private final Game game;
    private int timer;

    public Statistic(Game game) {
        this.game = game;
        this.timer = (int) game.getGameCharacteristicValue("time");
    }

    private Scoreboard openScoreBoard() {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        int total_score = 9;
        this.objective.getScore("      §a§lGameplay").setScore(total_score);
        total_score--;
        this.objective.getScore("").setScore(total_score);
        total_score--;
        this.objective.getScore("§7§lLes drapeaux :").setScore(total_score);
        total_score--;
        for (Flag flag : this.game.getMap().getFlag_list())
        {
            if (flag.isCatched())
            {
                this.objective.getScoreboard().resetScores("§6Drapeau " + flag.getName() + " : none");
                Arrays.stream(TeamList.values())
                        .filter(team_list -> team_list != TeamList.RANDOM)
                        .collect(Collectors.toList())
                        .forEach(team_list -> this.objective.getScoreboard().resetScores("§6Drapeau " + flag.getName() + " : " + team_list.getName()));
                this.objective.getScore("§6Drapeau " + flag.getName() + " : " + flag.getTeam_catched().getName()).setScore(total_score);
            }
            else
            {
                this.objective.getScore("§6Drapeau " + flag.getName() + " : none").setScore(total_score);
            }
            total_score--;
        }
        this.objective.getScore(" ").setScore(total_score);
        total_score--;
        this.objective.getScore("§a§lPoints : ").setScore(total_score);
        total_score--;
        for (Map.Entry team_point : this.game.getTeam_point().entrySet())
        {
            TeamList team_color = (TeamList) team_point.getKey();
            this.objective.getScoreboard().resetScores(team_color.getName() + " : " + ((int) team_point.getValue() - 1));
            this.objective.getScore(team_color.getName() + " : " + team_point.getValue()).setScore(total_score);
            total_score--;
        }
        this.objective.getScore("§eTemps : " + this.timer).setScore(total_score);
        return this.board;
    }

    @Override
    public void run() {
        for (UUID playerUUID : this.game.getPlayer_list().keySet()) {
            Player player = Bukkit.getPlayer(playerUUID);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.setScoreboard(openScoreBoard());
        }
        this.objective.getScoreboard().resetScores("§eTemps : " + this.timer);
        this.timer--;
        this.objective.getScore("§eTemps : " + this.timer).setScore(0);

        if (this.timer <= -1) {
            this.cancel();
        }
    }
}