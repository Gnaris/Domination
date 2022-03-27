package command.request;

import command.builder.CommandBuilderExecutor;
import game.Game;
import main.Main;
import org.bukkit.entity.Player;

import java.util.Map;

public class RequestController extends CommandBuilderExecutor {

    private Request request;

    // REQUEST
    public RequestController(String game_name, String type_command, Player sender)
    {
        super(game_name, type_command, sender);
    }

    @Override
    public void checkAndExecuteCommandType()
    {
        switch (this.type_command)
        {
            case "create" :
                if(Main.game_list.containsKey(this.game_name))
                {
                    this.sender.sendMessage("§cLe nom de cette partie existe déjà !");
                    return;
                }
                for(Map.Entry gameList : Main.game_list.entrySet())
                {
                    this.game = (Game) gameList.getValue();
                    if(game.getOwner().equalsIgnoreCase(this.sender.getUniqueId().toString()))
                    {
                        this.sender.sendMessage("§cVous avez déjà créer une partie !");
                        return;
                    }
                }
                break;

            case "join" :
                if(Main.game_list == null || Main.game_list.get(this.game_name) == null)
                {
                    this.sender.sendMessage("§cLa partie que vous souhaiter rejoindre n'existe pas");
                    return;
                }
                //if(Main.game_list.get(this.game_name).get)
        }
        executeTypeCommand();
    }

    @Override
    public void executeTypeCommand()
    {
        this.request = new Request(this.game_name, this.sender);

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