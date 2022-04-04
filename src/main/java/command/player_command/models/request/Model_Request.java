package command.player_command.models.request;

import classification.Classification;
import classification.kit.KitList;
import classification.team.TeamList;
import command.player_command.parent.CommandModel;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

public class Model_Request extends CommandModel {

    public Model_Request(String game_name, Player sender, Main plugin)
    {
        super(game_name, sender, plugin);
    }

    public void create() {
        this.plugin.getGame_list().put(this.game_name, new Game(this.game_name, this.sender, this.plugin));
    }

    public void join() {
        this.game.getPlayerClassification().put(this.sender.getUniqueId(), new Classification(KitList.INCOGNITO, TeamList.RANDOM));
        this.game.getPlayerList().add(this.sender.getUniqueId());
    }

    public void delete() {
        this.plugin.getGame_list().remove(this.game_name);
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

