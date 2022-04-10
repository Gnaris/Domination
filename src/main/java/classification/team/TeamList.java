package classification.team;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum TeamList {
    RED(Material.RED_CONCRETE, Material.RED_STAINED_GLASS),
    BlUE(Material.BLUE_CONCRETE, Material.BLUE_STAINED_GLASS),
    RANDOM(null, null);

    private Material concrete;
    private Material glass;

    TeamList(Material concrete, Material glass)
    {
        this.concrete = concrete;
        this.glass = glass;
    }
}
