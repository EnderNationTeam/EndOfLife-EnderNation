package de.mxscha.en.endoflife.utils.manager.location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationsConfig {

    static File file = new File("plugins//EndOfLife", "locations.yml");
    static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void save() {
        if (!file.exists()) {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static File getFile() {
        return file;
    }
}
