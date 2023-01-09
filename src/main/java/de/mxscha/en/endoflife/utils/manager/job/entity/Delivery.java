package de.mxscha.en.endoflife.utils.manager.job.entity;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.chat.TextColor;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryPropertys;
import de.mxscha.en.endoflife.utils.manager.location.ConfigLocationUtil;
import de.mxscha.en.endoflife.utils.scoreboard.DefaultScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.HashMap;

import static org.bukkit.Material.*;

public class Delivery extends NPC implements Listener {

    private static final String AccepterName = "§6§lAbgabeort";

    private static final HashMap<Material, Integer> farmerXP = new HashMap<>();
    private static final HashMap<Material, Double> farmerMoney = new HashMap<>();

    public static void registerMoneyMaps() {
        farmerMoney.put(CARROT, 0.20);
        farmerMoney.put(POTATO, 0.20);
        farmerMoney.put(WHEAT, 0.20);
        farmerMoney.put(MELON_SLICE, 0.70);
        farmerMoney.put(HONEY_BLOCK, 2.30);
        farmerMoney.put(PUMPKIN, 1.10);
        farmerMoney.put(SUGAR_CANE, 0.05);
        farmerMoney.put(BEETROOT, 0.20);
        farmerMoney.put(PUMPKIN_SEEDS, 0.01);
        farmerMoney.put(COCOA_BEANS, 0.05);
        farmerMoney.put(MELON_SEEDS, 0.01);
        farmerMoney.put(WHEAT_SEEDS, 0.01);
        farmerMoney.put(BEETROOT_SEEDS, 0.01);
    }

