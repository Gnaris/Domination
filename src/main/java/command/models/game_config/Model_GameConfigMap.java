package command.models.game_config;

import coliseum.Coliseum;
import command.parent.CommandModel;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigMap extends CommandModel {

    private Coliseum coliseum;

    public Model_GameConfigMap(String game_name, Coliseum coliseum, Player sender, Main plugin) {
        super(game_name, sender, plugin);
        this.coliseum = coliseum;
    }

    public void setColiseum()
    {
        this.game.setColiseum(this.coliseum);
    }
}
