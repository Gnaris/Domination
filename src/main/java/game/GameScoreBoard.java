package game;

import classification.team.TeamList;
import main.Main;
import main.utils.GameRecuperator;
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
    private final List<String> score = new ArrayList<>();

    public GameScoreBoard(Player sender, Main plugin)
    {
        this.game = GameRecuperator.byPlayer_Name(plugin.getGames_list(), sender.getUniqueId());

        this.score.add("         §a§l▶ Caractéristique ◀   ");
        this.score.add("· Nb de Joueur : §e(" + this.game.getPlayer_list().size() + "/" + (int) this.game.getGameCharacteristicValue("player") + ")");
        this.score.add("· Durée de la Partie : §e" + (int) this.game.getGameCharacteristicValue("time") + "s");
        this.score.add("· Tps de Capture : §e" + (int) this.game.getGameCharacteristicValue("catchtimer") + "s");
        this.score.add("· Vitesse de Capture : §e+" + this.game.getGameCharacteristicValue("catchspeed") + "s");
        this.score.add("· Tps de Respawn : §e" + (int) this.game.getGameCharacteristicValue("respawntimer") + "s");
        this.score.add("· Nombre de Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flag"));
        this.score.add("· Rayon du Drapeau : §e" + (int) this.game.getGameCharacteristicValue("radius"));
        this.score.add("· Objectif Point : §e" + (int) this.game.getGameCharacteristicValue("point"));
        this.score.add("· Point par Massacre : §e" + (int) this.game.getGameCharacteristicValue("killpoint"));
        this.score.add("· Point par Drapeau : §e" + (int) this.game.getGameCharacteristicValue("flagpoint"));
        int red = 0;
        int blue = 0;
        int random = 0;
        for(UUID player : this.game.getPlayer_list().keySet())
        {
            if(this.game.getPlayer_list().get(player).getTeam() == TeamList.RED)
            {
                red++;
            }
            if(this.game.getPlayer_list().get(player).getTeam() == TeamList.BlUE)
            {
                blue++;
            }
            if(this.game.getPlayer_list().get(player).getTeam() == TeamList.RANDOM)
            {
                random++;
            }
        }
        this.score.add("                  §c" + red + " §7| §1" + blue + " §7| §d" + random);
        if(this.game.getMap() != null)
        {
            this.score.add("            Map : §2" + this.game.getMap().getName());
        }
        else
        {
            this.score.add("            Map : §2Aucune map");
        }
        this.score.add("      §aVotre équipe : §6" + this.game.getPlayer_list().get(sender.getUniqueId()).getTeam().name().substring(0,1).toUpperCase() + this.game.getPlayer_list().get(sender.getUniqueId()).getTeam().name().substring(1).toLowerCase());
        this.score.add("          §aVotre kit : §6" + this.game.getPlayer_list().get(sender.getUniqueId()).getKit().name().substring(0,1).toUpperCase() + this.game.getPlayer_list().get(sender.getUniqueId()).getKit().name().substring(1).toLowerCase());
    }

    private Scoreboard openScoreBoard()
    {
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName("§c§lDOMINATION");
        int size = this.score.size();
        for(int i = 0; i < this.score.size(); i++)
        {
            this.objective.getScore(this.score.get(i)).setScore(size);
            size--;
        }
        return this.board;
    }

    @Override
    public void run() {
        for(UUID playerUUID : this.game.getPlayer_list().keySet())
        {
            Bukkit.getPlayer(playerUUID).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            Bukkit.getPlayer(playerUUID).setScoreboard(openScoreBoard());
        }
    }
}
