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
        Random random = new Random();
        int random_number;
        Coliseum coliseum = new Coliseum(this.game.getColiseum().getName(), this.game.getColiseum().getWorld());
        for(int i = 0; i < nb_flag; i++)
        {
            random_number = random.nextInt(nb_flag);
            this.sender.sendMessage("number :" + random_number);
            this.sender.sendMessage(this.game.getColiseum().getFlag_list().get(random_number).getName());
            Flag flag = new Flag(this.game.getColiseum().getFlag_list().get(random_number).getName(), this.game.getColiseum().getFlag_list().get(random_number).getFlag_location());
            flag.buildFlag(Material.WHITE_CONCRETE, Material.GLASS, (int) this.game.getGameCharacteristicValue("radius"));
            coliseum.getFlag_list().add(flag);
        }
        for(int i = 0; i < nb_spawn; i++)
        {
            random_number = random.nextInt(nb_spawn);
            Spawn spawn = new Spawn(this.game.getColiseum().getSpawn_list().get(random_number).getName(), this.game.getColiseum().getSpawn_list().get(random_number).getTeam_color(), this.game.getColiseum().getFlag_list().get(random_number).getFlag_location());
            coliseum.getSpawn_list().add(spawn);
        }
        this.game.setColiseum(coliseum);
        this.plugin.getColiseum_list().get(coliseum.getName()).setUsed(true);
    }

    public void invite() {

    }

    public void help()
    {

    }

    public void start()
    {

    }
}

