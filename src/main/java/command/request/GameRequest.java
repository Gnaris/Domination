package command.request;

import game.Game;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameRequest implements RequestInterface {

    private String game_name;
    private Player sender;

    public GameRequest(String game_name, Player sender)
    {
        this.game_name = game_name;
        this.sender = sender;
    }

    @Override
    public void create() {
        Main.game_list.put(this.game_name,new Game(this.sender));
        Bukkit.broadcastMessage("OUI");
    }

    @Override
    public void join() {
       Game game = Main.game_list.get(this.game_name);
    }

    @Override
    public void delete() {

    }

    @Override
    public void leave() {

    }

    @Override
    public void load() {

    }

    @Override
    public void invite() {

    }
}
