package command.models.game_config;


import command.parent.CommandController;
import command.parent.CommandModel;

public class Model_GameConfigValue extends CommandModel{

    public Model_GameConfigValue(CommandController controller) {
        super(controller);
    }

    public void updateConfiguration()
    {
        this.game.setGameCharacteristicValue(this.command_type, this.value);
    }
}
