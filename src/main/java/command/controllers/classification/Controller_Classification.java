package command.controllers.classification;

import classification.kit.KitList;
import classification.team.TeamList;
import command.models.classification.Model_Classification;
import command.parent.CommandController;
import game.Game;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller_Classification extends CommandController{

    private TeamList team;
    private KitList kit;
    private final String value_team_kit;

    public Controller_Classification(String command_type, String value_team_kit, Player sender, Main plugin) {
        super(command_type, sender, plugin);

        for(Map.Entry game : plugin.getGame_list().entrySet())
        {
            Game game_name = (Game) game.getValue();
            if(game_name.getPlayerClassification().containsKey(sender.getUniqueId()))
            {
                this.game_name = (String) game.getKey();
                this.game = plugin.getGame_list().get((String) game.getKey());
                break;
            }
        }

        this.value_team_kit = value_team_kit;
    }


    @Override
    public void ControlCmd()
    {
        if(this.game == null)
        {
            this.sender.sendMessage("§cVous devez tout d'abord rejoindre une partie avant de faire cela");
            return;
        }

        switch (this.command_type)
        {
            case "team" :
            {
                Arrays.stream(TeamList.values())
                        .filter(team_list -> team_list.toString().toLowerCase().equalsIgnoreCase(this.value_team_kit))
                        .collect(Collectors.toList())
                        .forEach(team_list -> this.team = team_list);

                if (this.team == null)
                {
                    this.sender.sendMessage("§cLa couleur de cette team n'existe pas !");
                    return;
                }
                break;
            }

            case "kit" :
            {
                Arrays.stream(KitList.values())
                        .filter(kit_list -> kit_list.toString().toLowerCase().equalsIgnoreCase(this.value_team_kit))
                        .collect(Collectors.toList())
                        .forEach(kit_list -> this.kit = kit_list);

                if (this.kit == null)
                {
                    this.sender.sendMessage("§cCe kit n'existe pas !");
                    return;
                }
                break;
            }
        }

        executeCmd();
    }

    @Override
    protected void executeCmd() {
        Model_Classification model_classification = null;
        switch (this.command_type)
        {
            case "team" :
                model_classification = new Model_Classification<>(this.game_name, this.sender, this.team, this.plugin);
                break;

            case "kit" :
                model_classification = new Model_Classification<>(this.game_name, this.sender, this.kit, this.plugin);
                break;
        }

        assert model_classification != null;
        model_classification.updateConfiguration();
        new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}