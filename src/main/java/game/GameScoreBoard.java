package game;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.Map;
import java.util.UUID;

public class GameScoreBoard extends BukkitRunnable {

    private ScoreboardManager manager = Bukkit.getScoreboardManager();
    private Scoreboard board = manager.getNewScoreboard();
    private Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

    private Game game;
    private Player sender;

    public GameScoreBoard(Player sender)
    {
        this.sender = sender;
        for(Map.Entry game_list : Main.game_list.entrySet())
        {
            Game game = (Game) game_list.getValue();
            if(game.getPlayerList().contains(sender.getUniqueId()))
            {
                this.game = game;
                break;
            }
        }
    }

    private Scoreboard openScoreBoard()
    {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        Score score14 = this.objective.getScore("        §a§lCaractéristique     ");
        score14.setScore(14);
        Score score13 = this.objective.getScore("§7Nombre de joueur : §e(" + this.game.getPlayerList().size() + "/" + this.game.getGameCharacteristicValue("player") + ")");
        score13.setScore(13);
        Score score12 = this.objective.getScore("§7Durée de la partie : §e" + this.game.getGameCharacteristicValue("time") + "s");
        score12.setScore(12);
        Score score11 = this.objective.getScore("§7Durée de la capture : §e" + this.game.getGameCharacteristicValue("catchtimer") + "s");
        score11.setScore(11);
        Score score10 = this.objective.getScore("§7Capture Accélérée : §e+" + this.game.getGameCharacteristicValue("catchspeed") + "s");
        score10.setScore(10);
        Score score9 = this.objective.getScore("§7Temps de Respawn : §e" + this.game.getGameCharacteristicValue("respawntimer") + "s");
        score9.setScore(9);
        Score score8 = this.objective.getScore("§7Nombre drapeau : §e" + this.game.getGameCharacteristicValue("flag"));
        score8.setScore(8);
        Score score7 = this.objective.getScore("§7Rayon du drapeau : §e" + this.game.getGameCharacteristicValue("radius"));
        score7.setScore(7);
        Score score6 = this.objective.getScore("§7Spawn par équipe : §e" + this.game.getGameCharacteristicValue("spawn"));
        score6.setScore(6);
        Score score5 = this.objective.getScore("§7Objectif Point : §e" + this.game.getGameCharacteristicValue("point"));
        score5.setScore(5);
        Score score4 = this.objective.getScore("§7Point par joueur tuer : §e" + this.game.getGameCharacteristicValue("killpoint"));
        score4.setScore(4);
        Score score3 = this.objective.getScore("§7Point par drapeau : §e" + this.game.getGameCharacteristicValue("flagpoint"));
        score3.setScore(3);
        Score score2 = this.objective.getScore("§7Map : §e" + "AUCUNE");
        score2.setScore(2);
        Score score1 = this.objective.getScore("§aVotre équipe : §6§l" + this.game.getPlayerClassification().get(this.sender.getUniqueId()).getTeam().name().substring(0,1).toUpperCase() + this.game.getPlayerClassification().get(this.sender.getUniqueId()).getTeam().name().substring(1).toLowerCase());
        score1.setScore(1);
        Score score0 = this.objective.getScore("§aVotre kit : §6§l" + this.game.getPlayerClassification().get(this.sender.getUniqueId()).getKit().name().substring(0,1).toUpperCase() + this.game.getPlayerClassification().get(this.sender.getUniqueId()).getKit().name().substring(1).toLowerCase());
        score0.setScore(0);
        return this.board;
    }

    @Override
    public void run() {
        for(UUID playerUUID : this.game.getPlayerList())
        {
            Player player = Bukkit.getPlayer(playerUUID);
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            player.setScoreboard(openScoreBoard());
        }
    }
}