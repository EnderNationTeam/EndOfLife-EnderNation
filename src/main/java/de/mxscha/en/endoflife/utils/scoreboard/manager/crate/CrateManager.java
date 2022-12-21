package de.mxscha.en.endoflife.utils.scoreboard.manager.crate;

import de.mxscha.en.endoflife.utils.scoreboard.manager.item.ItemCreator;
import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.listener.player.CrateListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Random;

public class CrateManager {

    private final Player player;
    private static Inventory inventory = null;
    private List<ItemStack> items;
    private int count;
    private BukkitTask bukkitTask;

    public CrateManager(Player player, List<ItemStack> items, String inventoryName) {
        this.player = player;
        CrateManager.inventory = getDefaultInventory(inventoryName);
        player.openInventory(inventory);
        this.items = items;
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
    }

    private void checkEnd() {
        if (count != 30) return;
        bukkitTask.cancel();
        ItemStack finalItem = inventory.getItem(31);
        if (finalItem != null) {
            // Zu Components

            Component E = Component.text("§lE").color(TextColor.fromHexString("#0D2CA6"));
            Component n = Component.text("§ln").color(TextColor.fromHexString("#113BDF"));
            Component d = Component.text("§ld").color(TextColor.fromHexString("#0738FB"));
            Component O = Component.text("§lO").color(TextColor.fromHexString("#C1CBF4"));
            Component F = Component.text("§lf").color(TextColor.fromHexString("#FFFFFF"));
            Component L = Component.text("§lL").color(TextColor.fromHexString("#BAFADE"));
            Component i = Component.text("§li").color(TextColor.fromHexString("#48E4A0"));
            Component f = Component.text("§lf").color(TextColor.fromHexString("#2DD58C"));
            Component e = Component.text("§le").color(TextColor.fromHexString("#0FBE72"));
            Component middle = Component.text(" §8| ");
            Component item = finalItem.displayName().color(NamedTextColor.DARK_AQUA);
            Component text = Component.text("Du hast das Item §l").color(NamedTextColor .GRAY);
            Component win = Component.text(" gewonnen!").color(NamedTextColor.GRAY);
            Component finalText = E.append(n).append(d).append(O).append(F).append(L).append(i).append(f).append(e).append(middle).append(text).append(item).append(win);
            player.sendMessage(finalText);
            player.getInventory().addItem(finalItem);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
        greenGlass(inventory);
        closeInventory(player);
        CrateListener.getCooldown().remove(player);
    }

    private void closeInventory(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getOpenInventory().equals(CrateManager.inventory))
                    player.closeInventory();
            }
        }.runTaskLater(EndoflifeCore.getInstance(), 20 * 3);
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
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, displayName);
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
            inventory.setItem(slot - 1, inventory.getItem(slot));
    }

    public static Inventory getInventory() {
        return inventory;
    }
}