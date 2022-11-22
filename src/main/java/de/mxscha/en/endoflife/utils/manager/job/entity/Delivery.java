package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.chat.TextColor;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Material.*;

public class Delivery implements Listener {

    private static final String AccepterName = "§6§lAbgabeort";

    private static final HashMap<Material, Integer> farmer = new HashMap<>();

    public static void registerMaps() {
        farmer.put(CARROT, 1);
        farmer.put(POTATO, 1);
        farmer.put(WHEAT, 1);
        farmer.put(MELON, 1);
        farmer.put(BAMBOO, 1);
        farmer.put(KELP, 1);
        farmer.put(SUGAR_CANE, 1);
        farmer.put(PUMPKIN_SEEDS, 1);
        farmer.put(MELON_SEEDS, 1);
        farmer.put(WHEAT_SEEDS, 1);
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!(entity.getType() == EntityType.IRON_GOLEM)) return;
        IronGolem trader = (IronGolem) entity;
        if (trader.getCustomName() == null) return;
        if (trader.getCustomName().equals("§6§lAbgabeort")) {
            event.setCancelled(true);
            InventoryOpener.open(player, 12);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §6§lAbgabe")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case REDSTONE -> {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §cSchließen")) {
                        player.closeInventory();
                    }
                    break;
                }
                case HOPPER_MINECART -> {
                    int xp = 0;
                    for (ItemStack stack : player.getInventory().getContents()) {
                        if (stack == null) continue;
                        if (farmer.containsKey(stack.getType())) {
                            if (stack.getAmount() > 1) {
                                int stacks = 0;
                                for (int i = 0; i <= stack.getAmount(); i++) {
                                    stacks = i*farmer.get(stack.getType());
                                    player.getInventory().remove(stack);
                                }
                                xp += stacks;
                            } else {
                                player.getInventory().remove(stack);
                                xp += 1;
                            }
                        }
                    }
                    if (xp != 0) {
                        EndoflifeCore.getInstance().getJobAPI().addXP(player.getUniqueId(), xp);
                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast " + TextColor.GREEN_2.get() + "§l" + xp + "XP §7& §c0€ §7bekommen!");
                    } else
                        player.sendMessage(Messages.PREFIX.get() + "§cDu hast nichts zum verkaufen!");
                    break;
                }
                case CHEST_MINECART -> {
                    player.closeInventory();
                    InventoryOpener.open(player, 13);
                    break;
                }
                case PAPER -> {
                    player.closeInventory();
                    int items = 0;
                    for (ItemStack stack : player.getInventory().getContents()) {
                        if (stack == null) continue;
                        if (farmer.containsKey(stack.getType())) {
                            if (stack.getAmount() > 1) {
                                int stacks = 0;
                                for (int i = 0; i <= stack.getAmount(); i++) {
                                    stacks = i*farmer.get(stack.getType());
                                }
                                items += stacks;
                            } else
                                items += 1;
                        }
                    }
                    Component E = Component.text("§lE").color(net.kyori.adventure.text.format.TextColor.fromHexString("#0D2CA6"));
                    Component n = Component.text("§ln").color(net.kyori.adventure.text.format.TextColor.fromHexString("#113BDF"));
                    Component d = Component.text("§ld").color(net.kyori.adventure.text.format.TextColor.fromHexString("#0738FB"));
                    Component O = Component.text("§lO").color(net.kyori.adventure.text.format.TextColor.fromHexString("#C1CBF4"));
                    Component F = Component.text("§lf").color(net.kyori.adventure.text.format.TextColor.fromHexString("#FFFFFF"));
                    Component L = Component.text("§lL").color(net.kyori.adventure.text.format.TextColor.fromHexString("#BAFADE"));
                    Component i = Component.text("§li").color(net.kyori.adventure.text.format.TextColor.fromHexString("#48E4A0"));
                    Component f = Component.text("§lf").color(net.kyori.adventure.text.format.TextColor.fromHexString("#2DD58C"));
                    Component e = Component.text("§le").color(net.kyori.adventure.text.format.TextColor.fromHexString("#0FBE72"));
                    Component middle = Component.text(" §8| ");
                    Component itemsInInventory = Component.text("§l" + items, net.kyori.adventure.text.format.TextColor.fromCSSHexString("#2DD58C"));
                    Component text = Component.text("§7Du hast ");
                    Component sell = Component.text(" §7verkaufbare Items im Inventar!");
                    Component finalText = E.append(n).append(d).append(O).append(F).append(L).append(i).append(f).append(e).append(middle).append(text).append(itemsInInventory).append(sell);
                    player.sendMessage(finalText);

                }
            }
        } else if (event.getView().getTitle().equals("§8» §6§lAbgabe §8» " + TextColor.BLUE_2.get() + "§lAbgabe")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case REDSTONE -> {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §cZurück")) {
                        player.closeInventory();
                        InventoryOpener.open(player, 12);
                    }
                    break;
                }
                case CHEST_MINECART -> {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● " + TextColor.BLUE_2.get() + "§lBestätigen")) {
                        ArrayList<ItemStack> items = new ArrayList<>();
                        for (int i = 11; i < 16; i++) {
                            ItemStack item = event.getInventory().getItem(i);
                            if (item == null) continue;
                            items.add(item);
                            event.getInventory().remove(item);
                        }
                        for (int i = 20; i < 25; i++) {
                            ItemStack item = event.getInventory().getItem(i);
                            if (item == null) continue;
                            items.add(item);
                            event.getInventory().remove(item);
                        }
                        for (int i = 29; i < 34; i++) {
                            ItemStack item = event.getInventory().getItem(i);
                            if (item == null) continue;
                            items.add(item);
                            event.getInventory().remove(item);
                        }
                        for (int i = 38; i < 43; i++) {
                            ItemStack item = event.getInventory().getItem(i);
                            if (item == null) continue;
                            items.add(item);
                            event.getInventory().remove(item);
                        }
                        player.closeInventory();
                        int item = 0;
                        for (ItemStack stack : items) {
                            if (stack == null) continue;
                            if (farmer.containsKey(stack.getType())) {
                                if (stack.getAmount() > 1) {
                                    int stacks = 0;
                                    for (int i = 0; i <= stack.getAmount(); i++) {
                                        stacks = i * farmer.get(stack.getType());
                                    }
                                    item += stacks;
                                } else
                                    item += 1;
                            } else {
                                items.remove(stack);
                                player.getInventory().addItem(stack);
                            }
                        }
                        if (item != 0) {
                            EndoflifeCore.getInstance().getJobAPI().addXP(player.getUniqueId(), item);
                            player.sendMessage(Messages.PREFIX.get() + "§7Du hast " + TextColor.GREEN_2.get() + "§l" + item + "XP §7& §c0€ §7bekommen!");
                        } else
                            player.sendMessage(Messages.PREFIX.get() + "§cDu hast nichts zum verkaufen!");
                    }
                }
            }
        }
    }

    public static void spawnAccept() {
        if (new ConfigLocationUtil("Accepter").loadLocation() == null) return;
        Location location = new ConfigLocationUtil("Accepter").loadLocation();
        World world = location.getWorld();
        if (world == null) return;
        IronGolem golem = (IronGolem) world.spawnEntity(location, EntityType.IRON_GOLEM);
        golem.setAI(false);
        golem.setInvulnerable(true);
        golem.setSilent(true);
        golem.setCustomName("§6§lAbgabeort");
        golem.setCustomNameVisible(true);
    }

    public static void despawnAccept() {
        Bukkit.getWorlds().forEach(world -> {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof IronGolem) {
                    if (entity.getCustomName() == null) return;
                    if (entity.getCustomName().equals(AccepterName)) {
                        entity.remove();
                    }
                }
            }
        });
    }
}