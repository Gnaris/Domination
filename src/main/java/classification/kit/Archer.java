package classification.kit;

import classification.kit.parent.Kit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Archer extends Kit {
    public Archer(Player sender) {
        super(sender);
        this.kit = KitList.ARCHER;
    }

    @Override
    protected List<ItemStack> giveKit() {
        return null;
    }
}
