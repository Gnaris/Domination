package utils;

import game.Game;
import main.Main;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameUtils {

    /**
     * Get the game via a game name
     * @param games_list
     * @param game_name
     * @return
     */
    public static Game getGameByName(List<Game> games_list, String game_name)
    {
        final Game[] game = {null};
        games_list.stream()
                .filter(game_list -> game_list.getName().equalsIgnoreCase(game_name))
                .collect(Collectors.toList())
                .forEach(game_list -> game[0] = game_list);
        return game[0];
    }

    /**
     * Get the game via a player uuid
     * @param games_list
     * @param player_uuid
     * @return
     */
    public static Game getGameByPlayerUUID(List<Game> games_list, UUID player_uuid)
    {
        final Game[] game = {null};
        games_list.stream()
                .filter(game_list -> game_list.getPlayer_list().containsKey(player_uuid))
                .collect(Collectors.toList())
                .forEach(game_list -> game[0] = game_list);
        return game[0];
    }
}