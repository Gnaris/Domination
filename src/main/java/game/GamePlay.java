package game;

import classification.Classification;
import classification.team.TeamList;
import coliseum.Flag;
import coliseum.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GamePlay {

    private final Game game;
    private final List<Spawn> spawns_list;
    private final List<Flag> flags_list;
    private final List<UUID> players_list;
    private Map<UUID, Classification> player_classification = new HashMap<>();
    private List<TeamList> teams_list;

    public GamePlay(Game game)
    {
        this.game = game;
        this.spawns_list = game.getColiseum().getSpawn_list();
        this.flags_list = game.getColiseum().getFlag_list();
        this.players_list = game.getPlayerList();
        this.player_classification = game.getPlayerClassification();
        this.teams_list = Arrays.stream(TeamList.values())
                .filter(team_list -> team_list != TeamList.RANDOM)
                .collect(Collectors.toList());
    }

    public void teleportPlayers()
    {
        Player player;
        for(UUID player_uuid : this.players_list)
        {
            player = Bukkit.getPlayer(player_uuid);

            for(TeamList team_list : this.teams_list)
            {
                if(this.player_classification.get(player_uuid).getTeam() == team_list)
                {
                    Collections.shuffle(this.spawns_list);
                    for(Spawn spawn : this.spawns_list)
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
