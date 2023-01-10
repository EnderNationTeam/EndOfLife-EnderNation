package de.mxscha.en.endoflife.listener.arena;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.world.BuildCommand;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaMovementTeleportListener implements Listener {

    static Location arenaVoid = null;
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(arenaVoid == null) {
            arenaVoid = new ConfigLocationUtil("ArenaVoid").loadLocation();
        }

        Player player = event.getPlayer();
        if(player.getLocation().getWorld().getName().equals(arenaVoid.getWorld().getName())) {
            if (player.getLocation().getY() < arenaVoid.getY()) {
                if (!BuildCommand.getBuild().contains(player)) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.teleport(new ConfigLocationUtil("ArenaSpawn").loadLocation());
                            player.setFallDistance(0);
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                            player.sendMessage(Messages.PREFIX.get() + "§cDu wurdest zurück teleportiert!");
                        }
                    }.runTaskLater(EndoflifeCore.getInstance(), 1);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onElytra(PlayerMoveEvent event) {
        if(arenaVoid == null) {
            arenaVoid = new ConfigLocationUtil("ArenaVoid").loadLocation();
        }

        Player player = event.getPlayer();
        if(player.getLocation().getWorld().getName().equals(arenaVoid.getWorld().getName())) {
            if (player.isGliding()) {
                if (!BuildCommand.getBuild().contains(player)) {
                    player.setGliding(false);
                }
            }
        }
    }
}
