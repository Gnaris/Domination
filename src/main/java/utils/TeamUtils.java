package utils;

import classification.team.TeamList;
import map.core.flag.Flag;
import game.Game;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.stream.Collectors;

public class TeamUtils
{

    public static void filterTeam(Game game)
    {
        List<Integer> team_effective;
        List<TeamList> teams_list = Arrays.asList(TeamList.values()).stream().filter(TeamList::isPlayable).collect(Collectors.toList());
        List<UUID> random_players = new ArrayList<>();
        for(UUID playerUUID : game.getPlayer_list().keySet())
        {
            if(game.getPlayer_list().get(playerUUID).getTeam() == TeamList.RANDOM)
            {
                random_players.add(playerUUID);
            }
        }
        int i = 0;
        boolean assigned;
        Collections.shuffle(random_players);
        while(random_players.size() != 0)
        {
            team_effective = getTeamSizeList(game, null);
            assigned = false;
            while(i < team_effective.size() && !assigned)
            {
                for(int j = i + 1; j < team_effective.size(); j++)
                {
                        if(team_effective.get(i) == team_effective.get(j))
                        {
                            Random random = new Random();
                            int rdn = random.nextInt(teams_list.size());
                            game.getPlayer_list().get(random_players.get(0)).setTeam(teams_list.get(rdn));
                            assigned = true;
                        }
                        else if(team_effective.get(i) < team_effective.get(j))
                        {
                            game.getPlayer_list().get(random_players.get(0)).setTeam(teams_list.get(i));
                            assigned = true;
                        }
                        else if(team_effective.get(i) > team_effective.get(j))
                        {
                            game.getPlayer_list().get(random_players.get(0)).setTeam(teams_list.get(j));
                            assigned = true;
                        }
                }
                i++;
            }
            TeamList player_team_color = game.getPlayer_list().get(random_players.get(0)).getTeam();
            Bukkit.getPlayer(random_players.get(0)).sendMessage("§aOn vous a mis dans " + player_team_color.getColor() + " l'équipe " + player_team_color.getName());
            random_players.remove(0);
            i = 0;
        }
    }

    /**
     * Give total game player list per team or player list on flag area
     * @param game Game
     * @param flag If null, give game player list else player list on flag area
     * @return List
     */
    public static List<Integer> getTeamSizeList(Game game, Flag flag)
    {
        Map<TeamList, Integer> team_size = new HashMap<>();
        List<Integer> size = new ArrayList<>();

        Arrays.stream(TeamList.values())
                .filter(TeamList::isPlayable)
                .forEach(team_color -> team_size.put(team_color, 0));

        if(flag == null)
        {
            for(UUID playerUUId : game.getPlayer_list().keySet())
            {
                for(TeamList team_color : TeamList.values())
                {
                    if(game.getPlayer_list().get(playerUUId).getTeam().equals(team_color) && team_color.isPlayable())
                    {
                        team_size.put(team_color, team_size.get(team_color) + 1);
                    }
                }
            }
        }
        if(flag != null)
        {
            for(UUID playerUUId : flag.getPlayer_on_flag())
            {
                for(TeamList team_color : TeamList.values())
                {
                    if(game.getPlayer_list().get(playerUUId).getTeam().equals(team_color) && team_color.isPlayable())
                    {
                        team_size.put(team_color, team_size.get(team_color) + 1);
                    }
                }
            }
        }

        for(Map.Entry value : team_size.entrySet())
        {
            size.add((Integer) value.getValue());
        }
        return size;
    }

    public static List<UUID> getSpectatorsList(Game game)
    {
        List<UUID> spectators_list = new ArrayList<>();
        for(UUID playerUUID : game.getPlayer_list().keySet())
        {
            if(game.getPlayer_list().get(playerUUID).getTeam().equals(TeamList.SPECTATOR))
            {
                spectators_list.add(playerUUID);
            }
        }
        return spectators_list;
    }
}
