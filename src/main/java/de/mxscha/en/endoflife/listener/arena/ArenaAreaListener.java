package de.mxscha.en.endoflife.listener.arena;

import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ArenaAreaListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemFrame(PlayerItemFrameChangeEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getPlayer().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getPlayer().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            Block block = event.getClickedBlock();
            if(block != null && (block.getType().equals(Material.ENDER_CHEST) || block.getType().equals(Material.ENCHANTING_TABLE))) {
                return;
            }

            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
