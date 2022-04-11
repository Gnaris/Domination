package classification.team;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum TeamList {
    RED(Material.RED_CONCRETE, Material.RED_STAINED_GLASS, "§crouge"),
    BlUE(Material.BLUE_CONCRETE, Material.BLUE_STAINED_GLASS, "§1bleu"),
    RANDOM(null, null, "§drandom");

    private Material concrete;
    private Material glass;
    private String name;

    TeamList(Material concrete, Material glass, String name)
    {
        this.concrete = concrete;
        this.glass = glass;
        this.name = name;
    }
}
