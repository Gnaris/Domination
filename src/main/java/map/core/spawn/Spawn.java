package map.core.spawn;

import classification.team.TeamList;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Spawn {

    private final String name;
    private final TeamList team_color;
    private final Location location;

    public Spawn(String name, TeamList team_color, Location location)
    {
        this.name = name;
        this.team_color = team_color;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public TeamList getTeam_color() {
        return team_color;
    }

    public Location getLocation() {
        return location;
    }
}
