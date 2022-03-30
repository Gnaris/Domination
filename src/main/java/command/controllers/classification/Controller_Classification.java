package command.controllers.classification;

import classification.kit.KitList;
import classification.team.TeamList;
import command.models.classification.Model_Classification;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Controller_Classification{

    private final String command_type;
    private KitList kit_value;
    private TeamList team_value;
    private final String value;
    private final Player sender;
    private String game_name;
    private Game game;

    public Controller_Classification(String command_type, String value, Player sender) {

        this.command_type = command_type;
        this.value = value;
        this.sender = sender;
        for(Map.Entry game : Main.game_list.entrySet())
        {
            Game game_name = (Game) game.getValue();
            if(game_name.getPlayerClassification().containsKey(sender.getUniqueId()))
            {
                this.game_name = (String) game.getKey();
                this.game = Main.getGame((String) game.getKey());
            }
        }
    }

    public void checkAndExecuteCommandType()
    {
        if(this.game == null) {this.sender.sendMessage("§cVous devez tout d'abord rejoindre une partie avant de faire cela");return;}
        switch (this.command_type)
        {
            case "team" :
            {
                for(TeamList team : TeamList.values())
                {
                    if(this.value.equalsIgnoreCase(team.name().toLowerCase())) this.team_value = team; break;
                }
                if (this.team_value == null) {this.sender.sendMessage("§cLa couleur de cette team n'existe pas !");return;}
                break;
            }

            case "kit" :
            {
                for(KitList kit : KitList.values())
                {
                    if(this.value.equalsIgnoreCase(kit.name().toLowerCase())) this.kit_value = kit; break;
                }
                if (this.kit_value == null) {this.sender.sendMessage("§cCe kit n'existe pas !");return;}
                break;
            }
            default: this.sender.sendMessage("§cCommande incorrecte, veuillez faire /dt help"); return;
        }
        executeTypeCommand();
    }

    public void executeTypeCommand() {
        Model_Classification model_classification;
        switch (this.command_type)
        {
            case "team" :
                model_classification = new Model_Classification<TeamList>(this.game_name, this.sender, this.team_value);
                model_classification.updateConfiguration();
                break;

            case "kit" :
                model_classification = new Model_Classification<KitList>(this.game_name, this.sender, this.kit_value);
                model_classification.updateConfiguration();
                break;
        }
    }
}
