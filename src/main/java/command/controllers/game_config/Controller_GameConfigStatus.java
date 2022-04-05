package command.controllers.game_config;

import command.models.game_config.Model_GameConfigStatus;
import command.parent.CommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfigStatus extends CommandController {

    public Controller_GameConfigStatus(String game_name, String command_type, Player sender, Main plugin) {
        super(game_name, command_type, sender, plugin);
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
    public void executeCommand() {
        Model_GameConfigStatus game_config_status;

        switch (this.command_type)
        {
            case "open" :
                this.sender.sendMessage("§aTout le monde pourront dès à présent rejoindre votre partie");
                game_config_status = new Model_GameConfigStatus(this.game_name, this.sender, true, this.plugin);
                game_config_status.updateConfiguration();
                break;
            case "close" :
                this.sender.sendMessage("§aPlus personne pourra rejoindre votre partie sauf les invitations");
                game_config_status = new Model_GameConfigStatus(this.game_name, this.sender, false, this.plugin);
                game_config_status.updateConfiguration();
                break;
        }
    }
}
