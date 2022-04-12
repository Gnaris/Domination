package command.controllers.game_config;

import command.models.game_config.Model_GameConfigMap;
import command.parent.CommandController;
import game.core.GameScoreBoard;
import main.Main;
import utils.GameUtils;
import utils.MapsUtils;
import org.bukkit.entity.Player;

public class Controller_GameConfigMap extends CommandController {


    public Controller_GameConfigMap(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dt <game name> map <map name>
        this.game_name = args[0];
        this.command_type = args[1];
        this.map_name = args[2];

        this.map = MapsUtils.getMapByName(plugin.getMaps_list(), this.map_name);
        this.game = GameUtils.getGameByName(plugin.getGames_list(), this.game_name);
    }

    @Override
    public void ControlCmd() {

        String error = null;
        error = this.map == null ? "§cVous ne pouvez pas utiliser une map qui n'existe pas" :
                this.map.isUsed() ? "§cCette map est déjà utilisée par une autre partie" :
                this.game.getOwner() != this.sender.getUniqueId() ? "§cVous devez être le propriétaire de la partie" : null;
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
    protected void executeCmd()
    {
        Model_GameConfigMap game_config_map = new Model_GameConfigMap(this);
        game_config_map.setMap();
        this.sender.sendMessage("§aVous avez choisis l'arène " + this.map_name);
        new GameScoreBoard(this.game_name, this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}

