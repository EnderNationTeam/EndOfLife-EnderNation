package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.utils.manager.teleport.Teleport;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Set;

public class TeleportBewegungSensorListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        double fromZ = Math.round(event.getFrom().getZ() * 10.0) / 10.0;
        double fromX = Math.round(event.getFrom().getX() * 10.0) / 10.0;
        double toX = Math.round(event.getTo().getX() * 10.0) / 10.0;
        double toZ = Math.round(event.getTo().getZ() * 10.0) / 10.0;

        if (fromZ != toZ && fromX != toX) {
            Set<Player> playerSet = Teleport.getInTeleport();
            Player player = event.getPlayer();
            if(playerSet.contains(player)) {
                Teleport.cancelTeleport(player);
            }
        }

    }

}
