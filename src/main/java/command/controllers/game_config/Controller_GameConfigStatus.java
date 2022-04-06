package command.controllers.game_config;

import command.models.game_config.Model_GameConfigStatus;
import command.parent.CommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfigStatus extends CommandController {

    public Controller_GameConfigStatus(String command_type, String game_name, Player sender, Main plugin) {
        super(game_name, command_type, sender, plugin);
    }

    @Override
    public void ControlCmd() {

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

        executeCmd();
    }

    @Override
    protected void executeCmd() {
        Model_GameConfigStatus game_config_status = null;

        switch (this.command_type)
        {
            case "open" :
                game_config_status = new Model_GameConfigStatus(this.game_name, this.sender, true, this.plugin);
                this.sender.sendMessage("§aTout le monde pourront dès à présent rejoindre votre partie");
                break;
            case "close" :
                game_config_status = new Model_GameConfigStatus(this.game_name, this.sender, false, this.plugin);
                this.sender.sendMessage("§aPlus personne ne pourra rejoindre votre partie sauf pour les invitations");
                break;
        }

        assert game_config_status != null;
        game_config_status.updateConfiguration();
    }
}
