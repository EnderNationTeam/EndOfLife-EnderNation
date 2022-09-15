package de.mxscha.endernationendoflife.utils;

import de.mxscha.endernationendoflife.EndoflifeCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CrateManager {

    private final Player player;
    private static Inventory inventory = null;
    private final List<ItemStack> items;
    private int count;
    private BukkitTask bukkitTask;
    private HashMap<Player, BukkitTask> list = new HashMap<>();

    public CrateManager(Player player, Inventory inventory, List<ItemStack> items, String inventoryName) {
        this.player = player;
        CrateManager.inventory = inventory;
        this.items = items;
        if (canOpen(player)) {
            getDefaultInventory(inventoryName);
            this.bukkitTask = EndoflifeCore.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(EndoflifeCore.getInstance(), new Runnable() {
                @Override
                public void run() {
                    ItemStack nextItem = items.get(new Random().nextInt(items.size()));
                    for (int i = 29; i != 35; i++)
                        moveItem(i);
                    inventory.setItem(34, nextItem);
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 2);
                    count++;
                    checkEnd();
                }
            }, 2L, 3L);
            list.put(player, this.bukkitTask);
        } else {
            player.sendMessage(MessageManager.Prefix + "§cBitte nur eine Truhe gleichzeitig öffnen!");
        }
    }

    private void checkEnd() {
        if (count != 30) return;
        bukkitTask.cancel();
        ItemStack finalItem = inventory.getItem(31);
        player.sendMessage(MessageManager.Prefix + "§7Du hast das Item " + finalItem.getItemMeta().getDisplayName() + "§7 gewonnen!");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
        greenGlass(inventory);
        player.getInventory().addItem(finalItem);
        closeInventory(player);
    }

    private void closeInventory(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.getOpenInventory() == CrateManager.inventory)
                player.closeInventory();
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20*3);
    }

    private void greenGlass(Inventory inventory) {
        for (int i = 0; i < 22; i++) {
            inventory.setItem(i, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        for (int i = 23; i < 29; i++) {
            inventory.setItem(i, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        for (int i = 34; i < 54; i++) {
            inventory.setItem(i, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        inventory.setItem(22, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(29, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(30, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(32, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(33, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
    }

    private Inventory getDefaultInventory(String displayName) {
        inventory = Bukkit.createInventory(null, 9*6, displayName);
        grayGlass(inventory);
        inventory.setItem(22, new ItemCreator(Material.HOPPER).setName("§8● §7Dein Gewinn§8:").toItemStack());
        return inventory;
    }

    private void grayGlass(Inventory inventory) {
        for (int i = 0; i < 22; i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        for (int i = 23; i < 28; i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        for (int i = 35; i < 54; i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    private void moveItem(int slot) {
        if (inventory.getItem(slot) != null && inventory.getItem(slot).getType() != Material.LIME_STAINED_GLASS_PANE)
            inventory.setItem(slot-1, inventory.getItem(slot));
    }

    private boolean canOpen(Player player) {
        return list.containsKey(player);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}