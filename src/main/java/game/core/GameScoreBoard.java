package game.core;

import classification.kit.KitList;
import classification.team.TeamList;
import game.Game;
import main.Main;
import utils.GameUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;
import java.util.stream.Collectors;

public class GameScoreBoard extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

    private final Game game;
    private final Player sender;

    public GameScoreBoard(String game_name, Player sender, Main plugin)
    {
        this.game = GameUtils.getGameByName(plugin.getGames_list(), game_name);
        this.sender = sender;
    }

    private Scoreboard openScoreBoard()
    {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        fillScore();
        return this.board;
    }

    @Override
    public void run() {
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            if(this.game.getPlayer_list().containsKey(playerUUID))
            {
                Bukkit.getPlayer(playerUUID).setScoreboard(openScoreBoard());
            }
        }
    }

    private void fillScore()
    {
        this.objective.getScore("         §a§l▶ Caractéristique ◀   ").setScore(15);
        this.objective.getScore("· Nb de Joueur : §e(" + this.game.getPlayer_list().size() + "/" + (int) this.game.getGameCharacteristicValue("player") + ")").setScore(14);
        this.objective.getScore("· Durée de la Partie : §e" + (int) this.game.getGameCharacteristicValue("time") + "s").setScore(13);
        this.objective.getScore("· Tps de Capture : §e" + (int) this.game.getGameCharacteristicValue("catchtimer") + "s").setScore(12);
        this.objective.getScore("· Vitesse de Capture : §e+" + this.game.getGameCharacteristicValue("catchspeed") + "s").setScore(11);
        this.objective.getScore("· Tps de Respawn : §e" + (int) this.game.getGameCharacteristicValue("respawntimer") + "s").setScore(10);
        this.objective.getScore("· Nombre de Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flag")).setScore(9);
        this.objective.getScore("· Rayon du Drapeau : §e" + (int) this.game.getGameCharacteristicValue("radius")).setScore(8);
        this.objective.getScore("· Objectif Point : §e" + (int) this.game.getGameCharacteristicValue("point")).setScore(7);
        this.objective.getScore("· Point par Massacre : §e" + (int) this.game.getGameCharacteristicValue("killpoint")).setScore(6);
        this.objective.getScore("· Point par Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flagpoint")).setScore(5);
        if(this.game.getMap() != null)
        {
            this.objective.getScoreboard().resetScores("            Map : §2Aucune map");
            this.objective.getScore("            Map : §2" + this.game.getMap().getName()).setScore(4);
        }
        else
        {
            this.objective.getScore("            Map : §2Aucune map").setScore(4);
        }
        int i = TeamList.values().length - 1;

        for(TeamList team_list : TeamList.values())
        {
            this.objective.getScoreboard().resetScores(team_list.getColor()+"Équipe " + team_list.getName() + " : " + (updateEffectif(team_list) - 1));
            this.objective.getScore(team_list.getColor()+"Équipe " + team_list.getName() + " : " + updateEffectif(team_list)).setScore(i);
            i--;
        }
    }

    private int updateEffectif(TeamList team_color)
    {
        int effectif = 0;
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            if(this.game.getPlayer_list().get(playerUUID).getTeam() == team_color)
            {
                effectif++;
            }
        }
        return effectif;
    }
}
