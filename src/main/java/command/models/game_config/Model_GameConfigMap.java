package command.models.game_config;


import command.parent.CommandController;
import command.parent.CommandModel;

public class Model_GameConfigMap extends CommandModel {

    public Model_GameConfigMap(CommandController controller) {
        super(controller);
    }

    public void setMap()
    {
        this.game.setMap(this.coliseum);
    }
}
