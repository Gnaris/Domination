package map;

import map.core.flag.Flag;
import map.core.spawn.Spawn;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Coliseum {

    private final String name;
    private final String world;
    private final List<Flag> flag_list = new ArrayList<>();
    private final List<Spawn> spawn_list = new ArrayList<>();
    private Location end_spawn;
    private boolean used = false;

    public Coliseum(String name, String world)
    {
        this.name = name;
        this.world = world;
    }
}
