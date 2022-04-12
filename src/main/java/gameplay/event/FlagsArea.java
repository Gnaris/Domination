package gameplay.event;

import coliseum.core.Flag;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlagsArea implements Listener {

    private final Main plugin;
    private final List<Game> games_started = new ArrayList<>();
    private Game player_game;

    private CatchFlags catch_bar;
    private BukkitTask catch_task;

    public FlagsArea(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Determines whether the player enters or exits the flag
     * @param e
     */
    @EventHandler
    public void onFlag(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        //Find this player game name
        if(this.plugin.getGames_list() == null) { return; }
        this.games_started.addAll(this.plugin.getGames_list());
        this.games_started.stream()
                .filter(game_list -> game_list.getPlayer_list().containsKey(player.getUniqueId()) && game_list.isLaunched())
                .collect(Collectors.toList())
                .forEach(game_list -> this.player_game = game_list);
        if(this.player_game == null){ return; }

        //Enter / Exit Flag Variable
        List<Flag> flags_list = this.player_game.getMap().getFlag_list();
        Flag flag = null;
        int i = 0;
        int area = (int) this.player_game.getGameCharacteristicValue("radius") + 1;

        while(flag == null && i < flags_list.size())
        {
            //Enter the area
            if(player.getLocation().distance(flags_list.get(i).getFlag_location()) <= area && !flags_list.get(i).getPlayer_on_flag_area().contains(player.getUniqueId()))
            {
                flag = this.player_game.getMap().getFlag_list().get(i);
                player.sendMessage("§aVous entrez dans le drapeau : " + flag.getName());
                this.catch_bar = new CatchFlags(this.player_game, flag, e.getPlayer());
                this.catch_bar.getBar().addPlayer(player);
                this.catch_task = this.catch_bar.runTaskTimer(this.plugin, 0, 20);
                flag.getPlayer_on_flag_area().add(player.getUniqueId());
            }

            //Exit the area
            if(player.getLocation().distance(flags_list.get(i).getFlag_location()) > area && flags_list.get(i).getPlayer_on_flag_area().contains(player.getUniqueId()))
            {
                flag = this.player_game.getMap().getFlag_list().get(i);
                player.sendMessage("§cVous êtes sortis du drapeau : " + flag.getName());
                this.catch_bar.getBar().removePlayer(player);
                this.catch_task.cancel();
                flag.getPlayer_on_flag_area().remove(player.getUniqueId());
            }
            i++;
        }
    }
}
