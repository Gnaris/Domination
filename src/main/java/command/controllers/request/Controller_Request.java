package command.controllers.request;

import command.models.request.Model_Request;
import command.parent.CommandController;
import game.Game;
import game.GameScoreBoard;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Controller_Request extends CommandController {

    public Controller_Request(String command_type, String game_name, Player sender, Main plugin) {
        super(game_name, command_type, sender, plugin);
    }

    public Controller_Request(String command_type, Player sender, Main plugin)
    {
        super(command_type, sender, plugin);
    }

    @Override
    public void ControlCmd()
    {
        if(this.command_type.equalsIgnoreCase("help")) { executeCmd(); }

        if(this.game == null && !this.command_type.equalsIgnoreCase("create"))
        {
            this.sender.sendMessage("§cLe nom de la partie est incorrecte ou inexistant");
            return;
        }

        switch (this.command_type)
        {

            case "create":
            {
                if (this.plugin.getGame_list().containsKey(this.game_name))
                {
                    this.sender.sendMessage("§cLe nom de cette partie existe déjà !");
                    return;
                }
                for (Map.Entry gameList : this.plugin.getGame_list().entrySet())
                {
                    this.game = (Game) gameList.getValue();if (this.game.getOwner() == this.sender.getUniqueId())
                    {
                        this.sender.sendMessage("§cVous avez déjà créer une partie !");
                        return;
                    }
                }
                break;
            }


            case "join":
            {
                if (this.game.isLaunched())
                {
                    this.sender.sendMessage("!cLa partie a déjà été lancée");
                    return;
                }
                if (!this.game.isOpen())
                {
                    this.sender.sendMessage("§cLa partie que vous souhaitez rejoindre est en privée");
                    return;
                }
                if (this.game.getPlayerList().size() > this.game.getGameCharacteristicValue("player"))
                {
                    this.sender.sendMessage("§cLa partie est remplit, vous ne pouvez pas rejoindre la partie");
                    return;
                }
                if (this.game.getPlayerList().contains(this.sender.getUniqueId()))
                {
                    this.sender.sendMessage("§cVous êtes déjà dans la partie !");
                    return;
                }
                for (Map.Entry gameList : this.plugin.getGame_list().entrySet())
                {
                    this.game = (Game) gameList.getValue();
                    if (this.game.getPlayerList().contains(this.sender.getUniqueId()))
                    {
                        this.sender.sendMessage("Vous êtes déjà dans une partie !");return;
                    }
                }
                break;
            }


            case "delete":
            {
                if (this.game.getOwner() != this.sender.getUniqueId() || !this.sender.hasPermission("domination.animator.use"))
                {
                    this.sender.sendMessage("§cVous ne pouvez pas supprimer la partie des autres");
                    return;
                }
                if (this.game.isLaunched() || !this.sender.hasPermission("domination.animator.use"))
                {
                    this.sender.sendMessage("§cLa partie que vous souhaitez supprimer n'existe pas");
                    return;
                }
                break;
            }


            case "leave":
            {
                if (!this.game.getPlayerList().contains(this.sender.getUniqueId()))
                {
                    this.sender.sendMessage("§cVous n'avez pas encore rejoins cette partie !");
                    return;
                }
                break;
            }


            case "load":
            {
                if(this.game.getColiseum() == null)
                {
                    this.sender.sendMessage("§cVous ne pouvez pas charger une map vide");
                    return;
                }
                if(this.game.getColiseum().isUsed())
                {
                    this.sender.sendMessage("§cMalheuresement quelqu'un vous a volé votre map. Veuillez en reprendre une autre");
                    return;
                }
                break;
            }


            case "invite":
            {

            }

            case "start" :
            {

            }
        }
        executeCmd();
    }


        @Override
        public void executeCmd()
        {
            Model_Request request = new Model_Request(this.game_name, this.sender, this.plugin);

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
                new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
                this.sender.sendMessage("§aVous avez rejoins la partie de §e" + Objects.requireNonNull(Bukkit.getPlayer(this.plugin.getGame_list().get(this.game_name).getOwner())).getName());
                break;
            }

            case "delete" :
            {
                request.delete();
                for(UUID player : this.game.getPlayerList())
                {
                    Objects.requireNonNull(Bukkit.getPlayer(player)).sendMessage("§cLa partie §e<< " + this.game_name + " >> §cvient d'être supprimé");
                    Objects.requireNonNull(Bukkit.getPlayer(player)).setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                }
                break;
            }

            case "leave" :
            {
                this.sender.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
                request.leave();
                new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
                this.sender.sendMessage("§cVous avez quitté la partie !");
                break;
            }

            case "load" :
            {
                request.load();
                new GameScoreBoard(this.sender, this.plugin).runTaskLater(this.plugin, 0);
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
                request.help();
                break;
            }

            case "start" :
            {
                request.start();
                break;
            }
        }
    }
}