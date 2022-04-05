package command.admin_command.controllers;

import classification.team.TeamList;
import classification.team.parent.Team;
import command.admin_command.parent.AdministratorCommandController;
import main.Main;
import org.bukkit.entity.Player;

public class Controller_Spawn extends AdministratorCommandController {

    private TeamList team_color;

    public Controller_Spawn(String map_name, String command_type, String name_value, String team_color, Main plugin, Player sender) {
        super(map_name, command_type, name_value, plugin, sender);
        for(TeamList team : TeamList.values())
        {
            if(team.toString().toLowerCase().equalsIgnoreCase(team_color))
            {
                this.team_color = team;
                break;
            }
        }
    }

    @Override
    public void checkAndExecuteCommand() {
        if(this.plugin.getColiseum_list().get(this.map_name) == null || this.plugin.getColiseum_list() == null)
        {
            this.sender.sendMessage("Le nom de la map est inexistant");
            return;
        }
        if(this.team_color == null)
        {
            this.sender.sendMessage("§cLe nom de cette équipe est invalide");
        }

        switch(this.command_type)
        {
            case "setspawn" :
            {
                if(this.plugin.getColiseum_list().get(this.map_name).getSpawn_list().get(this.name_value) != null)
                {
                    this.sender.sendMessage("Le nom de ce spawn est déjà existant");
                    return;
                }
                break;
            }

            case "deletespawn" :
            {
                if(this.plugin.getColiseum_list().get(this.map_name).getSpawn_list().get(this.name_value) == null)
                {
                    this.sender.sendMessage("Le nom de ce spawn est inexistant");
                    return;
                }
            }
        }
    }

    @Override
    public void executeCommand() {

    }
}
