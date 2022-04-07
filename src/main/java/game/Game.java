package game;

import classification.Classification;
import coliseum.Coliseum;
import game.builder.GameCharacteristic;
import main.Main;
import org.bukkit.entity.Player;

import java.util.*;

public class Game extends GameCharacteristic {

    private UUID owner;
    private String game_name;
    private List<UUID> player_list = new ArrayList<>();
    private Map<UUID, Classification> player_classification = new HashMap<>();
    private Coliseum coliseum = null;


    public Game(String game_name, Player sender, Main plugin)
    {
        super(plugin);
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

    public String getGame_name() {
        return game_name;
    }

    public Coliseum getColiseum() {
        return coliseum;
    }

    public void setColiseum(Coliseum coliseum) {
        this.coliseum = coliseum;
    }

}
