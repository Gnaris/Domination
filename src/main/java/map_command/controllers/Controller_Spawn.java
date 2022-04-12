package map_command.controllers;

import classification.team.TeamList;
import main.Main;
import utils.MapsUtils;
import map_command.models.Model_Spawn;
import map_command.parent.AdminCmdController;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Controller_Spawn extends AdminCmdController {

    public Controller_Spawn(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dta <map name> [setspawn, deletespawn] <color team> <name>
        this.map_name = args[0];
        this.command_type = args[1];
        String color_team = args[2];
        this.name = args[3];

        this.map = MapsUtils.getMapByName(this.maps_list, this.map_name);

        Arrays.stream(TeamList.values())
                .filter(teamlist -> color_team.equalsIgnoreCase(teamlist.toString().toLowerCase()))
                .collect(Collectors.toList())
                .forEach(teamlist -> this.color_team = teamlist);
    }


    @Override
    public void controlCmd() {
        String error;
        error = this.map == null ? "§cLe nom de la map est inexistant" :
                this.color_team == null ? "§cLe nom de cette équipe est invalide" :
                this.color_team == TeamList.RANDOM ? "§cPourquoi mettre un spawn pour les randoms ?" : null;

        if(error == null)
        {
            switch(this.command_type)
            {
                case "setspawn" :
                {
                    error = this.map.getSpawn_list().stream()
                            .anyMatch(spawn_list -> spawn_list.getName().equalsIgnoreCase(this.name)) ?
                            "§cLe nom de ce spawn existe déjà. Veuillez vérifier si le nom n'est pas dans les autres team aussi" : null;
                    break;
                }

                case "deletespawn" :
                {
                    error = this.map.getSpawn_list().stream()
                            .noneMatch(spawn_list -> spawn_list.getName().equalsIgnoreCase(this.name)) ?
                            "§cLe nom de ce spawn n'existe pas !" : null;
                    break;
                }
            }
        }

        if(error == null)
        {
            executeCmd();
        }
        else
        {
            this.sender.sendMessage(error);
        }
    }

    @Override
    public void executeCmd() {

        Model_Spawn model_spawn = new Model_Spawn(this);

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
