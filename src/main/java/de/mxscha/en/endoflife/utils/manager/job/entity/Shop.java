package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;

public class Shop extends NPC {

    private final String name = "§6§lShop";

    @Override
    public void spawn() {
        Location location = new ConfigLocationUtil("Shop").loadLocation();
        if (location == null) return;
        WanderingTrader wanderingTrader = (WanderingTrader) location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
        wanderingTrader.setAI(false);
        wanderingTrader.setInvulnerable(true);
        wanderingTrader.setSilent(true);
        wanderingTrader.setCustomName(name);
        wanderingTrader.setCustomNameVisible(true);
    }

    @Override
    public void despawn() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity != null) {
                    if (entity.isCustomNameVisible()) {
                        if (entity.getCustomName().equals(name)) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }
}
