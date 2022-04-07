package command.models.request;

import classification.Classification;
import classification.kit.KitList;
import classification.team.TeamList;
import coliseum.Coliseum;
import coliseum.Flag;
import coliseum.Spawn;
import command.parent.CommandModel;
import game.Game;
import main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Model_Request extends CommandModel {

    public Model_Request(String game_name, Player sender, Main plugin)
    {
        super(game_name, sender, plugin);
    }

    public void create() {
        this.plugin.getGame_list().put(this.game_name, new Game(this.game_name, this.sender, this.plugin));
    }

    public void join() {
        this.game.getPlayerClassification().put(this.sender.getUniqueId(), new Classification(KitList.INCOGNITO, TeamList.RANDOM));
        this.game.getPlayerList().add(this.sender.getUniqueId());
    }

    public void delete() {
        this.plugin.getGame_list().remove(this.game_name);
    }

    public void leave() {
        this.game.getPlayerList().remove(this.sender.getUniqueId());
    }

    public void load() {

        int nb_flag = (int) this.game.getGameCharacteristicValue("flag");
        int nb_spawn = (int) this.game.getGameCharacteristicValue("spawn");

        Coliseum coliseum = new Coliseum(this.game.getColiseum().getName(), this.game.getColiseum().getWorld());
        Collections.shuffle(this.game.getColiseum().getFlag_list());
        for(int i = 0; i < nb_flag; i++)
        {
            Flag flag = this.game.getColiseum().getFlag_list().get(i);
            coliseum.getFlag_list().add(flag);
            flag.buildFlag(Material.WHITE_CONCRETE, Material.GLASS, (int) this.game.getGameCharacteristicValue("radius"));
        }

        Collections.shuffle(this.game.getColiseum().getSpawn_list());
        for(int i = 0; i < nb_spawn; i++)
        {
            Spawn spawn = new Spawn(this.game.getColiseum().getSpawn_list().get(i).getName(), this.game.getColiseum().getSpawn_list().get(i).getTeam_color(), this.game.getColiseum().getSpawn_list().get(i).getLocation());
            coliseum.getSpawn_list().add(spawn);
        }

        this.game.getColiseum().setUsed(true);
        this.game.setColiseum(coliseum);
        this.game.getColiseum().setMap_loaded(true);
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

