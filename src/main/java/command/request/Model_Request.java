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
        this.sender.sendMessage("§aLa partie " + this.game_name + " a été crée avec succès");
    }

    @Override
    public void join() {
        Main.getGame(this.game_name).getPlayerList().add(this.sender.getUniqueId());
        this.sender.sendMessage("§aVous avez rejoins la partie de §e" + Main.getGame(this.game_name).getOwner());
    }

    @Override
    public void delete() {
        Main.game_list.remove(this.game_name);
        this.sender.sendMessage("§cLa partie §e" + this.game_name + " §rvient d'être supprimé");
    }

    @Override
    public void leave() {
        Main.getGame(this.game_name).getPlayerList().remove(this.sender.getUniqueId());
        this.sender.sendMessage("§cVous avez quitté la partie !");
    }

    @Override
    public void load() {

    }

    @Override
    public void invite() {

    }
}

