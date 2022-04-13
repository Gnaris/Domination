package game;

import classification.Classification;
import classification.team.TeamList;
import coliseum.Coliseum;
import game.parent.GameCharacteristic;
import lombok.Getter;
import lombok.Setter;
import main.Main;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Game extends GameCharacteristic {

    private UUID owner;
    private String name;
    private Map<UUID, Classification> player_list = new HashMap<>();
    private Coliseum map = null;
    private Map<TeamList, Integer> team_point = new HashMap<>();
    private boolean map_loaded = false;
    private boolean launched = false;
    private Double timer = 0.0;

    public Game(String name, Player sender, Main plugin)
    {
        super(plugin);
        this.name = name;
        this.owner = sender.getUniqueId();
        Arrays.stream(TeamList.values())
                .filter(team_list -> team_list.isPlayable())
                .collect(Collectors.toList())
                .forEach(team_list -> this.team_point.put(team_list, 0));
    }
}
