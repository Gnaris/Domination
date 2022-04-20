package gameplay.event;

import classification.TeamList;
import game.Game;
import main.Main;
import map.core.spawn.Spawn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import utils.GameUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Respawn implements Listener {

    private final Main plugin;
    private final Map<UUID, Long> deaths_list = new  HashMap<>();
    private final Map<UUID, Long> anti_spam = new HashMap<>();

    public Respawn(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRespawn(EntityDamageByEntityEvent e)
    {
        if(this.plugin.getGames_list() == null)
        {
            return;
        }
        if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player))
        {
            return;
        }
        Player victim = (Player) e.getEntity();
        Player aggressor = (Player) e.getDamager();
        Game game = GameUtils.getGameByPlayerUUID(plugin.getGames_list(), victim.getUniqueId());
        if(game == null || !game.isLaunched())
        {
            return;
        }

        TeamList victim_team_color = game.getPlayer_list().get(victim.getUniqueId()).getTeam();
        TeamList aggressor_team_color = game.getPlayer_list().get(aggressor.getUniqueId()).getTeam();
        if(victim_team_color == aggressor_team_color)
        {
            e.setCancelled(true);
        }
        if(victim.getHealth() - e.getDamage() <= 0)
        {
            e.setCancelled(true);
            victim.setHealth(20);
            game.teleportRandomSpawn(victim.getUniqueId(), victim_team_color);
            this.deaths_list.put(victim.getUniqueId(), System.currentTimeMillis() / 1000);
            victim.sendMessage("§aVous avez été tué par " + victim.getKiller().getName());

            // ADD point for aggressor team
            int point_per_kill = (int) game.getGameCharacteristicValue("killpoint");
            game.getTeam_point().put(aggressor_team_color, game.getTeam_point().get(aggressor_team_color) + point_per_kill);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        if(!this.deaths_list.containsKey(player.getUniqueId())) return;
            Game game = GameUtils.getGameByPlayerUUID(this.plugin.getGames_list(), player.getUniqueId());
            int respawn_timer = (int) game.getGameCharacteristicValue("respawntimer");
            long time_left = (this.deaths_list.get(player.getUniqueId()) - System.currentTimeMillis() / 1000) + respawn_timer;
            if(time_left > 0)
            {
                e.setCancelled(true);
                if(!this.anti_spam.containsKey(player.getUniqueId()) || (this.anti_spam.get(player.getUniqueId()) - System.currentTimeMillis() / 1000) + 1 <= 0)
                {
                    player.sendMessage("Respawn dans " + time_left + " secondes");
                    this.anti_spam.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
                }
            }
            else
            {
                e.setCancelled(false);
                this.deaths_list.remove(player.getUniqueId());
            }
    }
}
