package game.builder;

import main.Main;

import java.util.HashMap;
import java.util.Map;

public abstract class GameCharacteristic {

    protected Map<String, Double> game_characteristic;
    protected Map<String, Double> min_game_config;
    protected Map<String, Double> max_game_config;

    protected boolean open;
    protected boolean launched = false;
    protected String map = null;

    public GameCharacteristic(Main config)
    {
        this.min_game_config = new HashMap<>();
        this.min_game_config.put("player",config.getConfig().getDouble("default_game_caracteristique.minimum.player"));
        this.min_game_config.put("time",config.getConfig().getDouble("default_game_caracteristique.minimum.time"));
        this.min_game_config.put("respawntimer", config.getConfig().getDouble("default_game_caracteristique.minimum.respawntimer"));
        this.min_game_config.put("catchtimer",config.getConfig().getDouble("default_game_caracteristique.minimum.catchtimer"));
        this.min_game_config.put("catchspeed", config.getConfig().getDouble("default_game_caracteristique.minimum.catchspeed"));
        this.min_game_config.put("flag", config.getConfig().getDouble("default_game_caracteristique.minimum.flag"));
        this.min_game_config.put("radius",config.getConfig().getDouble("default_game_caracteristique.minimum.radius"));
        this.min_game_config.put("spawn", config.getConfig().getDouble("default_game_caracteristique.minimum.spawn"));
        this.min_game_config.put("point", config.getConfig().getDouble("default_game_caracteristique.minimum.point"));
        this.min_game_config.put("killpoint", config.getConfig().getDouble("default_game_caracteristique.minimum.killpoint"));
        this.min_game_config.put("flagpoint", config.getConfig().getDouble("default_game_caracteristique.minimum.flagpoint"));

        this.max_game_config = new HashMap<>();
        this.max_game_config.put("player",config.getConfig().getDouble("default_game_caracteristique.maximum.player"));
        this.max_game_config.put("time",config.getConfig().getDouble("default_game_caracteristique.maximum.time"));
        this.max_game_config.put("respawntimer", config.getConfig().getDouble("default_game_caracteristique.maximum.respawntimer"));
        this.max_game_config.put("catchtimer",config.getConfig().getDouble("default_game_caracteristique.maximum.catchtimer"));
        this.max_game_config.put("catchspeed", config.getConfig().getDouble("default_game_caracteristique.maximum.catchspeed"));
        this.max_game_config.put("flag", config.getConfig().getDouble("default_game_caracteristique.maximum.flag"));
        this.max_game_config.put("radius",config.getConfig().getDouble("default_game_caracteristique.maximum.radius"));
        this.max_game_config.put("spawn", config.getConfig().getDouble("default_game_caracteristique.maximum.spawn"));
        this.max_game_config.put("point", config.getConfig().getDouble("default_game_caracteristique.maximum.point"));
        this.max_game_config.put("killpoint", config.getConfig().getDouble("default_game_caracteristique.maximum.killpoint"));
        this.max_game_config.put("flagpoint", config.getConfig().getDouble("default_game_caracteristique.maximum.flagpoint"));

        this.open = config.getConfig().getBoolean("default_game_caracteristique.game_opened");

        this.game_characteristic = new HashMap<>();
        this.game_characteristic.put("player",this.min_game_config.get("player"));
        this.game_characteristic.put("time",this.min_game_config.get("time"));
        this.game_characteristic.put("respawntimer", this.min_game_config.get("respawntimer"));
        this.game_characteristic.put("catchtimer",this.min_game_config.get("catchtimer"));
        this.game_characteristic.put("catchspeed", this.min_game_config.get("catchspeed"));
        this.game_characteristic.put("flag", this.min_game_config.get("flag"));
        this.game_characteristic.put("radius",this.min_game_config.get("radius"));
        this.game_characteristic.put("spawn", this.min_game_config.get("spawn"));
        this.game_characteristic.put("point", this.min_game_config.get("point"));
        this.game_characteristic.put("killpoint", this.min_game_config.get("killpoint"));
        this.game_characteristic.put("flagpoint", this.min_game_config.get("flagpoint"));
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public double getGameCharacteristicValue(String key)
    {
        return this.game_characteristic.get(key);
    }

    public void setGameCharacteristicValue(String key, double value)
    {
        this.game_characteristic.put(key, value);
    }

    public double getMinGameConfigValue(String key)
    {
        return this.min_game_config.get(key);
    }

    public void setMinGameConfigValue(String key, double value)
    {
        this.min_game_config.put(key, value);
    }

    public double getMaxGameConfigValue(String key)
    {
        return this.max_game_config.get(key);
    }

    public void setMaxGameConfigValue(String key, double value)
    {
        this.max_game_config.put(key, value);
    }
}
