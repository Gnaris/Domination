package command.models;

import command.parent.CommandModel;
import main.Main;
import org.bukkit.entity.Player;

public class Model_GameConfigValue extends CommandModel{

    private final int value;

    public Model_GameConfigValue(String game_name, Player sender, int value)
    {
        super(game_name, sender);
        this.value = value;
    }

    public void setPlayer() {
        this.game.setGameCharacteristicValue("player", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setTime() {
        this.game.setGameCharacteristicValue("time", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setRespawnTimer() {
        this.game.setGameCharacteristicValue("respawntimer", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setCatchingTimer() {
        this.game.setGameCharacteristicValue("catchtimer", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setCatchingSpeed() {
        this.game.setGameCharacteristicValue("catchspeed", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setFlag() {
        this.game.setGameCharacteristicValue("flag", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setRadius() {
        this.game.setGameCharacteristicValue("radius", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setSpawn() {
        this.game.setGameCharacteristicValue("spawn", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setPoint() {
        this.game.setGameCharacteristicValue("point", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setKillPoint() {
        this.game.setGameCharacteristicValue("killpoint", this.value);
        Main.game_list.put(this.game_name, this.game);
    }

    public void setFlagPoint() {
        this.game.setGameCharacteristicValue("flagpoint", this.value);
        Main.game_list.put(this.game_name, this.game);
    }
}
