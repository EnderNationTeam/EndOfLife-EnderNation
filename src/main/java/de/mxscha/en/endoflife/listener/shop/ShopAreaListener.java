package de.mxscha.en.endoflife.listener.shop;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.scoreboard.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class ShopAreaListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(FoodLevelChangeEvent event) {
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getEntity().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getEntity() instanceof Player player) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getEntity().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getEntity() instanceof Player player) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
