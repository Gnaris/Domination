package main.utils;

import game.Game;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameRecuperator {

    public static Game byGame_Name(List<Game> games_list, String game_name)
    {
        final Game[] game = {null};
        games_list.stream()
                .filter(game_list -> game_list.getName().equalsIgnoreCase(game_name))
                .collect(Collectors.toList())
                .forEach(game_list -> game[0] = game_list);
        return game[0];
    }

    public static Game byPlayer_Name(List<Game> games_list, UUID player_uuid)
    {
        final Game[] game = {null};
        games_list.stream()
                .filter(game_list -> game_list.getPlayer_list().containsKey(player_uuid))
                .collect(Collectors.toList())
                .forEach(game_list -> game[0] = game_list);
        return game[0];
    }
}