package classification.kit;

import classification.kit.parent.Kit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Mage extends Kit {
    public Mage(Player sender) {
        super(sender);
    }

    @Override
    protected List<ItemStack> giveKit() {
        return null;
    }
}
