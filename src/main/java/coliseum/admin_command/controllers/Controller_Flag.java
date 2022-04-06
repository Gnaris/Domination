package coliseum.admin_command.controllers;

import coliseum.Flag;
import coliseum.admin_command.models.Model_Flag;
import coliseum.admin_command.parent.AdminCmdController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Flag extends AdminCmdController {


    public Controller_Flag(Player sender, String command_type, String map_name, String value_name, Main plugin) {
        super(sender, command_type, map_name, value_name, plugin);
    }

    @Override
    public void controlCmd() {

        boolean response = true;

        if(!this.coliseums_list.containsKey(this.map_name))
        {
            this.sender.sendMessage("§cLe nom de la map est inexistant");
            response = false;
        }
        else
        {
            switch(this.command_type)
            {
                case "setflag" :
                {
                    if(this.coliseums_list.get(this.map_name).getFlag_list().stream().anyMatch(flag_list -> flag_list.getName().equalsIgnoreCase(this.value_name)))
                    {
                        this.sender.sendMessage("§cLe nom de ce drapeau existe déjà");
                        response = false;
                    }
                    break;
                }

                case "deleteflag" :
                {
                    boolean contains = false;
                    for(Flag flag : this.coliseums_list.get(this.map_name).getFlag_list())
                    {
                        if(flag.getName().equalsIgnoreCase(this.value_name))
                        {
                           contains = true;
                           break;
                        }
                    }
                    if(!contains)
                    {
                        this.sender.sendMessage("§cLe nom de ce drapeau est inexistant");
                        response = false;
                    }
                    break;
                }
            }
        }

        if(response)
        {
            executeCmd();
        }
    }

    @Override
    protected void executeCmd()
    {
        Model_Flag model_flag = new Model_Flag(this.map_name, this.value_name, this.sender, this.coliseum_config, this.coliseums_list);

        switch (this.command_type)
        {
            case "setflag" :
            {
                model_flag.addFlag();
                this.sender.sendMessage("§aUn nouveau drapeau à été ajouté");
                break;
            }
            case "deleteflag" :
            {
                model_flag.deleteFlag();
                this.sender.sendMessage("§aCe drapeau a été supprimé");
                break;
            }
        }
    }
}
