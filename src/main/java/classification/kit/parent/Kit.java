package classification.kit.parent;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Kit {

    protected Player sender;

    public Kit(Player sender)
    {
        this.sender = sender;
    }

    protected abstract List<ItemStack> giveKit();

}
