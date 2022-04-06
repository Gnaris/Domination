package command.controllers.game_config;

import coliseum.Coliseum;
import command.models.game_config.Model_GameConfigMap;
import command.parent.CommandController;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_GameConfigMap extends CommandController {

    private String coliseum_name;
    private Coliseum coliseum;

    public Controller_GameConfigMap(String game_name, String command_type, String coliseum_name, Player sender, Main plugin) {
        super(game_name, command_type, sender, plugin);
        this.coliseum_name = coliseum_name;
    }

    @Override
    public void ControlCmd() {
        if(this.plugin.getColiseum_list().get(this.coliseum_name) == null)
        {
            this.sender.sendMessage("§cVous ne pouvez pas utiliser une map qui n'existe pas");
            return;
        }

        this.coliseum = this.plugin.getColiseum_list().get(this.coliseum_name);

        if(this.plugin.getColiseum_list().get(this.coliseum_name).isUsed())
        {
            this.sender.sendMessage("§cCette map est déjà utilisée par une autre partie !");
            return;
        }

        executeCmd();
    }

    @Override
    protected void executeCmd()
    {
        Model_GameConfigMap game_config_map = new Model_GameConfigMap(this.game_name, this.coliseum, this.sender, this.plugin);
        game_config_map.setColiseum();
        this.sender.sendMessage("§cChangement d'arène effectuée : " + this.coliseum.getName());
        new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
    }
}
