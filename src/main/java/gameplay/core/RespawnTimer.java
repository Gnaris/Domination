package gameplay.core;

import game.Game;
import gameplay.event.Death;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class RespawnTimer extends BukkitRunnable {

    private UUID victim;
    private Death death;
    private int respawntimer;
    private Game game;

    public RespawnTimer(UUID victim, Death death, Game game)
    {
        this.victim = victim;
        this.death = death;
        this.respawntimer = (int) game.getGameCharacteristicValue("respawntimer");
        this.game = game;
    }

    @Override
    public void run() {
        boolean spawned = false;
        Bukkit.getPlayer(this.victim).setFireTicks(0);
        Bukkit.getPlayer(this.victim).setFreezeTicks(0);
        for(PotionEffect potion : Bukkit.getPlayer(this.victim).getActivePotionEffects())
        {
            Bukkit.getPlayer(this.victim).removePotionEffect(potion.getType());
        }
        for(PotionEffect potion : Bukkit.getPlayer(this.victim).getActivePotionEffects())
        {
            Bukkit.getPlayer(this.victim).removePotionEffect(potion.getType());
        }
        if((this.respawntimer <= 0 || this.game == null) && !spawned)
        {
            this.death.getDeaths_list().remove(this.victim);
            Bukkit.getPlayer(this.victim).setInvisible(false);
            spawned = true;
        }
        else
        {
            Bukkit.getPlayer(this.victim).sendTitle("§cVous avez été décapité", "§cRéapparaition dans " + this.respawntimer, 0, 20, 40);
        }
        if((this.respawntimer <= 5 || this.game == null) && spawned)
        {
            Bukkit.getPlayer(this.victim).setInvulnerable(false);
            this.cancel();
        }
        this.respawntimer--;
    }
}
