package command.controllers.game_config;

import command.models.game_config.Model_GameConfigValue;
import command.parent.CommandController;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfigValue extends CommandController {

    private final int value;

    public Controller_GameConfigValue(String game_name, String command_type, int value, Player sender, Main plugin) {
        super(game_name, command_type, sender, plugin);
        this.value = value;
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
        if(!this.plugin.getConfig().getBoolean("default_game_caracteristique.configurable." + this.command_type))
        {
            this.sender.sendMessage("§cCette configuration est désactivée par les staffs ou elle n'existe pas");
            return;
        }
        if(this.value < this.game.getMinGameConfigValue(this.command_type) || this.value > this.game.getMaxGameConfigValue(this.command_type))
        {
            this.sender.sendMessage("§cLes valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue(this.command_type) + " ni au dessus de " + this.game.getMaxGameConfigValue(this.command_type));
            return;
        }
        if(this.command_type.equalsIgnoreCase("flag") && this.value > this.game.getColiseum().getFlag_list().size())
        {
            this.sender.sendMessage("§eCette arène possède uniquement " + this.game.getColiseum().getFlag_list().size() + " drapeau(x). De ce fait, vous aurez uniquement ces drapeaux");
            return;
        }

        executeCmd();
    }

    @Override
    public void executeCmd()
    {
        Model_GameConfigValue game_config = new Model_GameConfigValue(this.game_name, this.command_type, this.value, this.sender, this.plugin);
        game_config.updateConfiguration();
        new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}
