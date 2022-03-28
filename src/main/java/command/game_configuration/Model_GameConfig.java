package command.game_configuration;

import command.abstracts.CommandModel;
import game.Game;
import org.bukkit.entity.Player;

public class Model_GameConfig extends CommandModel implements Interface_GameConfig {

    private final int value;

    public Model_GameConfig(Game game, Player sender, int value)
    {
        super(game, sender);
        this.value = value;
    }

    @Override
    public void setPlayer() {
        this.game.setGameCharacteristicValue("player", this.value);
    }

    @Override
    public void setTime() {

    }

    @Override
    public void setRespawnTimer() {

    }

    @Override
    public void setCatchingTimer() {

    }

    @Override
    public void setCatchingSpeed() {

    }

    @Override
    public void setFlag() {

    }

    @Override
    public void setRadius() {

    }

    @Override
    public void setSpawn() {

    }

    @Override
    public void setPoint() {

    }

    @Override
    public void setKillPoint() {

    }

    @Override
    public void setFlagPoint() {

    }

    @Override
    public void setOpen() {

    }

    @Override
    public void setMap() {

    }
}
