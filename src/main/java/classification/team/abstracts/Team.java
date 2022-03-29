package classification.team.abstracts;

import org.bukkit.entity.Player;

public abstract class Team{

    protected Player sender;

    public Team(Player sender)
    {
        this.sender = sender;
    }

    public abstract void join();

    public abstract void sendMessage();
}
