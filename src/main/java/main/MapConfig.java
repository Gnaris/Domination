package main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class MapConfig {

    // Donner un nom du fichier dans la propriété name_of_file
    // Dans le main fichier utiliser la methode CustomConfig pour récuperer le main;
    // Creer le fichier sous le meme nom du propriété name_of_file comme le config.yml

    public Main main;
    public FileConfiguration config = null;
    public File file = null;

    private final String name_of_file = "map";

    public MapConfig(Main main)
    {
        this.main = main;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if(this.file == null)
        {
            this.file = new File(this.main.getDataFolder(), this.name_of_file + ".yml");
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        InputStream defaultStream = this.main.getResource(this.name_of_file + ".yml");
        if(defaultStream != null)
        {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.config.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig()
    {
        if(this.config == null)
        {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig()
    {
        if(this.config == null || this.file == null) {
            return;
        }
        try {
            this.getConfig().save(this.file);
        } catch (IOException e) {
            this.main.getLogger().log(Level.SEVERE, "Impossible de sauvegarder le fichier de configuration " + this.file + e);
        }
    }

    public void saveDefaultConfig()
    {
        if(this.file == null) {
            this.file = new File(this.main.getDataFolder(), this.name_of_file + ".yml");
        }

        if(!this.file.exists()) {
            this.main.saveResource(this.name_of_file + ".yml", false);
        }
    }
}