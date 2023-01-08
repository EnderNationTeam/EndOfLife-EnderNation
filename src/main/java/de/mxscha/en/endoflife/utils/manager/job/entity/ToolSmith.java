package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ToolSmith extends NPC implements Listener {

    private final String name = "§9§lSchmied";
    private static final String inventoryName = "§8» §9§lSchmied";
    private Location location;

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Villager smith) {
            if (!smith.isCustomNameVisible()) return;
            if (smith.getCustomName().equals(name)) {
                Player player = event.getPlayer();
                event.setCancelled(true);
                InventoryOpener.open(player, 13);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(inventoryName)) {
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);
        }
    }

    private void close(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory();

            }
        }.runTaskLater(EndoflifeCore.getInstance(), 1);
    }

    @Override
    public void spawn() {
        location = new ConfigLocationUtil("ToolSmith").loadLocation();
        if (location == null) return;
        Villager smith = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        smith.setProfession(Villager.Profession.TOOLSMITH);
        smith.setSilent(true);
        smith.setAI(false);
        smith.setInvulnerable(true);
        smith.setCustomNameVisible(true);
        smith.setCustomName(name);
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

    public static String getInventoryName() {
        return inventoryName;
    }
}
