package game;

import classification.Classification;
import game.builder.GameCharacteristic;
import main.Main;
import org.bukkit.entity.Player;

import java.util.*;

public class Game extends GameCharacteristic {

    private UUID owner;
    private List<UUID> player_list = new ArrayList<>();;
    private Map<UUID, Classification> player_classification = new HashMap<>();;

    public Game(Player sender, Main config)
    {
        super(config);
        this.owner = sender.getUniqueId();
    }

    public UUID getOwner()
    {
        return this.owner;
    }

    public List<UUID> getPlayerList() { return this.player_list;}

    public Map<UUID, Classification> getPlayerClassification()
    {
        return this.player_classification;
    }
}
