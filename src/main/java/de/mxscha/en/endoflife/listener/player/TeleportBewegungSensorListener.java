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
        if (event.getFrom().getZ() != event.getTo().getZ() && event.getFrom().getX() != event.getTo().getX()) {
            Set<Player> playerSet = Teleport.getInTeleport();
            Player player = event.getPlayer();
            if(playerSet.contains(player)) {
                Teleport.cancelTeleport(player);
            }
        }

    }

}
