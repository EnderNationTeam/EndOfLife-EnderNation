package de.mxscha.en.endoflife.listener.shop;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class ShopAreaListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getBlock().getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getBlock().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getBlock().getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(FoodLevelChangeEvent event) {
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getEntity().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getEntity().getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (event.getEntity() instanceof Player player) {
                    if (!BuildCommand.getBuild().contains(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if (EndoflifeCore.getInstance().getRegionManager().isIn(event.getEntity().getLocation(), shopLocation1, shopLocation2)) {
            if (event.getEntity().getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (event.getEntity() instanceof Player player) {
                    if (!BuildCommand.getBuild().contains(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemFrame(PlayerItemFrameChangeEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), shopLocation1, shopLocation2)) {
            if (player.getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location shopLocation1 = new ConfigLocationUtil("ShopRegion1").loadLocation();
        Location shopLocation2 = new ConfigLocationUtil("ShopRegion2").loadLocation();
        Location spawnLocation = new ConfigLocationUtil("Spawn").loadLocation();

        if(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }

        if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), shopLocation1, shopLocation2)) {
            if (player.getLocation().getWorld().getName().equals(spawnLocation.getWorld().getName())) {
                if (!BuildCommand.getBuild().contains(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
