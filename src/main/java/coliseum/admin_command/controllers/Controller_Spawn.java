package coliseum.admin_command.controllers;

import classification.team.TeamList;
import coliseum.admin_command.models.Model_Spawn;
import coliseum.admin_command.parent.AdminCmdController;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Controller_Spawn extends AdminCmdController {

    private TeamList team_color;

    public Controller_Spawn(Player sender, String command_type, String map_name, String team_color, String value_name, Main plugin) {
        super(sender, command_type, map_name, value_name, plugin);

        Arrays.stream(TeamList.values())
                .filter(teamlist -> team_color.equalsIgnoreCase(teamlist.toString().toLowerCase()))
                .collect(Collectors.toList())
                .forEach(teamlist -> this.team_color = teamlist);
    }


    @Override
    public void controlCmd() {
        boolean response = true;

        if(this.coliseums_list.get(this.map_name) == null)
        {
            this.sender.sendMessage("Le nom de la map est inexistant");
            response = false;
        }
        if(this.team_color == null)
        {
            this.sender.sendMessage("§cLe nom de cette équipe est invalide");
            response = false;
        }
        if(this.team_color == TeamList.RANDOM)
        {
            this.sender.sendMessage("§cPourquoi mettre un spawn pour les randoms ?");
            response = false;
        }

        if(response)
        {
            switch(this.command_type)
            {
                case "setspawn" :
                {
                    if(this.coliseums_list.get(this.map_name).getSpawn_list().get(this.value_name) != null)
                    {
                        this.sender.sendMessage("§cLe nom de ce spawn existe déjà. Veuillez vérifier si le nom n'est pas dans les autres team aussi");
                        response = false;
                    }
                    break;
                }

                case "deletespawn" :
                {
                    if(this.coliseums_list.get(this.map_name).getSpawn_list().get(this.value_name) == null)
                    {
                        this.sender.sendMessage("§cLe nom de ce spawn est inexistant");
                        return;
                    }
                }
            }
        }

        if(response) executeCmd();
    }

    @Override
    public void executeCmd() {

        Model_Spawn model_spawn = new Model_Spawn(this.map_name, this.value_name, this.sender.getLocation(), this.team_color, this.coliseum_config, this.coliseums_list);

        switch(this.command_type)
        {
            case "setspawn" :
            {
                model_spawn.setSpawn();
                this.sender.sendMessage("§aUn nouveau spawn a été défini");
                break;
            }

            case "deletespawn" :
            {
                model_spawn.deleteSpawn();
                this.sender.sendMessage("§aCe spawn a définitivement été supprimé");
                break;
            }
        }
    }
}
