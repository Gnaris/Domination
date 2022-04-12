package game.core;

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

public class GameScoreBoard extends BukkitRunnable {

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("GameScoreboard", "dummy");

    private final Game game;
    private final List<String> scores = new ArrayList<>();
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
        int size = this.scores.size();
        for(int i = 0; i < this.scores.size(); i++)
        {
            this.objective.getScore(this.scores.get(i)).setScore(size);
            size--;
        }
        return this.board;
    }

    @Override
    public void run() {
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            if(this.game.getPlayer_list().containsKey(playerUUID))
            {
                fillScore(playerUUID);
                Bukkit.getPlayer(playerUUID).setScoreboard(openScoreBoard());
            }
            this.scores.clear();
        }
    }

    private void fillScore(UUID player)
    {
        this.scores.add("         §a§l▶ Caractéristique ◀   ");
        this.scores.add("· Nb de Joueur : §e(" + this.game.getPlayer_list().size() + "/" + (int) this.game.getGameCharacteristicValue("player") + ")");
        this.scores.add("· Durée de la Partie : §e" + (int) this.game.getGameCharacteristicValue("time") + "s");
        this.scores.add("· Tps de Capture : §e" + (int) this.game.getGameCharacteristicValue("catchtimer") + "s");
        this.scores.add("· Vitesse de Capture : §e+" + this.game.getGameCharacteristicValue("catchspeed") + "s");
        this.scores.add("· Tps de Respawn : §e" + (int) this.game.getGameCharacteristicValue("respawntimer") + "s");
        this.scores.add("· Nombre de Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flag"));
        this.scores.add("· Rayon du Drapeau : §e" + (int) this.game.getGameCharacteristicValue("radius"));
        this.scores.add("· Objectif Point : §e" + (int) this.game.getGameCharacteristicValue("point"));
        this.scores.add("· Point par Massacre : §e" + (int) this.game.getGameCharacteristicValue("killpoint"));
        this.scores.add("· Point par Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flagpoint"));
        int red = 0;
        int blue = 0;
        int random = 0;
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            if(this.game.getPlayer_list().get(playerUUID).getTeam() == TeamList.RED)
            {
                red++;
            }
            if(this.game.getPlayer_list().get(playerUUID).getTeam() == TeamList.BlUE)
            {
                blue++;
            }
            if(this.game.getPlayer_list().get(playerUUID).getTeam() == TeamList.RANDOM)
            {
                random++;
            }
        }
        this.scores.add("                  §c" + red + " §7| §1" + blue + " §7| §d" + random);
        if(this.game.getMap() != null)
        {
            this.scores.add("            Map : §2" + this.game.getMap().getName());
        }
        else
        {
            this.scores.add("            Map : §2Aucune map");
        }
        this.scores.add("      §aVotre équipe : " + this.game.getPlayer_list().get(player).getTeam().getName());
        this.scores.add("          §aVotre kit : §6" + this.game.getPlayer_list().get(player).getKit().getName());
    }
}
