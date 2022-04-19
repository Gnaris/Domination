package game;

import classification.Classification;
import classification.TeamList;
import map.Coliseum;
import map.core.flag.Flag;
import game.parent.GameCharacteristic;
import lombok.Getter;
import lombok.Setter;
import main.Main;
import main.MapConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
                .filter(TeamList::isPlayable)
                .collect(Collectors.toList())
                .forEach(team_list -> this.team_point.put(team_list, 0));
    }

    public void deleteGame()
    {
        //Teleport every player to spawn and clear inventory
        MapConfig map_config = this.plugin.getMaps_config();

        for(UUID playerUUID : this.getPlayer_list().keySet())
        {
            Bukkit.getPlayer(playerUUID).teleport(this.map.getEnd_spawn());
            Bukkit.getPlayer(playerUUID).getInventory().clear();
            Bukkit.getPlayer(playerUUID).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        //Rebuild flag
        for(Flag flag : this.map.getFlag_list())
        {
            flag.buildFlag(Material.OBSIDIAN, Material.OBSIDIAN, (int) this.getGameCharacteristicValue("radius"));
        }

        //Reset Map and set not used this map
        int i = 0;
        Coliseum map = null;
        while(i < this.plugin.getMaps_list().size() && map == null)
        {
            if(this.plugin.getMaps_list().get(i).getName().equalsIgnoreCase(this.map.getName()))
            {
                map = this.plugin.getMaps_list().get(i);
                map.setUsed(false);
            }
            i++;
        }
        //Delete Game
        this.plugin.getGames_list().remove(this);
    }
}
