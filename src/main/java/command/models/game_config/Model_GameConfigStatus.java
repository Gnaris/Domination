package command.models.game_config;


import command.parent.CommandController;
import command.parent.CommandModel;

public class Model_GameConfigStatus extends CommandModel{


    public Model_GameConfigStatus(CommandController controller) {
        super(controller);
    }


    public void updateConfiguration()
    {
        this.game.setOpen(this.status);
    }
}
