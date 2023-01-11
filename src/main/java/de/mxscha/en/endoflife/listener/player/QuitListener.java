package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.commands.player.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        if (VanishCommand.getVanishedPlayers().contains(player)) {
            VanishCommand vanishCommand = new VanishCommand();
            vanishCommand.setUnVanished(player);
        }

        EndoflifeCore.getInstance().getBackpackManager().saveAndDelete(event.getPlayer().getUniqueId());
    }
}
