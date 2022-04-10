package gameplay.event;

import coliseum.core.Flag;
import game.Game;
import gameplay.event.CatchFlags;
import main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlagsArea implements Listener {

    private final Main plugin;
    private final List<Game> games_list = new ArrayList<>();
    private Game game;
    private CatchFlags catch_bar;
    private BukkitTask catch_flag;

    public FlagsArea(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFlag(PlayerMoveEvent e)
    {
        if(this.plugin.getGames_list() == null) { return; }
        this.games_list.addAll(this.plugin.getGames_list());
        this.games_list.stream()
                .filter(game_list -> game_list.getPlayer_list().containsKey(e.getPlayer().getUniqueId()) && game_list.isLaunched())
                .collect(Collectors.toList())
                .forEach(game_list -> this.game = game_list);
        if(this.game == null){ return; }


        for(Flag flag : this.game.getMap().getFlag_list())
        {
            if(e.getPlayer().getLocation().distance(flag.getFlag_location()) <= this.game.getGameCharacteristicValue("radius") + 1)
            {
                if(!flag.getPlayer_on_flag().contains(e.getPlayer().getUniqueId()))
                {
                    e.getPlayer().sendMessage("§aVous rentrez dans le drapeau : " + flag.getName());
                    this.catch_bar = new CatchFlags(this.game, flag, e.getPlayer());
                    this.catch_flag = this.catch_bar.runTaskTimer(this.plugin, 0, 20);
                    flag.getPlayer_on_flag().add(e.getPlayer().getUniqueId());
                }
                return;
            }

            if(flag.getPlayer_on_flag() != null)
            {
                if(flag.getPlayer_on_flag().contains(e.getPlayer().getUniqueId()))
                {
                    if(e.getPlayer().getLocation().distance(flag.getFlag_location()) > this.game.getGameCharacteristicValue("radius") + 1)
                    {
                        flag.getPlayer_on_flag().remove(e.getPlayer().getUniqueId());
                        e.getPlayer().sendMessage("§cVous êtes sortis du drapeau : " + flag.getName());
                        this.catch_bar.getBar().removePlayer(e.getPlayer());
                        this.catch_flag.cancel();
                        this.catch_flag = null;
                    }
                }
            }
        }
    }
}
