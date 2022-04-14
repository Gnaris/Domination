package coliseum.core;


import classification.team.TeamList;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Flag {

    private final String name;
    private final Location flag_location;
    private final List<UUID> player_on_flag = new ArrayList<>();
    private FlagStatus status = FlagStatus.NONE;
    private TeamList team_catched = null;
    private HashMap<TeamList, UUID> progress_capture_list = new HashMap<TeamList, UUID>();

    public Flag(String name, Location flag_location)
    {
        this.name = name;
        this.flag_location = flag_location;
        Arrays.stream(TeamList.values())
                .filter(team_color -> team_color != TeamList.RANDOM ||team_color != TeamList.SPECTATOR)
                .collect(Collectors.toList())
                .forEach(team_color -> this.progress_capture_list.put(team_color, null));
    }

    public void buildFlag(Material block, Material glass, int radius)
    {
        //Flag platform
        for(int x = (int) this.flag_location.getX() - (radius + 1); x < (int) this.flag_location.getX() + radius; x++) {
            for(int z = (int) this.flag_location.getZ() - radius; z < (int) this.flag_location.getZ() + (radius + 1); z++) {
                this.flag_location.getWorld()
                        .getBlockAt(x, (int) this.flag_location.getY() - 1, z)
                        .setType(block);
            }
        }

        //Beacon platform
        for(int x = (int) this.flag_location.getX() - 2; x < (int) this.flag_location.getX() + 1; x++) {
            for(int z = (int) this.flag_location.getZ() - 1; z < (int) this.flag_location.getZ() + 2; z++) {
                this.flag_location.getWorld()
                        .getBlockAt(x, (int) this.flag_location.getY() - 3, z)
                        .setType(Material.DIAMOND_BLOCK);
            }
        }

        this.flag_location.getWorld()
                .getBlockAt((int) this.flag_location.getX() -1, (int) this.flag_location.getY() -2, (int) this.flag_location.getZ())
                .setType(Material.BEACON);

        this.flag_location.getWorld()
                .getBlockAt((int) this.flag_location.getX() -1, (int) this.flag_location.getY() -1, (int) this.flag_location.getZ())
                .setType(glass);
    }


    public List<UUID> getPlayer_on_flag_area() {
        return player_on_flag;
    }
}
