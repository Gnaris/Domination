package command.controllers.gameconfig;

import command.models.gameconfig.Model_GameConfigStatus;
import command.parent.CommandController;
import org.bukkit.entity.Player;

public class Controller_GameConfigStatus extends CommandController {

    public Controller_GameConfigStatus(String game_name, String command_type, Player sender) {
        super(game_name, command_type, sender);
    }

    @Override
    public void checkAndExecuteCommand() {
        if(this.game == null)
        {
            this.sender.sendMessage("§cVous devez d'abord créer une partie !");
            return;
        }
        if(!this.game.getOwner().toString().equalsIgnoreCase(this.sender.getUniqueId().toString()))
        {
            this.sender.sendMessage("§cVous devez être le propriétaire de la partie !");
            return;
        }
        if(this.game.isOpen() && this.command_type.equalsIgnoreCase("open"))
        {
            this.sender.sendMessage("§cVotre partie est déjà ouverte aux publics");
            return;
        }
        if(!this.game.isOpen() && this.command_type.equalsIgnoreCase("close"))
        {
            this.sender.sendMessage("§cVotre partie est déjà fermé aux publics");
            return;
        }
        executeCommand();
    }

    @Override
    protected void executeCommand() {
        boolean status = command_type.equalsIgnoreCase("open");
        Model_GameConfigStatus game_config_status = new Model_GameConfigStatus(this.game_name, this.sender, status);
        game_config_status.updateConfiguration();

        switch (this.command_type)
        {
            case "open" :
                this.sender.sendMessage("§aTout le monde pourront dès à présent rejoindre votre partie");
                break;
            case "close" :
                this.sender.sendMessage("§aPlus personne pourra rejoindre votre partie sauf les invitations");
                break;
        }
    }
}
