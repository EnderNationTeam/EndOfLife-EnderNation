package de.mxscha.en.endoflife.listener.arena;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * Author: Keksgauner
 * Time: 09.01.2023
 */
public class ArenaAreaListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if (!BuildCommand.getBuild().contains(player)) {
                if(event.getBlock().getType() != Material.FIRE) {
                    event.setCancelled(true);
                }

                Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
                Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();
                if (!EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), arenaPvp1, arenaPvp2)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if (!BuildCommand.getBuild().contains(player)) {
                if(event.getBlock().getType() != Material.FIRE) {
                    event.setCancelled(true);
                }

                Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
                Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();
                if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), arenaPvp1, arenaPvp2)) {
                    removeLiquid(Material.FIRE, event.getBlock());
                } else {
                    event.setCancelled(true);
                }
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
        Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
        Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();

        ItemStack itemStack = event.getItem();

        if(itemStack == null) {
            return;
        }

        Material material = itemStack.getType();
        if((event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && !(
                material.equals(Material.BOW)
                    || material.equals(Material.BOWL)
                    || material.equals(Material.CROSSBOW)
                    || material.equals(Material.EGG)
                    || material.equals(Material.SNOWBALL)
                    || material.equals(Material.ENDER_PEARL)
                    || material.equals(Material.FISHING_ROD)
                )
        ) {
            return;
        }

        if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), arenaPvp1, arenaPvp2)) {
            if((!material.equals(Material.EGG)) || material.equals(Material.WATER_BUCKET) || material.equals(Material.FLINT_AND_STEEL) || material.equals(Material.LAVA_BUCKET) || material.equals(Material.BUCKET)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        removeLiquid(Material.WATER, event.getClickedBlock());
                        removeLiquid(Material.LAVA, event.getClickedBlock());
                    }
                }.runTaskLater(EndoflifeCore.getInstance(), 20*5);
                return;
            }
        }

        if (event.getPlayer().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            Block block = event.getClickedBlock();
            if(block != null && (
                    block.getType().equals(Material.ENDER_CHEST)
                            || block.getType().equals(Material.ENCHANTING_TABLE)
                            || block.getType().equals(Material.ANVIL)
                            || block.getType().equals(Material.GRINDSTONE)
            )) {
                return;
            }

            if (!BuildCommand.getBuild().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void noLiquidFlow(BlockPhysicsEvent event) {
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            // This is thrown when a liquid block is about to flow
            Block block = event.getBlock();
            if(block.getType().equals(Material.WATER) || block.getType().equals(Material.LAVA)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void noLiquidFlow(BlockFromToEvent event) {
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (event.getBlock().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            // This is thrown anytime a liquid flows
            Block block = event.getBlock();
            if(block.getType().equals(Material.WATER) || block.getType().equals(Material.LAVA)) {
                event.setCancelled(true);
            }
        }
    }

    private void removeLiquid(Material material, Block clickedBlock) {

        if(clickedBlock == null) {
            return;
        }

        Location location = clickedBlock.getLocation().add(0, 1, 0);
        Block block = location.getBlock();

        if(block.getType().equals(material)) {
            block.setType(Material.AIR);
        }
    }
}
