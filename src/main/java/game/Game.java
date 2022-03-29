package game;

import classification.Classification;
import game.builder.GameCharacteristic;
import org.bukkit.entity.Player;

import java.util.*;

public class Game extends GameCharacteristic {

    private UUID owner;
    private List<UUID> player_list;
    private Map<UUID, Classification> player_classification;

    public Game(Player sender)
    {
        this.owner = sender.getUniqueId();
        this.player_list = new ArrayList<>();
        this.player_classification = new HashMap<>();
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
