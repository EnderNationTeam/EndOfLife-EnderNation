package de.mxscha.endernationendoflife.listener.generall;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ShopAreaListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isInRegion(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isInRegion(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isInRegion(event.getEntity().getLocation(), shopLocation1, shopLocation2)) {
            event.setCancelled(true);
        }
    }
}
