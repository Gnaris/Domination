package map_command.models;

import classification.TeamList;
import map.core.spawn.Spawn;
import map_command.parent.AdminCmdController;
import map_command.parent.AdminCmdModel;
import org.bukkit.Location;
import utils.MapsUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Model_Spawn extends AdminCmdModel {

    private TeamList color_team;
    private final String spawn_type;

    public Model_Spawn(AdminCmdController controller, String spawn_type)
    {
        super(controller);
        Arrays.stream(TeamList.values())
                .filter(team_color -> team_color.toString().toLowerCase().equalsIgnoreCase(spawn_type))
                .forEach(team_color -> this.color_team = team_color);
        this.spawn_type = spawn_type;
    }

    public void setSpawn()
    {
        DecimalFormat position = new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.ENGLISH));
        DecimalFormat axe = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.ENGLISH));
        double x, y , z, yaw , pitch;
        x = Double.parseDouble(position.format(this.sender.getLocation().getX()));
        y = Double.parseDouble(position.format(this.sender.getLocation().getY()));
        z = Double.parseDouble(position.format(this.sender.getLocation().getZ()));
        yaw = Double.parseDouble(axe.format(this.sender.getLocation().getYaw()));
        pitch = Double.parseDouble(axe.format(this.sender.getLocation().getPitch()));
        List<Double> location = Arrays.asList(x,y,z,yaw,pitch);

        //end spawn
        if(this.spawn_type.equalsIgnoreCase("endspawn"))
        {
            this.map_config.getConfig().set("map." + this.map.getName() + ".endspawn", null);
            this.map_config.getConfig().set("map." + this.map.getName() + ".endspawn.world", this.sender.getWorld().getName());
            this.map_config.getConfig().set("map." + this.map.getName() + ".endspawn.location", location);

            this.map.setEnd_spawn(new Location(this.sender.getWorld(), x,y,z, (float) yaw, (float) pitch));
        }
        else if(this.color_team != null)
        {
            //Team spawn
            this.map_config.getConfig().set("map." + this.map.getName() + ".spawns_teams" + this.spawn_type + "." + this.name, null);
            this.map_config.getConfig().set("map." + this.map.getName() + ".spawns_teams." + this.spawn_type + "." + this.name, location);

            this.map.getSpawn_list().add(new Spawn(this.name, this.color_team, new Location(this.sender.getWorld(), x,y,z, (float) yaw, (float) pitch)));
        }
        this.map_config.saveConfig();
    }

    public void deleteSpawn()
    {
        //End spawn
        if(this.spawn_type.equalsIgnoreCase("endspawn"))
        {
            this.map_config.getConfig().set("map." + this.map.getName() + ".endspawn", null);
            this.map.setEnd_spawn(null);
        }
        else if(this.color_team != null)
        {
            //Team spawn
            this.map_config.getConfig().set("map." + this.map.getName() + ".spawns_teams." + this.spawn_type + "." + this.name, null);
            this.map.getSpawn_list().stream()
                    .filter(spawn_list -> spawn_list.getName().equalsIgnoreCase(this.name))
                    .collect(Collectors.toList())
                    .forEach(spawn_list -> this.map.getSpawn_list().remove(spawn_list));
        }
        this.map_config.saveConfig();
    }
}
