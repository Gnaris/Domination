package command.controllers.request;


import command.models.request.Model_Request;
import command.parent.CommandController;
import game.core.GameScoreBoard;
import game.core.Launcher;
import main.Main;
import utils.GameUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import utils.TeamUtils;

import java.util.Objects;
import java.util.UUID;

public class Controller_Request extends CommandController {


    public Controller_Request(String[] args, Player sender, Main plugin) {
        super(args, sender, plugin);

        // Command : /dt [RequestList] <game name> OR /dt help

        this.command_type = args[0];
        if(args.length == 2 && !this.command_type.equalsIgnoreCase("help"))
        {
            this.game_name = args[1];
            this.game = GameUtils.getGameByName(plugin.getGames_list(), this.game_name);
        }
    }

    @Override
    public void ControlCmd()
    {
        String error;
        error = this.game == null && !this.command_type.equalsIgnoreCase("create") && !this.command_type.equalsIgnoreCase("help")  ? "§cLe nom de la partie est incorrecte ou inexistant" : null;

        if(error == null)
        {
            switch (this.command_type)
            {
                case "create":
                {
                    error = this.plugin.getGames_list().contains(this.game) ? "§cLe nom de cette partie existe déjà !" : null;
                    if(error == null && !this.sender.hasPermission("domination.animator.use"))
                    {
                        error = this.plugin.getGames_list().stream()
                                .anyMatch(game_list -> game_list.getOwner() == this.sender.getUniqueId()) ? "§cVous avez déjà crée une partie" : null;
                    }
                    break;
                }

                case "join":
                {
                    error = this.game.isLaunched() ? "§cLa partie a déjà été lancée" :
                            !this.game.isOpen() && this.game.getOwner() != this.sender.getUniqueId()  ? "§cLa partie est en privée" :
                            (this.game.getPlayer_list().size() - TeamUtils.getSpectatorsList(this.game).size())  > this.game.getGameCharacteristicValue("player") ? "§cIl n'y a plus de place dans cette partie" :
                            this.game.getPlayer_list().containsKey(this.sender.getUniqueId()) ? "§cVous êtes déjà dans la partie" : null;
                    if(error == null)
                    {
                        error = this.plugin.getGames_list().stream()
                                .anyMatch(game_list -> game_list.getPlayer_list().containsKey(this.sender.getUniqueId())) ? "§cVous avez déjà rejoins une partie" : null;
                    }
                    break;
                }

                case "delete":
                {
                    error = this.game.getOwner() != this.sender.getUniqueId() && !this.sender.hasPermission("domination.animator.use") ? "§cVous ne pouvez pas supprimer la partie des autres" :
                            this.game.isLaunched() ? "§cVous ne pouvez pas supprimer une partie déjà lancé" : null;
                    break;
                }

                case "leave":
                {
                    error = !this.game.getPlayer_list().containsKey(this.sender.getUniqueId()) ? "§cVous n'avez pas encore rejoins cette partie !" : null;
                    break;
                }

                case "load":
                {
                    error = this.game.getMap() == null ? "§cVous ne pouvez pas charger une map vide" :
                            (int) this.game.getGameCharacteristicValue("flag") > this.game.getMap().getFlag_list().size() ? "§cCette map contient uniquement " + this.game.getMap().getFlag_list().size() + " drapeau(x)" :
                            this.game.getMap().isUsed() ? "§cMalheuresement quelqu'un a chargé la map avant vous. Veuillez en reprendre une autre (Premier charger, premier servi)" :
                            this.game.isMap_loaded() ? "§cVous avez déjà chargé la map" :
                            this.game.getOwner() != this.sender.getUniqueId() ? "§cVous devez être le propriétaire de la partie" : null;
                    break;
                }

                case "invite":
                {

                }

                case "start" :
                {
                    error  = this.game.getOwner() != this.sender.getUniqueId() && !this.sender.hasPermission("domination.animator.use") ? "§cVous n'êtes pas le propriétaire de la partie" :
                            this.game.isLaunched() ? "§cCette parti a déjà été lancée" :
                            this.game.getMap() == null ? "Vous devez d'abord choisir une map avant de lancer votre partie" :
                            !this.game.isMap_loaded() ? "§cVeuillez charger votre map" : null;
                    break;
                }

                case "help" :
                {
                    error = null;
                    break;
                }
            }
        }

        if(error == null)
        {
            executeCmd();
        }
        else
        {
            this.sender.sendMessage(error);
        }

    }


        @Override
        public void executeCmd()
        {
            Model_Request request = new Model_Request(this);

        switch(this.command_type)
        {
            case "create" :
            {
                request.create();
                this.sender.sendMessage("§aLa partie " + this.game_name + " a été crée avec succès");
                break;
            }

            case "join" :
            {
                request.join();
                new GameScoreBoard(this.game_name, this.sender, this.plugin).runTaskLater(this.plugin, 0);
                this.sender.sendMessage("§aVous avez rejoins la partie de §e" + Objects.requireNonNull(Bukkit.getPlayer(this.game.getOwner()).getName()));
                break;
            }

            case "delete" :
            {
                request.delete();
                for(UUID player : this.game.getPlayer_list().keySet())
                {
                    Bukkit.getPlayer(player).sendMessage("§cLa partie §e<< " + this.game_name + " >> §cvient d'être supprimé");
                    Bukkit.getPlayer(player).setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                }
                this.sender.sendMessage("§aVous venez de supprimer la partie");
                break;
            }

            case "leave" :
            {
                request.leave();
                new GameScoreBoard(this.game_name, this.sender, this.plugin).runTaskLater(this.plugin, 0);
                this.sender.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                this.sender.sendMessage("§cVous avez quitté la partie !");
                break;
            }

            case "load" :
            {
                request.load();
                new GameScoreBoard(this.game_name ,this.sender, this.plugin).runTaskLater(this.plugin, 0);
                this.sender.sendMessage("§eChargement de la map terminé ! Vous pouvez dès à présent jouer !");
                break;
            }

            case "invite" :
            {
                request.invite();
                break;
            }

            case "help" :
            {
                this.sender.sendMessage("§aVoici la liste des commandes");
                if(this.args.length == 1)
                {
                    request.help();
                }
                if(this.args.length == 2)
                {
                    switch (this.args[1])
                    {
                        case "1" :
                        {
                            request.help();
                            break;
                        }
                        case "2" :
                        {
                            request.help2();
                            break;
                        }
                        case "3" :
                        {
                            request.help3();
                            break;
                        }
                    }
                }
                break;
            }

            case "start" :
            {
                request.start();
                new Launcher(this.game, this.plugin).runTaskTimer(this.plugin, 0, 20);
                break;
            }
        }
    }
}