package de.mxscha.endernationendoflife.utils.jobs;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.ItemCreator;
import de.mxscha.endernationendoflife.utils.MessageManager;
import de.mxscha.endernationendoflife.utils.inventory.InventoryOpener;
import de.mxscha.endernationendoflife.utils.locations.ConfigLocationUtil;
import de.mxscha.endernationendoflife.utils.scoreboard.DefaultScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Employer implements Listener {

    private static final HashMap<Player, Long> cooldown = new HashMap<>();

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
                    player.closeInventory();
                    InventoryOpener.open(player, 6);
                }
                case "§8● §2§lHolzfäller" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 7);
                }
                case "§8● §d§lSchlachter" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 8);
                }
                case "§8● §6§lMinenarbeiter" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 9);
                }
                case "§8● §9§lFischer" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 10);
                }
                case "§8● §cKündigen" -> {
                    if (!EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()).equals("Arbeitslos")) {
                        switch (EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId())) {
                            case "Farmer" -> {
                                boolean itemFound = false;
                                for (ItemStack contents : player.getInventory().getContents()) {
                                    if (contents == null) return;
                                    if (contents.getItemMeta().getDisplayName().equals("§aFarmer Hacke")) {
                                        itemFound = true;
                                        player.getInventory().remove(contents);
                                        player.closeInventory();
                                        EndoflifeCore.getInstance().getJobAPI().fireFromJob(player.getUniqueId(), Job.FARMER);
                                        player.sendMessage(MessageManager.Prefix + "§7Du hast deinen Job als §aFarmer §cabgelegt§7!");
                                        player.sendMessage(MessageManager.Prefix + "§cDu kannst erst in 20 Minuten einen neuen Job nehmen!");
                                        new DefaultScoreboard(player);
                                        if (!cooldown.containsKey(player)) {
                                            cooldown.put(player, System.currentTimeMillis());
                                        }
                                    }
                                }
                                if (!itemFound)
                                    player.sendMessage(MessageManager.Prefix + "§cBitte bring deine §aFarmer Hacker §cmit!");
                            }
                        }
                    } else
                        player.sendMessage(MessageManager.Prefix + "§cDu hast keinen Job!");
                }
                case "§8● §cSchließen" -> {
                    player.closeInventory();
                }
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8● §a§lFarmer")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> {
                    InventoryOpener.open(player, 1);
                    break;
                }
                case "§8● §aAnnehmen" -> {
                    if (!EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()).equals("Farmer")) {
                        if (EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()).equals("Arbeitslos")) {
                            if (canAgree(player)) {
                                EndoflifeCore.getInstance().getJobAPI().setJob(player.getUniqueId(), Job.FARMER);
                                new DefaultScoreboard(player);
                                player.closeInventory();
                                givePlayerJobTool(player, Job.FARMER);
                                player.sendMessage(MessageManager.Prefix + "§7Du bist nun §aFarmer§7!");
                            } else {
                                player.sendMessage(MessageManager.Prefix + "§cDu hast vor weniger als 20 Minuten einen Job gekündigt!");
                                player.sendMessage(MessageManager.Prefix + "§cDu musst noch §6" + restMinutes(player) + ":" + restSeconds(player) + " §cwarten!");
                            }
                        } else
                            player.sendMessage(MessageManager.Prefix + "§cDu hast bereits einen Job!");
                    } else
                        player.sendMessage(MessageManager.Prefix + "§cDu bist bereits §aFarmer§c!");
                    break;
                }
                case "§8● §6Infos" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 11);
                    break;
                }
            }
        } else if (event.getView().getTitle().equals("§8» §a§lFarmer §8● §6Infos")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 6);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8● §2§lHolzfäller")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8● §d§lSchlachter")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8● §6§lMinenarbeiter")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8● §9§lFischer")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 1);
            }
        }
    }

    private void givePlayerJobTool(Player player, Job job) {
        switch (job) {
            case FARMER -> player.getInventory().addItem(new ItemCreator(Material.DIAMOND_HOE).setName("§aFarmer Hacke").setUnbreakable(true).setLore("§8» §7Level§8: §b1").toItemStack());
        }
    }

    /*
    case "" -> {
                        if (!EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()).equals("Farmer")) {

                        } else
                            player.sendMessage(MessageManager.Prefix + "§cDu bist bereits §aFarmer§c!");
                        break;
                    }
     */

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

    private long getTimeElapsed(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    private long convertTimeMillis(long minutes) {
        minutes *= 60; // seconds
        return minutes * 1000; // milliseconds
    }

    private boolean canAgree(Player player) {
        if(cooldown.containsKey(player)) {
            return getTimeElapsed(cooldown.get(player)) >= convertTimeMillis(20);
        }
        return true;
    }

    private long getTimeToWait(long minutes, long timeElapsed) {
        return convertTimeMillis(minutes) - timeElapsed;
    }

    private long restMinutes(Player player) {
        long milliseconds = getTimeToWait(20, getTimeElapsed(cooldown.get(player)));
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        return minutes;
    }


    private long restSeconds(Player player) {
        long milliseconds = getTimeToWait(20, getTimeElapsed(cooldown.get(player)));
        long seconds = milliseconds / 1000;
        return seconds % 60;
    }
}
