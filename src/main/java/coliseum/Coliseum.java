package coliseum;

import java.util.HashMap;
import java.util.Map;

public class Coliseum {

    private final String name;
    private final String world;
    private final Map<String, Flag> flag_list = new HashMap<>();
    private final Map<String, Spawn> spawn_list = new HashMap<>();

    public Coliseum(String name, String world)
    {
        this.name = name;
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public Map<String, Flag> getFlag_list()
    {
        return this.flag_list;
    }

    public Map<String, Spawn> getSpawn_list() {
        return spawn_list;
    }

    public String getWorld() {
        return world;
    }
}
