package game;

import classification.Classification;
import classification.team.TeamList;
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
                .filter(team_list -> team_list.isPlayable())
                .collect(Collectors.toList())
                .forEach(team_list -> this.team_point.put(team_list, 0));
    }

    public void deleteGame()
    {
        //Teleport every player to spawn and clear inventory
        Location spawn = null;
        MapConfig map_config = this.plugin.getMaps_config();
        String world;
        double x, y, z;
        float yaw, pitch;
        for(String key : map_config.getConfig().getConfigurationSection("map." + this.map.getName() + ".spawns." + ".spawn").getKeys(false))
        {
            world = map_config.getConfig().getString("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".world");
            x = map_config.getConfig().getDouble("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".x");
            y = map_config.getConfig().getDouble("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".y");
            z = map_config.getConfig().getDouble("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".z");
            yaw = (float) map_config.getConfig().getDouble("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".yaw");
            pitch = (float) map_config.getConfig().getDouble("map." + this.map.getName() + ".spawns." + ".spawn." + key + ".pitch");
            spawn = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
        }
        for(UUID playerUUID : this.getPlayer_list().keySet())
        {
            Bukkit.getPlayer(playerUUID).teleport(spawn);
            Bukkit.getPlayer(playerUUID).getInventory().clear();
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
