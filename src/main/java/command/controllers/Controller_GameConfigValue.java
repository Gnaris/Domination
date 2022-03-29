package command.controllers;

import command.models.Model_GameConfigValue;
import command.parent.CommandController;
import main.Config;
import org.bukkit.entity.Player;

public class Controller_GameConfigValue extends CommandController {

    private int value;;
    private Model_GameConfigValue game_config;

    public Controller_GameConfigValue(String game_name, String command_type, Player sender, int value) {
        super(game_name, command_type, sender);
        this.value = value;
    }

    @Override
    public void checkAndExecuteCommandType() {

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
        if(!Config.GetBoolean("default_game_caracteristique.configurable." + this.command_type))
        {
            this.sender.sendMessage("§cCette configuration est désactivée par les staffs ou elle n'existe pas");
            return;
        }
        if(this.value < this.game.getMinGameConfigValue(this.command_type) || this.value > this.game.getMaxGameConfigValue(this.command_type))
        {
            this.sender.sendMessage("§cLes valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue(this.command_type) + " ni au dessus de " + this.game.getMaxGameConfigValue(this.command_type));
            return;
        }
        executeTypeCommand();
    }

    @Override
    public void executeTypeCommand()
    {
        Model_GameConfigValue game_config = new Model_GameConfigValue(this.game_name, this.sender, this.value);

        switch(this.command_type) {
            case "player":
                game_config.setPlayer();
                this.sender.sendMessage("§aNombre de joueur maximum : " + this.value);
                break;
            case "time":
                game_config.setTime();
                this.sender.sendMessage("§aDurée de la partie : " + this.value + " secondes");
                break;
            case "respawntimer":
                game_config.setRespawnTimer();
                this.sender.sendMessage("§aTemps de respawn : " + this.value);
                break;
            case "catchingtimer":
                game_config.setCatchingTimer();
                this.sender.sendMessage("§aDurée de la capture de drapeau : " + this.value);
                break;
            case "catchspeed":
                game_config.setCatchingSpeed();
                this.sender.sendMessage("§aRapidité de la capture selon le nombre de joueur : " + this.value);
                break;
            case "flag":
                game_config.setFlag();
                this.sender.sendMessage("§aNombre de drapeau dans la partie : " + this.value);
                break;
            case "radius":
                game_config.setRadius();
                this.sender.sendMessage("§aTaille du drapeau (rayon) : " + this.value);
                break;
            case "spawn":
                game_config.setSpawn();
                this.sender.sendMessage("§aNombre de spawn par équipe : " + this.value);
                break;
            case "point":
                game_config.setPoint();
                this.sender.sendMessage("§aPoint pour remporter la manche : " + this.value);
                break;
            case "killpoint": game_config.setKillPoint();
                this.sender.sendMessage("§aNombre de point par joueur tuer : " + this.value);
                break;
            case "flagpoint" :
                game_config.setFlagPoint();
                this.sender.sendMessage("§aNombre de point par drapeau capturé (par seconde) : " + this.value);
                break;
        }
    }
}
