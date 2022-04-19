package gameplay;

import classification.TeamList;
import map.core.spawn.Spawn;
import game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {

    private final Game game;
    private List<TeamList> teams_list;

    public GamePlay(Game game)
    {
        this.game = game;
        this.teams_list = Arrays.stream(TeamList.values())
                .filter(team_list -> team_list != TeamList.RANDOM)
                .collect(Collectors.toList());
    }

    public void teleportPlayers()
    {
        Player player;
        for(UUID player_uuid : this.game.getPlayer_list().keySet())
        {
            player = Bukkit.getPlayer(player_uuid);

            for(TeamList team_list : this.teams_list)
            {
                if(this.game.getPlayer_list().get(player_uuid).getTeam() == team_list)
                {
                    Collections.shuffle(this.game.getMap().getSpawn_list());
                    for(Spawn spawn : this.game.getMap().getSpawn_list())
                    {
                        if(spawn.getTeam_color() == team_list)
                        {
                            player.teleport(spawn.getLocation());
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

}
