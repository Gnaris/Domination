package command.controllers.classification;

import classification.KitList;
import classification.TeamList;

import command.models.classification.Model_Classification;
import command.parent.CommandController;
import game.core.GameScoreBoard;
import main.Main;
import utils.GameUtils;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Controller_Classification extends CommandController {


    public Controller_Classification(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dt [team, kit] <name>
        this.command_type = args[0];
        this.name = args[1];
        this.game = GameUtils.getGameByPlayerUUID(plugin.getGames_list(), this.sender.getUniqueId());
        if(this.game != null)
        {
            this.game_name = this.game.getName();
        }
    }

    @Override
    public void ControlCmd()
    {
        String error = null;
        error = this.game == null ? "§cVous devez d'abord rejoindre une partie" : null;
        if(error == null)
        {
            switch (this.command_type)
            {
                case "team" :
                {
                    Arrays.stream(TeamList.values())
                            .filter(team_list -> team_list.toString().toLowerCase().equalsIgnoreCase(this.name))
                            .collect(Collectors.toList())
                            .forEach(team_list -> this.team = team_list);

                    error = this.team == null ? "§cLa couleur de cette team n'existe pas" : null;
                    break;
                }

                case "kit" :
                {
                    Arrays.stream(KitList.values())
                            .filter(kit_list -> kit_list.toString().toLowerCase().equalsIgnoreCase(this.name))
                            .collect(Collectors.toList())
                            .forEach(kit_list -> this.kit = kit_list);

                    error = this.kit == null ? "§cCe kit n'existe pas" : null;
                    break;
                }
            }
        }

        if(error == null)
        {
            executeCmd();
        }
        else
        {
            this.sender.sendMessage(error);
        }
    }

    @Override
    protected void executeCmd() {
        Model_Classification model_classification = new Model_Classification(this);
        model_classification.updateConfiguration();
        new GameScoreBoard(this.game_name, this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}