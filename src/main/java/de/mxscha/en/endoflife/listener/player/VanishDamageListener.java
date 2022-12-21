package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.commands.player.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VanishDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (VanishCommand.getVanishedPlayers().contains(player)) {
                event.setCancelled(true);
            }
        }
    }
}
