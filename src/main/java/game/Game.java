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
import map.core.spawn.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

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
            if(this.isLaunched())
            {
                Bukkit.getPlayer(playerUUID).teleport(this.map.getEnd_spawn());
                Bukkit.getPlayer(playerUUID).getInventory().clear();
            }
            Bukkit.getPlayer(playerUUID).setFireTicks(0);
            Bukkit.getPlayer(playerUUID).setFreezeTicks(0);
            for(PotionEffect potion : Bukkit.getPlayer(playerUUID).getActivePotionEffects())
            {
                Bukkit.getPlayer(playerUUID).removePotionEffect(potion.getType());
            }
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

    public void teleportEveryone()
    {
        for(UUID playerUUID : this.player_list.keySet())
        {
            for(TeamList color_team : TeamList.values())
            {
                if(this.player_list.get(playerUUID).getTeam().equals(color_team))
                {
                    teleportRandomSpawn(playerUUID, color_team);
                }
            }
        }
    }

    public void teleportRandomSpawn(UUID player, TeamList color_team)
    {
        int i = 0;
        Spawn spawn = null;
        Collections.shuffle(this.map.getSpawn_list());
        while(i < this.map.getSpawn_list().size() && spawn == null)
        {
            if(this.map.getSpawn_list().get(i).getTeam_color().equals(color_team))
            {
                spawn = this.map.getSpawn_list().get(i);
            }
            i++;
        }
        Bukkit.getPlayer(player).teleport(spawn.getLocation());
        if(color_team.equals(TeamList.SPECTATOR))
        {
            Bukkit.getPlayer(player).setGameMode(GameMode.SPECTATOR);
        }
    }
}
