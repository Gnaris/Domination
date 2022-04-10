package command.models.request;

import classification.Classification;
import classification.kit.KitList;
import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.core.Flag;
import coliseum.core.Spawn;
import command.parent.CommandController;
import command.parent.CommandModel;
import game.Game;
import org.bukkit.Material;

import java.util.Collections;

public class Model_Request extends CommandModel {


    public Model_Request(CommandController controller) {
        super(controller);
    }

    public void create() {
        this.plugin.getGames_list().add(new Game(this.game_name, this.sender, this.plugin));
    }

    public void join() {
        this.game.getPlayer_list().put(this.sender.getUniqueId(), new Classification(KitList.INCOGNITO, TeamList.RANDOM));
    }

    public void delete() {
        this.plugin.getGames_list().remove(this.game);
    }

    public void leave() {
        this.game.getPlayer_list().remove(this.sender.getUniqueId());
    }

    public void load() {

        int nb_flag = (int) this.game.getGameCharacteristicValue("flag");

        Coliseum coliseum = new Coliseum(this.game.getMap().getName(), this.game.getMap().getWorld());
        Collections.shuffle(this.game.getMap().getFlag_list());
        for(int i = 0; i < nb_flag; i++)
        {
            Flag flag = this.game.getMap().getFlag_list().get(i);
            coliseum.getFlag_list().add(flag);
            flag.buildFlag(Material.WHITE_CONCRETE, Material.GLASS, (int) this.game.getGameCharacteristicValue("radius"));
        }

        Collections.shuffle(this.game.getMap().getSpawn_list());
        for(int i = 0; i < this.game.getMap().getSpawn_list().size(); i++)
        {
            Spawn spawn = new Spawn(this.game.getMap().getSpawn_list().get(i).getName(), this.game.getMap().getSpawn_list().get(i).getTeam_color(), this.game.getMap().getSpawn_list().get(i).getLocation());
            coliseum.getSpawn_list().add(spawn);
        }

        this.game.getMap().setUsed(true);
        this.game.setMap(coliseum);
        this.game.setMap_loaded(true);
    }

    public void invite() {

    }

    public void help()
    {

    }

    public void start()
    {
        this.game.setLaunched(true);
    }
}

