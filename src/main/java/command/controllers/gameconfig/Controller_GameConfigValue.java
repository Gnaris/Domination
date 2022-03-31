package command.controllers.gameconfig;

import command.interfaces.CommandExecutor;
import command.models.gameconfig.Model_GameConfigValue;
import command.parent.CommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfigValue extends CommandController{

    private int value;;
    private Model_GameConfigValue game_config;

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
        if(!this.config.getConfig().getBoolean("default_game_caracteristique.configurable." + this.command_type))
        {
            this.sender.sendMessage("§cCette configuration est désactivée par les staffs ou elle n'existe pas");
            return;
        }
        if(this.value < this.game.getMinGameConfigValue(this.command_type) || this.value > this.game.getMaxGameConfigValue(this.command_type))
        {
            this.sender.sendMessage("§cLes valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue(this.command_type) + " ni au dessus de " + this.game.getMaxGameConfigValue(this.command_type));
            return;
        }
        executeCommand();
    }

    @Override
    public void executeCommand()
    {
        Model_GameConfigValue game_config = new Model_GameConfigValue(this.game_name, this.sender,this.command_type, this.value, this.config);
        game_config.updateConfiguration();
    }
}
