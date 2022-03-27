package game;

import org.bukkit.entity.Player;

import java.util.UUID;

public class Game {

    private UUID owner;

    public Game(Player sender)
    {
        this.owner = sender.getUniqueId();
    }

    public String getOwner()
    {
        return this.owner.toString();
    }

}
