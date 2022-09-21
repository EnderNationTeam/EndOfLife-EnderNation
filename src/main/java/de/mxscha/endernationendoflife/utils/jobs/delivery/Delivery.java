package de.mxscha.endernationendoflife.utils.jobs.delivery;

import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.WanderingTrader;

public class Delivery {

    private static final String AccepterName = "§6§lAbgabeort";

    public static void spawnAccept() {
        if (new ConfigLocationUtil("Accepter").loadLocation() == null) return;
        Location location = new ConfigLocationUtil("Accepter").loadLocation();
        World world = location.getWorld();
        if (world == null) return;
        IronGolem golem = (IronGolem) world.spawnEntity(location, EntityType.IRON_GOLEM);
        golem.setAI(false);
        golem.setInvulnerable(true);
        golem.setSilent(true);
        golem.setCustomName("§6§lAbgabeort");
        golem.setCustomNameVisible(true);
    }

    public static void despawnAccept() {
        Bukkit.getWorlds().forEach(world -> {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof IronGolem) {
                    if (entity.getCustomName() == null) return;
                    if (entity.getCustomName().equals(AccepterName)) {
                        entity.remove();
                    }
                }
            }
        });
    }
}
