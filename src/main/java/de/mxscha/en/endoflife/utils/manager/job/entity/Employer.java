package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import de.mxscha.en.endoflife.utils.manager.job.Job;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
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
                        if ("Farmer".equals(EndoflifeCore.getInstance().getJobAPI().getJob(player.getUniqueId()))) {
                            player.closeInventory();
                            boolean itemFound = false;
                            for (int i = 0; i <= player.getInventory().getSize(); i++) {
                                ItemStack item = player.getInventory().getItem(i);
                                if (item == null) continue;
                                if (!item.hasItemMeta()) continue;
                                if (item.getItemMeta().getDisplayName().equals("§aFarmer Hacke")) {
                                    itemFound = true;
                                    player.getInventory().remove(item);
                                    player.closeInventory();
                                    EndoflifeCore.getInstance().getJobAPI().fireFromJob(player.getUniqueId(), Job.FARMER);
                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast deinen Job als §aFarmer §cabgelegt§7!");
                                    player.sendMessage(Messages.PREFIX.get() + "§cDu kannst erst in 20 Minuten einen neuen Job nehmen!");
                                    new DefaultScoreboard(player);
                                    if (!cooldown.containsKey(player)) {
                                        cooldown.put(player, System.currentTimeMillis());
                                    }
                                }
                            }
                            if (!itemFound) {
                                player.sendMessage(Messages.PREFIX.get() + "§cWillst du deinen Job wirklich kündigen?");
                                player.sendMessage(Messages.PREFIX.get() + "§cDa du dein Job Werkzeug nicht bei dir trägst,");
                                player.sendMessage(Messages.PREFIX.get() + "§cMusst du dafür aufkommen,");
                                var E = new TextComponent("E"); E.setBold(true); E.setColor(net.md_5.bungee.api.ChatColor.of("#0D2CA6"));
                                var N = new TextComponent("n"); N.setBold(true); N.setColor(net.md_5.bungee.api.ChatColor.of("#113BDF"));
                                var D = new TextComponent("d"); D.setBold(true); D.setColor(net.md_5.bungee.api.ChatColor.of("#0738FB"));
                                var O = new TextComponent("O"); O.setBold(true); O.setColor(net.md_5.bungee.api.ChatColor.of("#C1CBF4"));
                                var F = new TextComponent("f"); F.setBold(true); F.setColor(net.md_5.bungee.api.ChatColor.of("#FFFFFF"));
                                var L = new TextComponent("L"); L.setBold(true); L.setColor(net.md_5.bungee.api.ChatColor.of("#BAFADE"));
                                var I = new TextComponent("i"); I.setBold(true); I.setColor(net.md_5.bungee.api.ChatColor.of("#48E4A0"));
                                var f = new TextComponent("f"); f.setBold(true); f.setColor(net.md_5.bungee.api.ChatColor.of("#2DD58C"));
                                var e = new TextComponent("e"); e.setBold(true); e.setColor(net.md_5.bungee.api.ChatColor.of("#0FBE72"));
                                var middle = new TextComponent(" §8| §f");
                                var idk = new TextComponent("§cWillst du dennoch §8[");
                                var klammer = new TextComponent("§8]§c?");
                                var component = new TextComponent("KÜNDIGEN");
                                component.setBold(true);
                                component.setColor(net.md_5.bungee.api.ChatColor.of("#E32B43"));
                                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Bestätigen §8| §aSchadensersatz§8: §c100€").color(ChatColor.of("#FB1634")).create()));
                                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quit job confirm"));
                                player.sendMessage(E, N, D, O, F, L, I, f, e, middle, idk, component, klammer);
                            }
                        }
                    } else
                        player.sendMessage(Messages.PREFIX.get() + "§cDu hast keinen Job!");
                }
                case "§8● §cSchließen" -> {
                    player.closeInventory();
                }
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8» §a§lFarmer")) {
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
                                player.sendMessage(Messages.PREFIX.get() + "§7Du bist nun §aFarmer§7!");
                            } else {
                                player.sendMessage(Messages.PREFIX.get() + "§cDu hast vor weniger als 20min gekündigt!");
                                player.sendMessage(Messages.PREFIX.get() + "§cDu musst noch §6" + restMinutes(player) + ":" + restSeconds(player) + " §cwarten!");
                                player.sendMessage(Messages.PREFIX.get() + "§cBis du einen neuen Job annehmen kannst!");
                            }
                        } else
                            player.sendMessage(Messages.PREFIX.get() + "§cDu hast bereits einen Job!");
                    } else
                        player.sendMessage(Messages.PREFIX.get() + "§cDu bist bereits §aFarmer§c!");
                    break;
                }
                case "§8● §6Infos" -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 11);
                    break;
                }
            }
        } else if (event.getView().getTitle().equals("§8» §a§lFarmer §8» §6Infos")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if ("§8● §cZurück".equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                InventoryOpener.open(player, 6);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8» §2§lHolzfäller")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if ("§8● §cZurück".equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8» §d§lSchlachter")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if ("§8● §cZurück".equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8» §6§lMinenarbeiter")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if ("§8● §cZurück".equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                InventoryOpener.open(player, 1);
            }
        } else if (event.getView().getTitle().equals("§8» §a§lJobs §8» §9§lFischer")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            if ("§8● §cZurück".equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
                InventoryOpener.open(player, 1);
            }
        }
    }

    private void givePlayerJobTool(Player player, Job job) {
        if (job == Job.FARMER) {
            player.getInventory().addItem(new ItemCreator(Material.DIAMOND_HOE).setName("§aFarmer Hacke").setUnbreakable(true).setLore("§8» §7Level§8: §b1").toItemStack());
        }
    }

    public static void spawn() {
        if (new ConfigLocationUtil("Employer").loadLocation() == null) return;
        Location location = new ConfigLocationUtil("Employer").loadLocation();
        World world = location.getWorld();
        if (world == null) return;
        WanderingTrader wanderingTrader = (WanderingTrader) world.spawnEntity(location, EntityType.WANDERING_TRADER);
        wanderingTrader.setAI(false);
        wanderingTrader.setInvulnerable(true);
        wanderingTrader.setSilent(true);
        wanderingTrader.setCustomName("§a§lArbeitsgeber");
        wanderingTrader.setCustomNameVisible(true);
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
        if (cooldown.containsKey(player)) {
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
