package command.models.request;

import classification.Classification;
import classification.KitList;
import classification.TeamList;
import map.Coliseum;
import map.core.flag.Flag;
import map.core.spawn.Spawn;
import command.parent.CommandController;
import command.parent.CommandModel;
import game.Game;
import org.bukkit.Material;
import utils.GameUtils;

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

    /**
     * Créer une clone de la map choisis tout en triant le nombre de spawn et drapeau selon la configuration choisis
     * Construit entre temps un drapeau.
     */
    public void load() {

        int nb_flag = (int) this.game.getGameCharacteristicValue("flag");

        Coliseum map = new Coliseum(this.game.getMap().getName(), this.game.getMap().getWorld());
        map.setEnd_spawn(this.game.getMap().getEnd_spawn());

        Collections.shuffle(this.game.getMap().getFlag_list());
        for(int i = 0; i < nb_flag; i++)
        {
            Flag flag = this.game.getMap().getFlag_list().get(i);
            map.getFlag_list().add(flag);
            flag.buildFlag(Material.WHITE_CONCRETE, Material.GLASS, (int) this.game.getGameCharacteristicValue("radius"));
        }

        Collections.shuffle(this.game.getMap().getSpawn_list());
        for(int i = 0; i < this.game.getMap().getSpawn_list().size(); i++)
        {
            Spawn spawn = new Spawn(this.game.getMap().getSpawn_list().get(i).getName(), this.game.getMap().getSpawn_list().get(i).getTeam_color(), this.game.getMap().getSpawn_list().get(i).getLocation());
            map.getSpawn_list().add(spawn);
        }

        this.game.getMap().setUsed(true);

        //Nouvelle map
        this.game.setMap(map);
        this.game.setMap_loaded(true);
    }

    public void invite() {

    }

    public void help()
    {
        this.sender.sendMessage("§a/dt create <nom de la partie> : §rCréer une partie");
        this.sender.sendMessage("§a/dt delete <nom de la partie> : §rSupprime la partie");
        this.sender.sendMessage("§a/dt leave <nom de la partie> : §rQuitte la partie");
        this.sender.sendMessage("§a/dt open/close <nom de la partie> : §rDéfinis si la partie sera en privée ou en public");
        this.sender.sendMessage("§a/dt invite <pseudo> <nom de la partie> : §rFaire une demande d'invitation au joueur dans la partie");
        this.sender.sendMessage("§a/dt <nom de la partie> map <nom de la map> : §rChoisir une map");
        this.sender.sendMessage("§a/dt load <nom de la partie> : §rConfirme la map");
        this.sender.sendMessage("§a/dt show map : §rAffiche tout les maps");
        Game game = GameUtils.getGameByPlayerUUID(this.plugin.getGames_list(), this.sender.getUniqueId());
        if(game != null && (game.getOwner() == this.sender.getUniqueId() || this.sender.hasPermission("domination.animator.use")))
        {
            this.sender.sendMessage("§aFaites /dt help 2 pour voir la liste des configurations de votre partie");
        }
        if(this.sender.hasPermission("domination.animator.use"))
        {
            this.sender.sendMessage("§aFaites /dt help 3 pour voir la liste des commandes pour créer une map");
        }
    }

    public void help2()
    {
        this.sender.sendMessage("§a/dt <nom de la partie> §e<player> §a<valeur> : §rDéfini le nombre maximum de joueur dans la partie");
        this.sender.sendMessage("§a/dt <nom de la partie> §e<time> §a<valeur> : §rDéfini ");
    }

    public void help3()
    {

    }

    public void start()
    {
        this.game.setLaunched(true);
        this.game.setTimer(this.game.getGameCharacteristicValue("time"));
    }
}

