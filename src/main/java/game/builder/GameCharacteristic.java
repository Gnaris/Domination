package game.builder;

import main.Main;

import java.util.HashMap;
import java.util.Map;

public abstract class GameCharacteristic {

    protected Map<String, Double> game_characteristic = new HashMap<>();
    protected Map<String, Double> min_game_config = new HashMap<>();
    protected Map<String, Double> max_game_config = new HashMap<>();

    protected boolean open;
    protected boolean launched = false;
    protected String map = null;

    public GameCharacteristic(Main plugin)
    {
        for(String key_config : plugin.getConfig().getConfigurationSection("default_game_caracteristique.minimum").getKeys(false))
        {
            this.min_game_config.put(key_config ,plugin.getConfig().getDouble("default_game_caracteristique.minimum." + key_config));
            this.max_game_config.put(key_config ,plugin.getConfig().getDouble("default_game_caracteristique.maximum." + key_config));
            this.game_characteristic.put(key_config, this.min_game_config.get(key_config));
        }

        this.open = plugin.getConfig().getBoolean("default_game_caracteristique.game_opened");
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
