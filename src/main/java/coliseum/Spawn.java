package coliseum;

import classification.team.TeamList;
import org.bukkit.Location;

public class Spawn {

    private String name;
    private TeamList team_color;
    private Location location;

    public Spawn(String name, TeamList team_color, Location location)
    {
        this.name = name;
        this.team_color = team_color;
        this.location = location;
    }
}
