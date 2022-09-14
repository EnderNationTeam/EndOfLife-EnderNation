package de.mxscha.endernationendoflife.utils.jobs;

import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Employer implements Listener {

    private final Inventory employer = Bukkit.createInventory(null, 9*5, "§8» §a§lArbeitsgeber");

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!(entity.getType() == EntityType.WANDERING_TRADER)) return;
        WanderingTrader trader = (WanderingTrader) entity;
        if (trader.getCustomName() == null) return;
        if (trader.getCustomName().equals("§a§lArbeitsgeber")) {
            event.setCancelled(true);
            new JobInventory(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §a§lArbeitsgeber")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);

        }
    }

    public static void spawn() {
        try {
            Location location = new ConfigLocationUtil("Employer").loadLocation();
            WanderingTrader wanderingTrader = (WanderingTrader) location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
            wanderingTrader.setAI(false);
            wanderingTrader.setInvulnerable(true);
            wanderingTrader.setSilent(true);
            wanderingTrader.setCustomName("§a§lArbeitsgeber");
            wanderingTrader.setCustomNameVisible(true);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§cDie Employer Location ist nicht gesetzt!");
        }
    }

    public static void despawn() {
        Bukkit.getWorlds().forEach(world -> {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof WanderingTrader) {
                    if (entity.getCustomName() == null) return;
                    if (entity.getCustomName().equals("§a§lArbeitsgeber")) {
                        entity.remove();
                    }
                }
            }
        });
    }
}
