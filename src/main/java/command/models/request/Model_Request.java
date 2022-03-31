package command.models.request;

import classification.Classification;
import classification.kit.KitList;
import classification.team.TeamList;
import command.parent.CommandModel;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public class Model_Request extends CommandModel {

    public Model_Request(String game_name, Player sender, Main config)
    {
        super(game_name, sender, config);
    }

    public void create() {
        Main.game_list.put(this.game_name,new Game(this.sender, this.config));
    }

    public void join() {
        this.game.getPlayerClassification().put(this.sender.getUniqueId(), new Classification(KitList.INCOGNITO, TeamList.RANDOM));
        this.game.getPlayerList().add(this.sender.getUniqueId());
    }

    public void delete() {
        Main.game_list.remove(this.game_name);
    }

    public void leave() {
        this.game.getPlayerList().remove(this.sender.getUniqueId());
    }

    public void load() {

    }

    public void invite() {

    }

    public void help()
    {

    }
}
