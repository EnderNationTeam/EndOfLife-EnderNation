package de.mxscha.en.endoflife.listener.world;

import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherEvent;

public class SpawnAreaWeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        Location spawn = new ConfigLocationUtil("Spawn").loadLocation();
        if (spawn == null) return;
        if (event.getWorld() == spawn.getWorld()) {
            event.getWorld().setStorm(false);
            event.getWorld().setThundering(false);
            event.getWorld().setWeatherDuration(0);
            event.getWorld().setTime(6000);
            event.setCancelled(true);
        }
    }
}