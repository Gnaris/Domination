package classification.kit.parent;

import classification.kit.KitList;
import classification.team.TeamList;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public abstract class Kit {

    protected Player sender;

    public Kit(Player sender)
    {
        this.sender = sender;
    }

    protected abstract List<ItemStack> giveKit();

}
