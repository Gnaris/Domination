package command.controllers.game_config;

import command.models.game_config.Model_GameConfigValue;
import command.parent.CommandController;
import game.core.GameScoreBoard;
import main.Main;
import main.utils.GameRecuperator;
import org.bukkit.entity.Player;

public class Controller_GameConfigValue extends CommandController {


    public Controller_GameConfigValue(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);
        // Command : /dt <game name> <name of key> <value>
        this.game_name = args[0];
        this.command_type = args[1];
        this.value = Integer.parseInt(args[2]);

        this.game = GameRecuperator.byGame_Name(plugin.getGames_list(), this.game_name);
    }

    @Override
    public void ControlCmd() {

        String error = null;
        error = this.game == null ? "§cVous devez d'abord créer une partie " :
                this.game.getOwner() != this.sender.getUniqueId() && !this.sender.hasPermission("domination.animator.use") ? "§cVous devez être le propriétaire de la partie" :
                !this.plugin.getConfig().getBoolean("default_game_caracteristique.configurable." + this.command_type) ? "§cCette configuration est désactivée par les staffs ou elle n'existe pas" :
                this.value < (int) this.game.getMinGameConfigValue(this.command_type) || this.value > (int) this.game.getMaxGameConfigValue(this.command_type) ?  "§cLes valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue(this.command_type) + " ni au dessus de " + this.game.getMaxGameConfigValue(this.command_type) : null;

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
    public void executeCmd()
    {
        Model_GameConfigValue game_config = new Model_GameConfigValue(this);
        game_config.updateConfiguration();
        new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}