    public static void registerXpMaps() {
        farmerXP.put(HONEY_BLOCK, 3);
        farmerXP.put(PUMPKIN, 2);
        farmerXP.put(CARROT, 1);
        farmerXP.put(POTATO, 1);
        farmerXP.put(WHEAT, 1);
        farmerXP.put(BEETROOT, 1);
        farmerXP.put(MELON_SLICE, 2);
        farmerXP.put(SUGAR_CANE, 1);
        farmerXP.put(COCOA_BEANS, 1);
        farmerXP.put(PUMPKIN_SEEDS, 0);
        farmerXP.put(MELON_SEEDS, 0);
        farmerXP.put(BEETROOT_SEEDS, 0);
        farmerXP.put(WHEAT_SEEDS, 0);
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
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        InventoryView view = event.getView();
        if (view.getTitle().equals("§8» §6§lAbgabe §8» " + TextColor.BLUE_2.get() + "§lAbgabe")) {
            for (int i = 0; i <= inventory.getSize()-1; i++) {
                ItemStack item = inventory.getItem(i);
                if (item == null) continue;
                if (!item.equals(new ItemCreator(GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack())) {
                    if (!item.equals(new ItemCreator(Material.BOOK).setName("§8● " + TextColor.BLUE_2.get() + "§lAbgabe")
                            .setLore("§7Zum Bestätigen auf das Minecart klicken!").toItemStack())) {
                        if (!item.equals(new ItemCreator(Material.CHEST_MINECART).setName("§8● " + TextColor.BLUE_2.get() + "§lBestätigen")
                                .setLore("§7Zum Bestätigen klicken!").toItemStack())) {
                            if (!item.equals(new ItemCreator(Material.REDSTONE).setName("§8● §cZurück").toItemStack())) {
                                player.getInventory().addItem(item);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §6§lAbgabe")) {
            if (!(event.getInventory() == player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case REDSTONE -> {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8● §cSchließen")) {
                        player.closeInventory();
                    }
                    break;
                }
                case HOPPER_MINECART -> {
                    switch (EndoflifeCore.getInstance().getJobAPI().getJob(player)) {
                        case "Farmer":
                            int xp = 0;
                            double money = 0;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack == null) continue;
                                if (farmerXP.containsKey(stack.getType())) {
                                    if (stack.getAmount() > 1) {
                                        int XPstacks = 0;
                                        int Moneystacks = 0;
                                        for (int i = 0; i <= stack.getAmount(); i++) {
                                            XPstacks = i* farmerXP.get(stack.getType());
                                            Moneystacks = i;
                                            player.getInventory().remove(stack);
                                        }
                                        money += Moneystacks*farmerMoney.get(stack.getType());
                                        xp += XPstacks;
                                    } else {
                                        xp += farmerXP.get(stack.getType());
                                        money += farmerMoney.get(stack.getType());
                                        player.getInventory().remove(new ItemCreator(stack.getType()).setAmount(1).toItemStack());
                                    }
                                }
                            }
                            if (xp != 0 || money != 0.00) {
                                EndoflifeCore.getInstance().getJobAPI().addXP(player, xp);
                                EndoflifeCore.getInstance().getMoneyAPI().addMoney(player, money);
                                new DefaultScoreboard(player).update();
                                DecimalFormat f = new DecimalFormat("#0.00");
                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast " + TextColor.GREEN_2.get() + "§l"+xp+"XP §7& §c"+f.format(money)+"€ §7bekommen!");
                            } else
                                player.sendMessage(Messages.PREFIX.get() + "§cDu hast nichts zum verkaufen!");
                            break;
                        case "Arbeitslos":
                            player.closeInventory();
                            player.sendMessage(Messages.PREFIX.get() + "§cDu brauchst einen Job um Items zu verkaufen");
                            break;
                    }
                    break;
                }
                case CHEST_MINECART -> {
                    player.closeInventory();
                    switch (EndoflifeCore.getInstance().getJobAPI().getJob(player)) {
                        case "Farmer":
                            Inventory inventory = Bukkit.createInventory(null, 9*6, "§8» §6§lAbgabe §8» " + TextColor.BLUE_2.get() + "§lAbgabe");
                            InventoryPropertys.fillWithGlass(inventory);
                            InventoryPropertys.addBackButton(inventory);
                            for (int i = 11; i < 16; i++) {
                                inventory.setItem(i, new ItemCreator(Material.AIR).toItemStack());
                            }
                            for (int i = 20; i < 25; i++) {
                                inventory.setItem(i, new ItemCreator(Material.AIR).toItemStack());
                            }
                            for (int i = 29; i < 34; i++) {
                                inventory.setItem(i, new ItemCreator(Material.AIR).toItemStack());
                            }
                            for (int i = 38; i < 43; i++) {
                                inventory.setItem(i, new ItemCreator(Material.AIR).toItemStack());
                            }
                            inventory.setItem(4, new ItemCreator(Material.BOOK).setName("§8● " + TextColor.BLUE_2.get() + "§lAbgabe")
                                    .setLore("§7Zum Bestätigen auf das Minecart klicken!").toItemStack());
                            inventory.setItem(43, new ItemCreator(Material.CHEST_MINECART).setName("§8● " + TextColor.BLUE_2.get() + "§lBestätigen")
                                    .setLore("§7Zum Bestätigen klicken!").toItemStack());
                            player.openInventory(inventory);
                            break;
                        case "Arbeitslos":
                            player.closeInventory();
                            player.sendMessage(Messages.PREFIX.get() + "§cDu brauchst einen Job um Items zu verkaufen");
                            break;
                    }
                    break;
                }
                case PAPER -> {
                    switch (EndoflifeCore.getInstance().getJobAPI().getJob(player)) {
                        case "Farmer":
                            player.closeInventory();
                            int items = 0;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack == null) continue;
                                if (farmerXP.containsKey(stack.getType())) {
                                    if (stack.getAmount() > 1) {
                                        int stacks = 0;
                                        for (int i = 0; i <= stack.getAmount(); i++) {
                                            stacks = i* farmerXP.get(stack.getType());
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
                            break;
                        case "Arbeitslos":
                            player.closeInventory();
                            player.sendMessage(Messages.PREFIX.get() + "§cDu brauchst einen Job um Items zu verkaufen");
                            break;
                    }
                }
            }
        } else if (event.getView().getTitle().equals("§8» §6§lAbgabe §8» " + TextColor.BLUE_2.get() + "§lAbgabe")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (event.getClickedInventory() == player.getInventory()) return;
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
                        int xp = 0;
                        double money = 0.00;
                        for (ItemStack stack : event.getInventory().getContents()) {
                            if (stack == null) continue;
                            if (farmerXP.containsKey(stack.getType())) {
                                if (stack.getAmount() > 1) {
                                    int XPstacks = 0;
                                    int Moneystacks = 0;
                                    for (int i = 0; i <= stack.getAmount(); i++) {
                                        XPstacks = i*farmerXP.get(stack.getType());
                                        Moneystacks = i;
                                        event.getInventory().remove(stack);
                                    }
                                    money += Moneystacks*farmerMoney.get(stack.getType());
                                    xp += XPstacks;
                                } else {
                                    xp += farmerXP.get(stack.getType());
                                    money += farmerMoney.get(stack.getType());
                                    player.getInventory().remove(new ItemCreator(stack.getType()).setAmount(1).toItemStack());
                                }
                            }
                        }
                        if (xp != 0 || money != 0.00) {
                            EndoflifeCore.getInstance().getJobAPI().addXP(player, xp);
                            EndoflifeCore.getInstance().getMoneyAPI().addMoney(player, money);
                            new DefaultScoreboard(player).update();
                            DecimalFormat f = new DecimalFormat("#0.00");
                            player.sendMessage(Messages.PREFIX.get() + "§7Du hast " + TextColor.GREEN_2.get() + "§l"+xp+"XP §7& §a"+f.format(money)+" € §7bekommen!");
                        } else
                            player.sendMessage(Messages.PREFIX.get() + "§cDu hast nichts zum verkaufen!");
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void spawn() {
        if (new ConfigLocationUtil("Accepter").loadLocation() == null) return;
        Location location = new ConfigLocationUtil("Accepter").loadLocation();
        World world = location.getWorld();
        if (world == null) return;
        IronGolem golem = (IronGolem) world.spawnEntity(location, EntityType.IRON_GOLEM);
        golem.setAI(false);
        golem.setInvulnerable(true);
        golem.setSilent(true);
        golem.setCustomName(AccepterName);
        golem.setCustomNameVisible(true);
    }

    @Override
    public void despawn() {
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

    public static HashMap<Material, Double> getFarmerMoney() {
        return farmerMoney;
    }

    public static HashMap<Material, Integer> getFarmerXP() {
        return farmerXP;
    }
}