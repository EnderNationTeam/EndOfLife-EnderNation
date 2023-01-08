package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Random;

public class RandomTeleport extends NPC implements Listener {

    private final String name = "§5§lRandom Teleport";
    private Location location;

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Enderman rtp) {
            if (!rtp.isCustomNameVisible()) return;
            if (rtp.getCustomName().equals(name)) {
                Player player = event.getPlayer();
                event.setCancelled(true);
                if (event.getHand() != EquipmentSlot.HAND) return;
                    String world = "EndOfLifeV4";
                    World eol = Bukkit.getWorld(world);
                    if (eol != null) {
                        int x = new Random().nextInt(2500);
                        int z = new Random().nextInt(2500);
                        int height = eol.getHighestBlockYAt(x, z);
                        player.teleport(new Location(eol, x, height, z));
                    } else
                        player.sendMessage(Messages.PREFIX.get() + "§cDer Random Teleport ist derzeit in Arbeit!");
            }
        }
    }

    @Override
    public void spawn() {
        location = new ConfigLocationUtil("RandomTeleport").loadLocation();
        if (location == null) return;
        Enderman rtp = (Enderman) location.getWorld().spawnEntity(location, EntityType.ENDERMAN);
        rtp.setCustomNameVisible(true);
        rtp.setCustomName(name);
        rtp.setInvulnerable(true);
        rtp.setAI(false);
        rtp.setSilent(true);
        rtp.setAI(false);
    }

    @Override
    public void despawn() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity != null) {
                    if (entity.isCustomNameVisible()) {
                        if (entity.getCustomName().equals(name)) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }
}
