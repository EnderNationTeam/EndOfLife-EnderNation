package de.mxscha.en.endoflife.listener.shop;

import de.mxscha.en.endoflife.EndoflifeCore;
import de.mxscha.en.endoflife.utils.manager.chat.Messages;
import de.mxscha.en.endoflife.utils.manager.item.ItemCreator;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryOpener;
import de.mxscha.en.endoflife.utils.manager.item.inventory.InventoryPropertys;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemFlag;

public class ShopListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!(entity.getType() == EntityType.WANDERING_TRADER)) return;
        WanderingTrader trader = (WanderingTrader) entity;
        if (trader.getCustomName() == null) return;
        if (trader.getCustomName().equals("§6§lShop")) {
            event.setCancelled(true);
            InventoryOpener.open(player, 2);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (event.getView().getTitle().equals("§8» §6§lShop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
                switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§8» §a§lItem Shop" -> InventoryOpener.open(player, 3);
                    case "§8» §5§lKey Shop" -> InventoryOpener.open(player, 4);
                    case "§8» §9§lRollen Shop"-> InventoryOpener.open(player, 5);
                    case "§8● §cSchließen"-> player.closeInventory();
                }
        }
        if (event.getView().getTitle().equals("§8» §5§lKey Shop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 2);
                case "§8● §8§lKohle Key" -> buyKey("coal", player);
                case "§8● §c§lKupfer Key" -> buyKey("copper", player);
                case "§8● §f§lEisen Key" -> buyKey("iron", player);
                case "§8● §6§lGold Key" -> buyKey("gold", player);
                case "§8● §b§lDiamand Key" -> buyKey("diamond", player);
                case "§8● §a§lSmaragt Key" -> buyKey("emerald", player);
                case "§8● §d§lAmethyst Key" -> buyKey("amethyst", player);
                case "§8● §8§lNetherit Key" -> buyKey("netherite", player);
            }
        }
        if (event.getView().getTitle().equals("§8» §a§lItem Shop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 2);
            }
        }
        if (event.getView().getTitle().equals("§8» §9§lRollen Shop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> InventoryOpener.open(player, 2);
            }
        }
    }

    private void buyKey(String key, Player player) {
        switch (key) {
            case "coal" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Coal")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Coal"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §0§lKohle Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "copper" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Copper")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Copper"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §c§lKupfer Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "gold" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Cold")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Gold"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§6§lGold Key").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §6§lGold Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "diamond" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Diamond")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Diamond"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§b§lDiamand Key").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §b§lDiamand Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "emerald" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Emerald")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Emerald"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§a§lSmaragt Key").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §a§lSmaragt Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "amethyst" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Amethyst")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Amethyst"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§d§lAmethyst Key").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §d§lAmethyst Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
            case "netherite" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= InventoryPropertys.getPriceFor("Netherite")) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), InventoryPropertys.getPriceFor("Netherite"));
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lNetherit Key").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast einen §8§lNetherit Key §7gekauft!");
                } else
                    player.sendMessage(Messages.PREFIX.get() + "§cDu hast nicht genügend Geld!");
            }
        }
    }
}
