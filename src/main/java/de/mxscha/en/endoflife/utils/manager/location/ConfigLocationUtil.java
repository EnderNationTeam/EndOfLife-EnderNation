package de.mxscha.en.endoflife.utils.manager.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLocationUtil {

    private Location location;
    private String root;

    public ConfigLocationUtil(Location location, String root) {
        this.location = location;
        this.root = root;
    }

    public ConfigLocationUtil(String root) {
        this(null, root);
    }

    public void saveLocation() {
        FileConfiguration config = LocationsConfig.getConfig();
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        LocationsConfig.save();
    }

    public Location loadLocation() {
        FileConfiguration config = LocationsConfig.getConfig();
        if (config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root + ".X"),
                    y = config.getDouble(root + ".Y"),
                    z = config.getDouble(root + ".Z");
            float yaw = (float) config.getDouble(root + ".Yaw");
            float pitch = (float) config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        } else
            return null;
    }
}
