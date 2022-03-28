package command.request;

import command.abstracts.CommandController;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class Controller_Request extends CommandController {

    private Model_Request request;
    private String game_name;

    // REQUEST
    public Controller_Request(String game_name, String type_command, Player sender)
    {
        super(game_name, type_command, sender);
        this.game_name = game_name;
    }

    @Override
    public void checkAndExecuteCommandType()
    {
        // Check the conditions according to the type of order
        switch (this.type_command) {
            case "create":
                if (Main.game_list.containsKey(this.game_name)) {this.sender.sendMessage("§cLe nom de cette partie existe déjà !");return;}
                for (Map.Entry gameList : Main.game_list.entrySet()) {this.game = (Game) gameList.getValue();if (this.game.getOwner() == this.sender.getUniqueId()) {this.sender.sendMessage("§cVous avez déjà créer une partie !");return;}}
                break;

            case "join":
                if (this.game.isLaunched()) {this.sender.sendMessage("!cLa partie que vous souhaitez rejoindre a déjà été lancée");return;}
                if (!this.game.isOpen()) {this.sender.sendMessage("§cLa partie que vous souhaitez rejoindre est en privée");return;}
                if (this.game.getPlayerList().size() >= this.game.getGameCharacteristicValue("player")) {this.sender.sendMessage("§cLa partie est remplit, vous ne pouvez plus rejoindre la partie");return;}
                if (this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("§cVous êtes déjà dans la partie !");return;}
                for (Map.Entry gameList : Main.game_list.entrySet()) {this.game = (Game) gameList.getValue();if (this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("Vous êtes déjà dans une partie !");return;}}
                break;

            case "delete":
                if (this.game.getOwner() != this.sender.getUniqueId() || !this.sender.hasPermission("domination.animator.use")) {this.sender.sendMessage("§cVous ne pouvez pas supprimer la partie des autres");return;}
                if (this.game.isLaunched() || !this.sender.hasPermission("domination.animator.use")) {this.sender.sendMessage("§cLa partie que vous souhaitez supprimer n'existe pas");return;}
                break;

            case "leave":
               if (!this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("§cVous n'avez pas encore rejoins cette partie !");return;}
                break;

            case "load":
                break;

            case "invite":
                break;

            case "help" :

            default: this.sender.sendMessage("§cCommande incorrecte, veuillez fait /dt help"); return;
        }
        executeTypeCommand();
    }

        public void executeTypeCommand()
        {
        this.request = new Model_Request(this.game_name, this.sender);

        switch(this.type_command)
        {
            case "create" : this.request.create(); break;
            case "join" : this.request.join(); break;
            case "delete" : this.request.delete(); break;
            case "leave" : this.request.leave(); break;
            case "load" : this.request.load(); break;
            case "invite" : this.request.invite(); break;
        }
    }
}