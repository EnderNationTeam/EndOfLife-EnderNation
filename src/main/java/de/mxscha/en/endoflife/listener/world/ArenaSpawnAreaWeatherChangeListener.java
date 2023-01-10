package de.mxscha.en.endoflife.listener.world;

import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ArenaSpawnAreaWeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        Location spawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();
        if (spawn == null) return;
        if (event.getWorld() == spawn.getWorld()) {
            event.setCancelled(true);
        }
    }
}