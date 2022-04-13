package classification.team;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum TeamList {
    RED(Material.RED_CONCRETE, Material.RED_STAINED_GLASS, "Rouge", "§c", true, true),
    BlUE(Material.BLUE_CONCRETE, Material.BLUE_STAINED_GLASS, "Bleu", "§1", true, true),
    RANDOM(null, null, "Random", "§d", false, false),
    SPECTATOR(null, null, "Spectacteur", "§7", true, false);

    private Material concrete;
    private Material glass;
    private String name;
    private String color;
    private boolean spawnable;
    private boolean playable;

    /**
     * @param concrete Flag platform
     * @param glass Glass color for beacon
     * @param name Translation
     * @param color String color
     * @param spawnable Can have spawn ?
     * @param playable Can play him ?
     */
    TeamList(Material concrete, Material glass, String name, String color, boolean spawnable, boolean playable)
    {
        this.concrete = concrete;
        this.glass = glass;
        this.name = name;
        this.color = color;
        this.spawnable = spawnable;
        this.playable = playable;
    }
}
