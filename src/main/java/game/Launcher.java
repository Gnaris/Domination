package game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Launcher extends BukkitRunnable
{

    private int timer;
    private Game game;

    public Launcher(Game game)
    {
        this.game = game;
        this.timer = 10;
    }

    @Override
    public void run()
    {
            for(UUID player : this.game.getPlayerList())
            {
                Bukkit.getPlayer(player).sendMessage("§aLa partie commençera dans : " + this.timer);
            }
            this.timer--;
            if(this.timer <= -1)
            {
                GamePlay gameplay = new GamePlay(this.game);
                gameplay.teleportPlayers();
                this.cancel();
            }
    }
}
