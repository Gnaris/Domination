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
    protected KitList kit = null;

    public Kit(Player sender)
    {
        this.sender = sender;
    }

    protected abstract List<ItemStack> giveKit();

    public void setKit()
    {
        for(Map.Entry game_name : Main.game_list.entrySet())
        {
            Game game = (Game) game_name.getValue();
            if(game.getPlayerList().contains(this.sender.getUniqueId()))
            {
                game.getPlayerClassification().get(this.sender.getUniqueId()).setKit(this.kit);
                break;
            }
        }
    }
}
