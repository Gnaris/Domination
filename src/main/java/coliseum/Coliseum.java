package coliseum;

import classification.team.TeamList;

import java.util.HashMap;
import java.util.Map;

public class Coliseum {

    private String name;
    private Map<String, Flag> flag_list = new HashMap<>();
    private Map<TeamList, Spawn> spawn_list = new HashMap<>();

    public Coliseum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Flag> getFlag_list()
    {
        return this.flag_list;
    }

    public Map<TeamList, Spawn> getSpawn_list()
    {
        return this.spawn_list;
    }
}
