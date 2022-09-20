package de.mxscha.endernationendoflife.utils.jobs;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.MessageManager;
import de.mxscha.endernationendoflife.utils.inventory.InventoryOpener;
import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Employer implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!(entity.getType() == EntityType.WANDERING_TRADER)) return;
        WanderingTrader trader = (WanderingTrader) entity;
        if (trader.getCustomName() == null) return;
        if (trader.getCustomName().equals("§a§lArbeitsgeber")) {
            event.setCancelled(true);
            InventoryOpener.open(player, 1);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §a§lJobs")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
                switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§8● §a§lFarmer" -> {

                    }
                    case "" -> {
                        if (!EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()).equals("Farmer")) {

                        } else
                            player.sendMessage(MessageManager.Prefix + "§cDu bist bereits §aFarmer§c!");
                        break;
                    }
                }
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
