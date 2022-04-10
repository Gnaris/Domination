package command.controllers.game_config;

import command.models.game_config.Model_GameConfigStatus;
import command.parent.CommandController;
import main.Main;
import main.utils.GameRecuperator;
import org.bukkit.entity.Player;

public class Controller_GameConfigStatus extends CommandController {


    public Controller_GameConfigStatus(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);
        //Command : /dt [open, close] <game_name>
        this.command_type = args[0];
        this.game_name = args[1];
        this.game = GameRecuperator.byGame_Name(plugin.getGames_list(), this.game_name);
    }

    @Override
    public void ControlCmd() {

        String error = null;

        error = this.game == null ? "§cVous devez d'abord créer une partie" :
                this.game.getOwner() != this.sender.getUniqueId() ? "§cVous devez être le propriétaire de la partie" :
                this.game.isOpen() && this.command_type.equalsIgnoreCase("open") ? "§cVotre partie est actuellement déjà ouverte" :
                !this.game.isOpen() && this.command_type.equalsIgnoreCase("close") ? "Votre partie est déjà fermé aux plublics" : null;

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

        switch (this.command_type)
        {
            case "open" :
                this.status = true;
                this.sender.sendMessage("§aTout le monde pourront dès à présent rejoindre votre partie");
                break;
            case "close" :
                this.status = false;
                this.sender.sendMessage("§aPlus personne ne pourra rejoindre votre partie sauf pour les invitations");
                break;
        }

        Model_GameConfigStatus game_config_status = new Model_GameConfigStatus(this);
        game_config_status.updateConfiguration();
    }
}
