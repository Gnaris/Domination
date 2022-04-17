package gameplay.event;

import classification.Classification;
import classification.team.TeamList;
import map.core.flag.Flag;
import map.core.flag.FlagStatus;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import utils.GameUtils;
import utils.TeamUtils;

import java.util.*;

public class FlagsArea implements Listener{

    private final Main plugin;
    private Game game;

    private CatchFlags catch_bar;
    private BukkitTask catch_task;

    public FlagsArea(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFlag(PlayerMoveEvent e)
    {
        if(this.plugin.getGames_list() == null) {return;}
        Player player = e.getPlayer();
        this.game  = GameUtils.getGameByPlayerUUID(this.plugin.getGames_list(), player.getUniqueId());
        if(this.game == null || !this.game.isLaunched()){ return; }

        //Define variable / shortcut variable
        Flag flag = null;
        int i = 0;

        List<Flag> flags_list = this.game.getMap().getFlag_list();
        Map<UUID, Classification> players_list = this.game.getPlayer_list();
        int area = (int) this.game.getGameCharacteristicValue("radius") + 1; // +1 (beacon)

        while(flag == null && i < flags_list.size())
        {
            //Enter the area
            if(player.getLocation().distance(flags_list.get(i).getFlag_location()) <= area)
            {
                flag = this.game.getMap().getFlag_list().get(i);
                if(!flags_list.get(i).getPlayer_on_flag().contains(player.getUniqueId()))
                {
                    player.sendMessage("§aVous entrez dans le drapeau : " + flag.getName());
                    flag.getPlayer_on_flag().add(player.getUniqueId());
                }

                flag.setStatus(checkConflict(flag));
                if(flag.getStatus() != FlagStatus.CONFLICT)
                {
                    TeamList player_team_color = players_list.get(player.getUniqueId()).getTeam();
                    if(flag.getTeam_catched() != player_team_color)
                    {
                        if(flag.getPlayer_capturing_list().get(players_list.get(player.getUniqueId()).getTeam()) == null)
                        {
                            this.catch_bar = new CatchFlags(this.game, flag, e.getPlayer());
                            this.catch_bar.getBar().addPlayer(player);
                            this.catch_task = this.catch_bar.runTaskTimer(this.plugin, 0, 20);
                            flag.getPlayer_capturing_list().put(players_list.get(player.getUniqueId()).getTeam(), player.getUniqueId());
                            flag.setStatus(FlagStatus.PROGRESS);
                        }
                    }
                }
            }

            //Exit the area
            if(player.getLocation().distance(flags_list.get(i).getFlag_location()) > area && flags_list.get(i).getPlayer_on_flag().contains(player.getUniqueId()))
            {
                flag = this.game.getMap().getFlag_list().get(i);
                player.sendMessage("§cVous êtes sortis du drapeau : " + flag.getName());
                if(this.catch_task != null)
                {
                    if(this.catch_bar.getBar().getPlayers().contains(player))
                    {
                        this.catch_bar.getBar().removePlayer(player);
                        this.catch_task.cancel();
                        this.catch_task = null;
                        flag.getPlayer_capturing_list().put(players_list.get(player.getUniqueId()).getTeam(), null);
                    }
                }
                flag.getPlayer_on_flag().remove(player.getUniqueId());
            }
            i++;
        }
    }

    private FlagStatus checkConflict(Flag flag)
    {
        //fill variable
        List<Integer> numbers_players_list = TeamUtils.getTeamSizeList(this.game, flag);
        FlagStatus status = FlagStatus.NONE;
        int i = 0;
        do{
            for(int j = i + 1; j < numbers_players_list.size(); j++)
            {
                if(numbers_players_list.get(i) == numbers_players_list.get(j))
                {
                    status = FlagStatus.CONFLICT;
                }
            }
            i++;
        }while (status != FlagStatus.NONE && i < numbers_players_list.size());

        return status;
    }
}
