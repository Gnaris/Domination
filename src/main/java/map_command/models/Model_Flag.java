package map_command.models;

import map.core.flag.Flag;
import map_command.parent.AdminCmdController;
import map_command.parent.AdminCmdModel;
import org.bukkit.Location;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Model_Flag extends AdminCmdModel {


    public Model_Flag(AdminCmdController controller) {
        super(controller);
    }

    public void addFlag()
    {
        DecimalFormat position = new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.ENGLISH));
        double x, y , z;
        x = Double.parseDouble(position.format(this.sender.getLocation().getX()));
        y = Double.parseDouble(position.format(this.sender.getLocation().getY()));
        z = Double.parseDouble(position.format(this.sender.getLocation().getZ()));
        //File Configuration
        List<Double> location = Arrays.asList(x, y, z);

        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name, null);
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + this.name, location);
        this.map_config.saveConfig();

        //Data Main Class
        this.map.getFlag_list().add(new Flag(this.name, new Location(this.sender.getWorld(), x,y,z)));
    }

    public void deleteFlag()
    {
        //File Configuration
        this.map_config.getConfig().set("map." + this.map.getName() + ".flags." + name, null);
        this.map_config.saveConfig();

        //Data Main Class
        this.map.getFlag_list().stream()
                .filter(flag_list -> flag_list.getName().equalsIgnoreCase(this.name))
                .collect(Collectors.toList())
                .forEach(flag_list -> this.map.getFlag_list().remove(flag_list));
    }
}
