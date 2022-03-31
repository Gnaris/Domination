package command.controllers.request;

import command.parent.CommandController;
import command.models.request.Model_Request;
import game.Game;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;

public class Controller_Request extends CommandController{

    public Controller_Request(String game_name, String command_type, Player sender, Main config) {
        super(game_name, command_type, sender, config);
    }

    public Controller_Request(String command_type, Player sender, Main config)
    {
        super(command_type, sender, config);
    }

    @Override
    public void checkAndExecuteCommand()
    {
        this.sender.sendMessage("Lol");
        if(this.game == null || !this.command_type.equalsIgnoreCase("create")) {this.sender.sendMessage("§cLe nom de la partie est incorrecte ou inexistant"); return; }
        switch (this.command_type)
        {

            case "create":
            {
                if (Main.game_list.containsKey(this.game_name)) {this.sender.sendMessage("§cLe nom de cette partie existe déjà !");return;}
                for (Map.Entry gameList : Main.game_list.entrySet()) {this.game = (Game) gameList.getValue();if (this.game.getOwner() == this.sender.getUniqueId()) {this.sender.sendMessage("§cVous avez déjà créer une partie !");return;}}
                break;
            }


            case "join":
            {
                if (this.game.isLaunched()) {this.sender.sendMessage("!cLa partie que vous souhaitez rejoindre a déjà été lancée");return;}
                if (!this.game.isOpen()) {this.sender.sendMessage("§cLa partie que vous souhaitez rejoindre est en privée");return;}
                if (this.game.getPlayerList().size() > this.game.getGameCharacteristicValue("player")) {this.sender.sendMessage("§cLa partie est remplit, vous ne pouvez plus rejoindre la partie");return;}
                if (this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("§cVous êtes déjà dans la partie !");return;}
                for (Map.Entry gameList : Main.game_list.entrySet()) {this.game = (Game) gameList.getValue();if (this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("Vous êtes déjà dans une partie !");return;}}
                break;
            }


            case "delete":
            {
                if (this.game.getOwner() != this.sender.getUniqueId() || !this.sender.hasPermission("domination.animator.use")) {this.sender.sendMessage("§cVous ne pouvez pas supprimer la partie des autres");return;}
                if (this.game.isLaunched() || !this.sender.hasPermission("domination.animator.use")) {this.sender.sendMessage("§cLa partie que vous souhaitez supprimer n'existe pas");return;}
                break;
            }


            case "leave":
            {
                if (!this.game.getPlayerList().contains(this.sender.getUniqueId())) {this.sender.sendMessage("§cVous n'avez pas encore rejoins cette partie !");return;}
                break;
            }


            case "load":
            {
                break;
            }


            case "invite":
            {
            }

            default: this.sender.sendMessage("§cCommande incorrecte, veuillez faire /dt help"); return;
        }
        executeCommand();
    }


        @Override
        public void executeCommand()
        {
            Model_Request request = new Model_Request(this.game_name, this.sender, this.config);

        switch(this.command_type)
        {
            case "create" :
            {
                request.create();
                this.sender.sendMessage("§aLa partie " + this.game_name + " a été crée avec succès");
                break;
            }

            case "join" :
            {
                request.join();
                new GameScoreBoard(this.sender).runTaskLater(this.config, 0);
                this.sender.sendMessage("§aVous avez rejoins la partie de §e" + Objects.requireNonNull(Bukkit.getPlayer(Main.getGame(this.game_name).getOwner())).getName());
                break;
            }

            case "delete" :
            {
                request.delete();
                this.sender.sendMessage("§cLa partie §e<< " + this.game_name + " >>§cvient d'être supprimé");
                break;
            }

            case "leave" :
            {
                request.leave();
                this.sender.sendMessage("§cVous avez quitté la partie !");
                break;
            }

            case "load" :
            {
                request.load();
                break;
            }

            case "invite" :
            {
                request.invite();
                break;
            }

            case "help" :
            {
                request.help();
                break;
            }

        }
    }
}