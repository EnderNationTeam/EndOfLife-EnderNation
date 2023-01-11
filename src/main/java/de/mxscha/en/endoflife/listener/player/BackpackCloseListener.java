package de.mxscha.en.endoflife.listener.player;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.backpack.manager.BackpackManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class BackpackCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getPlayer() instanceof Player player) {
            UUID uuid = player.getUniqueId();
            BackpackManager backpackManager = EndoflifeCore.getInstance().getBackpackManager();
            if(backpackManager.isPlayerExist(uuid)) {
                if(backpackManager.getBackpack(uuid).getInventory().equals(event.getInventory())) {
                    backpackManager.save(uuid); // Speichert. TheEnderHacker und Keksgauner haben so entschieden
                    backpackManager.autoDelete(uuid);
                }
            }
        }
    }
}
