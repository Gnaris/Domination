package command.game_configuration;

import command.abstracts.CommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfig extends CommandController {

    private int value;;
    private Model_GameConfig game_config;

    public Controller_GameConfig(String game_name, String type_command, Player sender, int value) {
        super(game_name, type_command, sender);
        this.value = value;
    }

    @Override
    public void checkAndExecuteCommandType() {

        if(this.game == null) {this.sender.sendMessage("§cVous devez d'abord creer une partie !"); return;}
        if(this.game.getOwner() != this.sender.getUniqueId() || !this.sender.hasPermission("domination.animator.use")) {this.sender.sendMessage("§cVous devez être le propriétaire de la partie !");return;}
        switch(this.type_command)
        {
            case "player" :
                if(this.value < this.game.getMinGameConfigValue("player") || this.value > this.game.getMaxGameConfigValue("player"))
                {
                    this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("player") + " ni au dessus de " + this.game.getMaxGameConfigValue("player"));
                    return;
                }
                break;
            case "time" :
                if(this.value < this.game.getMinGameConfigValue("time") || this.value > this.game.getMaxGameConfigValue("time"))
                {
                    this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("time") + " ni au dessus de " + this.game.getMaxGameConfigValue("time"));
                    return;
                }
                break;
            case "respawntimer" : if(this.value < this.game.getMinGameConfigValue("respawntimer") || this.value > this.game.getMaxGameConfigValue("respawntimer"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("respawntimer") + " ni au dessus de " + this.game.getMaxGameConfigValue("respawntimer"));
                return;
            }
                break;
            case "catchtimer" : if(this.value < this.game.getMinGameConfigValue("catchtimer") || this.value > this.game.getMaxGameConfigValue("catchtimer"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("catchtimer") + " ni au dessus de " + this.game.getMaxGameConfigValue("catchtimer"));
                return;
            }
                break;
            case "catchspeed" : if(this.value < this.game.getMinGameConfigValue("catchspeed") || this.value > this.game.getMaxGameConfigValue("catchspeed"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("catchspeed") + " ni au dessus de " + this.game.getMaxGameConfigValue("catchspeed"));
                return;
            }
                break;
            case "flag" : if(this.value < this.game.getMinGameConfigValue("flag") || this.value > this.game.getMaxGameConfigValue("flag"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("flag") + " ni au dessus de " + this.game.getMaxGameConfigValue("flag"));
                return;
            }
                break;
            case "radius" : if(this.value < this.game.getMinGameConfigValue("radius") || this.value > this.game.getMaxGameConfigValue("radius"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("radius") + " ni au dessus de " + this.game.getMaxGameConfigValue("radius"));
                return;
            }
                break;
            case "spawn" : if(this.value < this.game.getMinGameConfigValue("spawn") || this.value > this.game.getMaxGameConfigValue("spawn"))
            {
                this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("spawn") + " ni au dessus de " + this.game.getMaxGameConfigValue("spawn"));
                return;
            }
                break;
            case "point" :
                if(this.value < this.game.getMinGameConfigValue("point") || this.value > this.game.getMaxGameConfigValue("point"))
                {
                    this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("point") + " ni au dessus de " + this.game.getMaxGameConfigValue("point"));
                    return;
                }
                break;
            case "killpoint" :
                if(this.value < this.game.getMinGameConfigValue("killpoint") || this.value > this.game.getMaxGameConfigValue("killpoint"))
                {
                    this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("killpoint") + " ni au dessus de " + this.game.getMaxGameConfigValue("killpoint"));
                    return;
                }
                break;
            case "flagpoint" :
                if(this.value < this.game.getMinGameConfigValue("flagpoint") || this.value > this.game.getMaxGameConfigValue("flagpoint"))
                {
                    this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue("flagpoint") + " ni au dessus de " + this.game.getMaxGameConfigValue("flagpoint"));
                    return;
                }
                break;
            default : this.sender.sendMessage("§cCommande incorrecte, veuillez fait /dt help"); return;
        }
        executeTypeCommand();
    }

    @Override
    public void executeTypeCommand()
    {
        Model_GameConfig game_config = new Model_GameConfig(this.game, this.sender, this.value);

        switch(this.type_command) {
            case "player":
                game_config.setPlayer();
                this.sender.sendMessage("§aNombre de joueur maximum : " + this.value);
                break;
            case "time": game_config.setTime(); break;
            case "respawntimer": game_config.setRespawnTimer(); break;
            case "catchingtimer": game_config.setCatchingTimer(); break;
            case "catchspeed": game_config.setCatchingSpeed(); break;
            case "flag": game_config.setFlag(); break;
            case "radius": game_config.setRadius(); break;
            case "spawn": game_config.setSpawn(); break;
            case "point": game_config.setPoint(); break;
            case "killpoint": game_config.setKillPoint(); break;
            case "flagpoint" : game_config.setFlagPoint(); break;
        }
    }

    // PLUS TARD
    private boolean CheckConfigValue(String key)
    {
        if(this.value < this.game.getMinGameConfigValue(key) || this.value > this.game.getMaxGameConfigValue(key))
        {
            this.sender.sendMessage("Les valeurs ne doivent pas être en dessous de " + this.game.getMinGameConfigValue(key) + " ni au dessus de " + this.game.getMaxGameConfigValue(key));
            return false;
        }
        return true;
    }
}
