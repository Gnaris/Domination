package classification.kit;

import classification.kit.parent.Kit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Epeiste extends Kit {
    public Epeiste(Player sender) {
        super(sender);
        this.kit = KitList.EPEISTE;
    }

    @Override
    protected List<ItemStack> giveKit() {
        return null;
    }
}
