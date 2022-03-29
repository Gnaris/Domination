package command.request;

import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public class Model_Request implements Interface_Request {

    private final String game_name;
    private final Player sender;

    public Model_Request(String game_name, Player sender)
    {
        this.game_name = game_name;
        this.sender = sender;
    }

    @Override
    public void create() {
        Main.game_list.put(this.game_name,new Game(this.sender));
    }

    @Override
    public void join() {
        Main.getGame(this.game_name).getPlayerList().add(this.sender.getUniqueId());
    }

    @Override
    public void delete() {
        Main.game_list.remove(this.game_name);
    }

    @Override
    public void leave() {
        Main.getGame(this.game_name).getPlayerList().remove(this.sender.getUniqueId());
    }

    @Override
    public void load() {

    }

    @Override
    public void invite() {

    }
}

