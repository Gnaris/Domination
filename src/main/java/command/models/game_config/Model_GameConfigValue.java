package command.models.game_config;


import command.parent.CommandController;
import command.parent.CommandModel;
import command.parent.Configuration;

public class Model_GameConfigValue extends CommandModel implements Configuration {

    public Model_GameConfigValue(CommandController controller) {
        super(controller);
    }

    @Override
    public void updateConfiguration()
    {
        this.game.setGameCharacteristicValue(this.command_type, this.value);
    }
}
