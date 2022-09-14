package de.mxscha.endernationendoflife.utils;

import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;

public class EntityManager {

    public void spawn() {
        try {
            Location shop = new ConfigLocationUtil("Shop").loadLocation();

            spawnShop(shop);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§cDie Entity Locations wurden noch nicht gesetzt!");
        }
    }

    public void despawn() {
        Bukkit.getWorlds().forEach(world -> {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof WanderingTrader) {
                    if (entity.getCustomName() == null) return;
                    if (entity.getCustomName().equals("§6§lShop")) {
                        entity.remove();
                    }
                }
            }
        });
    }

    private void spawnShop(Location location) {
        WanderingTrader wanderingTrader = (WanderingTrader) location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
        wanderingTrader.setAI(false);
        wanderingTrader.setInvulnerable(true);
        wanderingTrader.setSilent(true);
        wanderingTrader.setCustomName("§6§lShop");
        wanderingTrader.setCustomNameVisible(true);
    }
}
