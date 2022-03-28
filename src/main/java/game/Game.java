package game;

import game.builder.GameCharacteristic;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Game extends GameCharacteristic {

    private UUID owner;
    private List<UUID> playerList;

    public Game(Player sender)
    {
        this.owner = sender.getUniqueId();
    }

    public UUID getOwner()
    {
        return this.owner;
    }

    public List<UUID> getPlayerList() { return this.playerList;}
}
