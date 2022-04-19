package command.models.classification;


import command.parent.CommandController;
import command.parent.CommandModel;

public class Model_Classification extends CommandModel{


    public Model_Classification(CommandController controller) {
        super(controller);
    }

    public void updateConfiguration() {
        if(this.team != null)
        {
            this.game.getPlayer_list().get(this.sender.getUniqueId()).setTeam(this.team);
        }
        if(this.kit != null)
        {
            this.game.getPlayer_list().get(this.sender.getUniqueId()).setKit(this.kit);
        }
    }
}
