package coliseum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coliseum {

    private final String name;
    private final String world;
    private final List<Flag> flag_list;
    private final List<Spawn> spawn_list;
    private boolean used;

    public Coliseum(String name, String world)
    {
        this.name = name;
        this.world = world;
        this.flag_list = new ArrayList<>();
        this.spawn_list = new ArrayList<>();
        this.used = false;
    }

    public String getName() {
        return name;
    }

    public List<Flag> getFlag_list() {
        return flag_list;
    }

    public List<Spawn> getSpawn_list() {
        return spawn_list;
    }

    public String getWorld() {
        return world;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean statut)
    {
        this.used = statut;
    }
}
