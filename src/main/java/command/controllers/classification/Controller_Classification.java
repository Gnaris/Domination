package command.controllers.classification;

import classification.kit.KitList;
import classification.team.TeamList;
import command.models.classification.Model_Classification;
import command.parent.CommandController;
import game.Game;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Controller_Classification extends CommandController{

    private TeamList team;
    private KitList kit;
    private final String value;

    public Controller_Classification(String command_type, Player sender, Main plugin, String value) {
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

        this.value = value;
    }


    @Override
    public void checkAndExecuteCommand()
    {
        if(this.game == null) {this.sender.sendMessage("§cVous devez tout d'abord rejoindre une partie avant de faire cela");return;}
        switch (this.command_type)
        {
            case "team" :
            {
                for(TeamList team : TeamList.values())
                {
                    if(this.value.equalsIgnoreCase(team.name().toLowerCase())){ this.team = team; break; }
                }
                if (this.team == null) {this.sender.sendMessage("§cLa couleur de cette team n'existe pas !");return;}
                break;
            }

            case "kit" :
            {
                for(KitList kit : KitList.values())
                {
                    if(this.value.equalsIgnoreCase(kit.name().toLowerCase())){ this.kit = kit; break; }
                }
                if (this.kit == null) {this.sender.sendMessage("§cCe kit n'existe pas !");return;}
                break;
            }
            default: this.sender.sendMessage("§cCommande incorrecte, veuillez faire /dt help"); return;
        }
        executeCommand();
    }

    @Override
    public void executeCommand() {
        Model_Classification model_classification;
        switch (this.command_type)
        {
            case "team" :
                model_classification = new Model_Classification<TeamList>(this.game_name, this.sender, this.team, this.plugin);
                model_classification.updateConfiguration();
                break;

            case "kit" :
                model_classification = new Model_Classification<KitList>(this.game_name, this.sender, this.kit, this.plugin);
                model_classification.updateConfiguration();
                break;
        }
        new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}
