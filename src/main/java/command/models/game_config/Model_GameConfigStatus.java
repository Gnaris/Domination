package command.models.game_config;

import command.parent.CommandController;
import command.parent.CommandModel;
import command.parent.Configuration;

public class Model_GameConfigStatus extends CommandModel implements Configuration {


    public Model_GameConfigStatus(CommandController controller) {
        super(controller);
    }

    @Override
    public void updateConfiguration()
    {
        this.game.setOpen(this.status);
    }
}
