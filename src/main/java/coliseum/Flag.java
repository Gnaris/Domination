package coliseum;


import org.bukkit.Location;

public class Flag {

    private String name;
    private Location flag_location;

    public Flag(String name, Location flag_location)
    {
        this.name = name;
        this.flag_location = flag_location;
    }

    public String getName() {
        return name;
    }

    public Location getFlag_location() {
        return flag_location;
    }
}
