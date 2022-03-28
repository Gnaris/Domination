package main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class Config {

        // Donner un nom du fichier dans la propriété name_of_file
        // Dans le fichier main, utiliser le constructeur pour récuperer le main;
        // Creer le fichier sous le meme nom du propriété name_of_file comme le config.yml

    public static Main main;

    public static void Init(Main plugin)
    {
        main = plugin;
    }

    public static String GetString(String key)
    {
        return main.getConfig().getString(key);
    }

    public static List<String> GetStringList(String key)
    {
        return main.getConfig().getStringList(key);
    }

    public static int GetInt(String key)
    {
        return main.getConfig().getInt(key);
    }

    public static List<Integer> GetIntList(String key)
    {
        return main.getConfig().getIntegerList(key);
    }

    public static Double GetDouble(String key)
    {
        return main.getConfig().getDouble(key);
    }

    public static List<Double> GetDoubleList(String key)
    {
        return main.getConfig().getDoubleList(key);
    }

    public static List<Float> GetFloatList(String key)
    {
        return main.getConfig().getFloatList(key);
    }

    public static void Set(String key, Object value)
    {
        main.getConfig().set(key, value);
        main.saveConfig();
    }

    public static boolean GetBoolean(String key)
    {
        return main.getConfig().getBoolean(key);
    }

    public static java.util.Set<String> GetKey(String key)
    {
        return Objects.requireNonNull(main.getConfig().getConfigurationSection(key)).getKeys(false);
    }
}
