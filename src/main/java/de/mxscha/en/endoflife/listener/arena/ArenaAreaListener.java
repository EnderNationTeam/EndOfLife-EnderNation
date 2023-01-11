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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
        Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
        Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();


        Material material = event.getItem().getType();
        if((event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) && !(
                material.equals(Material.BOW)
                    || material.equals(Material.BOWL)
                    || material.equals(Material.CROSSBOW)
                    || material.equals(Material.BOW)
                    || material.equals(Material.EGG)
                    || material.equals(Material.SNOWBALL)
                    || material.equals(Material.ENDER_PEARL)
                    || material.equals(Material.FISHING_ROD)
                )
        ) {
            return;
        }

        if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), arenaPvp1, arenaPvp2)) {
            if(!material.equals(Material.EGG) || material.equals(Material.WATER_BUCKET) || material.equals(Material.LAVA_BUCKET) || material.equals(Material.BUCKET)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(event.getClickedBlock() != null) {
                            cancel();
                        }
                        Location location = event.getClickedBlock().getLocation().add(0, 1, 0);
                        Block block = location.getBlock();
                        if(block.getType().equals(Material.WATER)) {
                            block.setType(Material.AIR);

                            Location locationPlusX = location.add(1,0,0);
                            Location locationMinusX = location.add(-1,0,0);
                            Location locationPlusZ = location.add(0,0,1);
                            Location locationMinusZ = location.add(0,0,-1);
                            if(locationPlusX.getBlock().getType().equals(Material.WATER)) {
                                locationPlusX.getBlock().setType(Material.AIR);
                            }
                            if(locationMinusX.getBlock().getType().equals(Material.WATER)) {
                                locationMinusX.getBlock().setType(Material.AIR);
                            }
                            if (locationPlusZ.getBlock().getType().equals(Material.WATER)) {
                                locationPlusZ.getBlock().setType(Material.AIR);
                            }
                            if (locationMinusZ.getBlock().getType().equals(Material.WATER)) {
                                locationMinusZ.getBlock().setType(Material.AIR);
                            }
                        }

                        if(block.getType().equals(Material.LAVA)) {
                            block.setType(Material.AIR);

                            Location locationPlusX = location.add(1,0,0);
                            Location locationMinusX = location.add(-1,0,0);
                            Location locationPlusZ = location.add(0,0,1);
                            Location locationMinusZ = location.add(0,0,-1);
                            if(locationPlusX.getBlock().getType().equals(Material.LAVA)) {
                                locationPlusX.getBlock().setType(Material.AIR);
                            }
                            if(locationMinusX.getBlock().getType().equals(Material.LAVA)) {
                                locationMinusX.getBlock().setType(Material.AIR);
                            }
                            if (locationPlusZ.getBlock().getType().equals(Material.LAVA)) {
                                locationPlusZ.getBlock().setType(Material.AIR);
                            }
                            if (locationMinusZ.getBlock().getType().equals(Material.LAVA)) {
                                locationMinusZ.getBlock().setType(Material.AIR);
                            }
                        }
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
}
