package gameplay.event;

import classification.TeamList;
import game.Game;
import gameplay.core.RespawnTimer;
import lombok.Getter;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import utils.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Death implements Listener {

    private final Main plugin;
    private final List<UUID> deaths_list = new ArrayList<>();

    public Death(Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void Hitted(EntityDamageByEntityEvent e)
    {
        if(this.plugin.getGames_list() == null) return;
        if(!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player)) return;
        Player victim = (Player) e.getEntity();
        Player aggressor = (Player) e.getDamager();
        Game game = GameUtils.getGameByPlayerUUID(plugin.getGames_list(), victim.getUniqueId());
        if(game == null || !game.isLaunched()) {return;}
        TeamList victim_team_color = game.getPlayer_list().get(victim.getUniqueId()).getTeam();
        TeamList aggressor_team_color = game.getPlayer_list().get(aggressor.getUniqueId()).getTeam();
        if(victim_team_color == aggressor_team_color || this.deaths_list.contains(victim.getUniqueId())){ e.setCancelled(true); return;}
        if(victim.getHealth() - e.getDamage() <= 0)
        {
            e.setCancelled(true);
            victim.setHealth(20);
            game.teleportRandomSpawn(victim.getUniqueId(), game.getPlayer_list().get(victim.getUniqueId()).getTeam());
            this.deaths_list.add(victim.getUniqueId());
            new RespawnTimer(victim.getUniqueId(), this, game).runTaskTimer(this.plugin, 0, 20);
            victim.setInvisible(true);
            victim.setInvulnerable(true);
            victim.setFireTicks(0);
            victim.setFreezeTicks(0);
            for(PotionEffect potion : victim.getActivePotionEffects())
            {
                victim.removePotionEffect(potion.getType());
            }

            // ADD point for aggressor team
            int point_per_kill = (int) game.getGameCharacteristicValue("killpoint");
            game.getTeam_point().put(aggressor_team_color, game.getTeam_point().get(aggressor_team_color) + point_per_kill);
        }
    }

    @EventHandler
    public void HittedByOther(EntityDamageEvent e)
    {
        if(this.plugin.getGames_list() == null) return;
        if(!(e.getEntity() instanceof Player)) return;
        Player victim = (Player) e.getEntity();
        Game game = GameUtils.getGameByPlayerUUID(plugin.getGames_list(), victim.getUniqueId());
        if(game == null || !game.isLaunched()) {return;}
        if(this.deaths_list.contains(victim.getUniqueId())){ e.setCancelled(true); return;}
        if(victim.getHealth() - e.getDamage() <= 0)
        {
            e.setCancelled(true);
            victim.setHealth(20);
            game.teleportRandomSpawn(victim.getUniqueId(), game.getPlayer_list().get(victim.getUniqueId()).getTeam());
            this.deaths_list.add(victim.getUniqueId());
            new RespawnTimer(victim.getUniqueId(), this, game).runTaskTimer(this.plugin, 0, 20);
            victim.setInvisible(true);
            victim.setInvulnerable(true);
            victim.setFireTicks(0);
            victim.setFreezeTicks(0);
            for(PotionEffect potion : victim.getActivePotionEffects())
            {
                victim.removePotionEffect(potion.getType());
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        if(this.deaths_list.contains(e.getPlayer().getUniqueId()))e.setCancelled(true);
    }
}
