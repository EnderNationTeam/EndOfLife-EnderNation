package de.mxscha.endernationendoflife.listener;

import de.mxscha.endernationendoflife.utils.CrateManager;
import de.mxscha.endernationendoflife.utils.ItemCreator;
import de.mxscha.endernationendoflife.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrateListener implements Listener {

    private static Inventory inventory;
    List<ItemStack> items;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getClickedBlock() == null) return;
        Player player = event.getPlayer();
        Block crate = event.getClickedBlock();
        Block underCrate = crate.getRelative(BlockFace.DOWN);
        Block bottom = crate.getRelative(BlockFace.DOWN, 2);
        if (crate.getType() == Material.PLAYER_HEAD) {
            if (isBlockCrate(bottom)) {
                if (underCrate.getType() == Material.COAL_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8§lKohle Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §0§lKohle Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "coal");
                        player.openInventory(getDefaultInventory("§8● §8§lKohle Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.WAXED_COPPER_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§c§lKupfer Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §c§lKupfer Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "copper");
                        player.openInventory(getDefaultInventory("§8● §c§lKupfer Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.IRON_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§f§lEisen Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §f§lEisen Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "iron");
                        player.openInventory(getDefaultInventory("§8● §f§lEisen Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.GOLD_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§6§lGold Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §6§lGold Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "gold");
                        player.openInventory(getDefaultInventory("§8● §6§lGold Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.DIAMOND_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§b§lDiamand Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §b§lDiamand Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "diamond");
                        player.openInventory(getDefaultInventory("§8● §b§lDiamand Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.EMERALD_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§a§lSmaragt Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §a§lSmaragt Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "emerald");
                        player.openInventory(getDefaultInventory("§8● §a§lSmaragt Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.AMETHYST_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§d§lAmethyst Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §d§lAmethyst Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "amethyst");
                        player.openInventory(getDefaultInventory("§8● §d§lAmethyst Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
                if (underCrate.getType() == Material.NETHERITE_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.AIR || !player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§8§lNetherit Key")) {
                        player.sendMessage(MessageManager.Prefix + "§cNutze einen §8§lNetherit Key §cum diese Box zu öffnen!");
                    } else {
                        items = new ArrayList<>();
                        items.add(new ItemCreator(Material.DIAMOND).setName("1").toItemStack());
                        items.add(new ItemCreator(Material.IRON_INGOT).setName("2").toItemStack());
                        items.add(new ItemCreator(Material.GOLD_INGOT).setName("3").toItemStack());
                        removeKey(player, "netherite");
                        player.openInventory(getDefaultInventory("§8● §8§lNetherit Box"));
                        new CrateManager(player, inventory, items);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8● §8§lKohle Box")
                || event.getView().getTitle().equals("§8● §c§lKupfer Box")
                || event.getView().getTitle().equals("§8● §f§lEisen Box")
                || event.getView().getTitle().equals("§8● §6§lGold Box")
                || event.getView().getTitle().equals("§8● §b§lDiamand Box")
                || event.getView().getTitle().equals("§8● §a§lSmaragt Box")
                || event.getView().getTitle().equals("§8● §d§lAmethyst Box")
                || event.getView().getTitle().equals("§8● §8§lNetherit Box") ) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
        }
    }

    private void removeKey(Player player, String type) {
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        player.updateInventory();
    }

    private boolean isBlockCrate(Block under) {
        return under.getType() == Material.REINFORCED_DEEPSLATE;
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

    private Inventory getDefaultInventory(String displayName) {
        inventory = Bukkit.createInventory(null, 9*6, displayName);
        grayGlass(inventory);
        inventory.setItem(22, new ItemCreator(Material.HOPPER).setName("§8● §7Dein Gewinn§8:").toItemStack());
        return inventory;
    }
}