package game.core;

import utils.TeamUtils;
import game.Game;
import gameplay.core.PointIncreaser;
import gameplay.core.Statistic;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Launcher extends BukkitRunnable {

    private int timer;
    private final Game game;
    private final Main plugin;

    public Launcher(Game game, Main plugin) {
        this.game = game;
        this.timer = 10;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (UUID player : this.game.getPlayer_list().keySet()) {
            Bukkit.getPlayer(player).sendTitle("§aPréparez-vous !", "§aLa partie va commencer dans " + this.timer, 0, 20, 20);
        }
        this.timer--;
        if (this.timer <= -1) {
            TeamUtils.filterTeam(this.game);
            this.game.teleportEveryone();
            new Statistic(this.game).runTaskTimer(this.plugin, 0, 20);
            new PointIncreaser(this.game).runTaskTimer(this.plugin, 0, 20);
            this.cancel();
        }
    }
}