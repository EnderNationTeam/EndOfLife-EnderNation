package de.mxscha.en.endoflife.listener.arena;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/*
 * Author: Keksgauner
 * Time: 09.01.2023
 */
public class ArenaPvpEntryListener implements Listener {

    List<Player> list = new ArrayList<>();
    List<Player> runnable = new ArrayList<>();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Location arenaSpawn = new ConfigLocationUtil("ArenaSpawn").loadLocation();

        if (!event.getEntity().getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            return;
        }

        Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
        Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();

        if (event.getEntity() instanceof Player player) {
            if (!BuildCommand.getBuild().contains(player)) {
                if (!EndoflifeCore.getInstance().getRegionManager().isIn(event.getEntity().getLocation(), arenaPvp1, arenaPvp2)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Location arenaSpawn = ArenaMovementTeleportListener.arenaVoid;

        if (!player.getLocation().getWorld().getName().equals(arenaSpawn.getWorld().getName())) {
            if(list.contains(player)) {
                list.remove(player);
            }
            return;
        }

        Location arenaPvp1 = new ConfigLocationUtil("ArenaPvp1").loadLocation();
        Location arenaPvp2 = new ConfigLocationUtil("ArenaPvp2").loadLocation();

        if (!BuildCommand.getBuild().contains(player)) {
            if (EndoflifeCore.getInstance().getRegionManager().isIn(player.getLocation(), arenaPvp1, arenaPvp2)) {
                if (list.contains(player)) {
                    player.setFallDistance(0);

                    if (!player.getLocation().subtract(0, 1, 0).getBlock().getType().isAir()) {
                        if(!runnable.contains(player)) {
                            runnable.add(player);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    list.remove(player);
                                    runnable.remove(player);
                                }
                            }.runTaskLater(EndoflifeCore.getInstance(), 20 * 3);
                        }
                    }
                }
            } else {
                if(!list.contains(player)) {
                    list.add(player);
                }
            }
        }
    }
}
