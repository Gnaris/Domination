package command.request;

import command.builder.CommandBuilderExecutor;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class GameRequestController extends CommandBuilderExecutor {

    private GameRequest request;

    // REQUEST
    public GameRequestController(String game_name, String type_command, Player sender)
    {
        super(game_name, type_command, sender);
    }

    @Override
    public void checkAndExecuteCommandType()
    {
        switch (this.type_command)
        {
            case "create" :
                if(Main.game_list.containsKey(this.game_name)) {this.sender.sendMessage("§cLe nom de cette partie existe déjà !");return;}
                for(Map.Entry gameList : Main.game_list.entrySet())
                {
                    this.game = (Game) gameList.getValue();
                    if(this.game.getOwner().equalsIgnoreCase(this.sender.getUniqueId().toString())) {this.sender.sendMessage("§cVous avez déjà créer une partie !");return;}
                }
                break;

            case "join" :
                if(Main.game_list == null || Main.getGame(this.game_name) == null) {this.sender.sendMessage("§cLa partie que vous souhaiter rejoindre n'existe pas"); return;}
                if(Main.getGame(this.game_name).isLaunched()) { this.sender.sendMessage("!cLa partie que vous souhaitez rejoindre a déjà été lancée"); return; }
                if(!Main.getGame(this.game_name).isOpen()) { this.sender.sendMessage("§cLa partie que vous souhaitez rejoindre est en privée"); return;}
                if(Main.getGame(this.game_name).getPlayerList().size() >= Main.getGame(this.game_name).getPlayer()) {this.sender.sendMessage("§cLa partie est remplit, vous ne pouvez plus rejoindre la partie"); return;}
                if(Main.getGame(this.game_name).getPlayerList().contains(this.sender.getUniqueId())){this.sender.sendMessage("§cVous êtes déjà dans la partie !"); return;}
                for(Map.Entry gameList : Main.game_list.entrySet())
                {
                    this.game = (Game) gameList.getValue();
                    if(this.game.getPlayerList().contains(this.sender.getUniqueId())){this.sender.sendMessage("Vous êtes déjà dans une partie !"); return;}
                }
                break;

            case "delete" :
                
                break;

        }
        executeTypeCommand();
    }

    @Override
    public void executeTypeCommand()
    {
        this.request = new GameRequest(this.game_name, this.sender);

        switch(this.type_command)
        {
            case "create" : this.request.create(); break;
            case "join" : this.request.join(); break;
            case "delete" : this.request.delete(); break;
            case "leave" : this.request.leave(); break;
            case "load" : this.request.load(); break;
            case "invite" : this.request.invite(); break;
        }
    }
}