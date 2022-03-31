package command.parent;

import command.interfaces.CommandExecutor;
import game.Game;
import main.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandController implements CommandExecutor {

    public static class Builder
    {
        protected String command_type;
        protected Player sender;
        protected String game_name;
        protected Game game;
        protected Main config;

        public Builder setCommand_type(String command_type) {
            this.command_type = command_type;
            return this;
        }

        public Builder setSender(Player sender) {
            this.sender = sender;
            return this;
        }

        public Builder setGame_name(String game_name) {
            this.game_name = game_name;
            return this;
        }

        public Builder setConfig(Main config) {
            this.config = config;
            return this;
        }

        public CommandController build()
        {
            CommandController controller = new CommandController();
            controller.setCommand_type(this.command_type);
            controller.setSender(this.sender);
            controller.setGame_name(this.game_name);
            controller.setGame(Main.getGame(this.game_name));
            controller.setConfig(this.config);
            return controller;
        }
    }

    protected String command_type;
    protected Player sender;
    protected String game_name;
    protected Game game;
    protected Main config;

    public void setCommand_type(String command_type) {
        this.command_type = command_type;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setConfig(Main config) {
        this.config = config;
    }

    @Override
    public void checkAndExecuteCommand() {

    }

    @Override
    public void executeCommand() {

    }
}
