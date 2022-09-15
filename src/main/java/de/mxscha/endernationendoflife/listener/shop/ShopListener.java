package de.mxscha.endernationendoflife.listener.shop;

import de.mxscha.endernationendoflife.EndoflifeCore;
import de.mxscha.endernationendoflife.utils.ItemCreator;
import de.mxscha.endernationendoflife.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopListener implements Listener {

    private final Inventory shop = Bukkit.createInventory(null, 9*5, "§8» §6§lShop");
    private final Inventory itemShop = Bukkit.createInventory(null, 9*5, "§8» §a§lItem Shop");
    private final Inventory keyShop = Bukkit.createInventory(null, 9*5, "§8» §5§lKey Shop");
    private final Inventory effectShop = Bukkit.createInventory(null, 9*5, "§8» §9§lEffekt Shop");
    int priceForCoal = 1;// €
    int priceForCopper = 2;// €
    int priceForIron = 3;// €
    int priceForGold = 4;// €
    int priceForDiamond = 5;// €
    int priceForEmerald = 6;// €
    int priceForAmethyst = 7;// €
    int priceForNetherite = 8;// €

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (!(entity.getType() == EntityType.WANDERING_TRADER)) return;
        WanderingTrader trader = (WanderingTrader) entity;
        if (trader.getCustomName() == null) return;
        if (trader.getCustomName().equals("§6§lShop")) {
            event.setCancelled(true);
            openShop(player);
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
                    case "§8» §a§lItem Shop" -> openItemShop(player);
                    case "§8» §5§lKey Shop" -> openKeyShop(player);
                    case "§8» §9§lEffekt Shop"-> openEffectShop(player);
                    case "§8● §cSchließen"-> player.closeInventory();
                }
        }
        if (event.getView().getTitle().equals("§8» §5§lKey Shop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> openShop(player);
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
                case "§8● §cZurück" -> openShop(player);
            }
        }
        if (event.getView().getTitle().equals("§8» §9§lEffekt Shop")) {
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasDisplayName()) return;
            if (!event.getInventory().equals(player.getInventory()))
                event.setCancelled(true);
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8● §cZurück" -> openShop(player);
            }
        }
    }

    private void buyKey(String key, Player player) {
        switch (key) {
            case "coal" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForCoal) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForCoal);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lKohle Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §0§lKohle Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "copper" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForCopper) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForCopper);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§c§lKupfer Key").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §c§lKupfer Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "iron" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForIron) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForIron);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§f§lEisen Key").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §f§lEisen Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "gold" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForGold) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForGold);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§6§lGold Key").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §6§lGold Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "diamond" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForDiamond) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForDiamond);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§b§lDiamand Key").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §b§lDiamand Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "emerald" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForEmerald) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForEmerald);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§a§lSmaragt Key").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §a§lSmaragt Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "amethyst" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForAmethyst) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForAmethyst);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§d§lAmethyst Key").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §d§lAmethyst Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
            case "netherite" -> {
                if (EndoflifeCore.getInstance().getMoneyAPI().getMoney(player.getUniqueId()) >= priceForNetherite) {
                    EndoflifeCore.getInstance().getMoneyAPI().removeMoney(player.getUniqueId(), priceForNetherite);
                    player.getInventory().addItem(new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8§lNetherit Key").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
                    player.sendMessage(MessageManager.Prefix + "§7Du hast einen §8§lNetherit Key §7gekauft!");
                } else
                    player.sendMessage(MessageManager.Prefix + "§cDu hast nicht genügend Geld!");
            }
        }
    }

    private void openShop(Player player) {
        player.openInventory(shop);
        fillWithGlass(shop);
        shop.setItem(20, new ItemCreator(Material.ENDER_CHEST).setName("§8» §a§lItem Shop")
                .toItemStack());
        shop.setItem(22, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8» §5§lKey Shop")
                .addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        shop.setItem(24, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8» §9§lEffekt Shop")
                .addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        // addExitButton(shop);
    }

    private void openItemShop(Player player) {
        player.openInventory(itemShop);
        fillWithGlass(itemShop);
        // addBackButton(itemShop);
    }

    private void openKeyShop(Player player) {
        player.openInventory(keyShop);
        fillWithGlass(keyShop);
        keyShop.setItem(10, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lKohle Key").setLore("§8» §aPreis§8: §c"+priceForCoal+"€", "§8» §7Nutze diesen Schlüssel um die §8§lKohle Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(12, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §c§lKupfer Key").setLore("§8» §aPreis§8: §c"+priceForCopper+"€", "§8» §7Nutze diesen Schlüssel um die §c§lKupfer Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(14, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §f§lEisen Key").setLore("§8» §aPreis§8: §c"+priceForIron+"€", "§8» §7Nutze diesen Schlüssel um die §f§lEisen Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(16, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §6§lGold Key").setLore("§8» §aPreis§8: §c"+priceForGold+"€", "§8» §7Nutze diesen Schlüssel um die §6§lGold Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(28, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §b§lDiamand Key").setLore("§8» §aPreis§8: §c"+priceForDiamond+"€", "§8» §7Nutze diesen Schlüssel um die §b§lDiamand Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(30, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §a§lSmaragt Key").setLore("§8» §aPreis§8: §c"+priceForEmerald+"€", "§8» §7Nutze diesen Schlüssel um die §a§lSmaragt Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(32, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §d§lAmethyst Key").setLore("§8» §aPreis§8: §c"+priceForAmethyst+"€", "§8» §7Nutze diesen Schlüssel um die §d§lAmethyst Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        keyShop.setItem(34, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lNetherit Key").setLore("§8» §aPreis§8: §c"+priceForNetherite+"€", "§8» §7Nutze diesen Schlüssel um die §8§lNetherit Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        // addBackButton(keyShop);
    }

    private void openEffectShop(Player player) {
        player.openInventory(effectShop);
        fillWithGlass(effectShop);
        // addBackButton(effectShop);
    }

    private void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }

    /*
    private void addBackButton(Inventory inventory) {
        int slot = inventory.getSize()-9;
        inventory.setItem(slot, new ItemCreator(Material.PLAYER_HEAD).createCustomSkull("§8● §cZurück",
                "http://textures.minecraft.net/texture/f84f597131bbe25dc058af888cb29831f79599bc67c95c802925ce4afba332fc").toItemStack());
    }

    private void addExitButton(Inventory inventory) {
        int slot = inventory.getSize()-5;
        inventory.setItem(slot, new ItemCreator(Material.PLAYER_HEAD).createCustomSkull("§8● §cSchließen",
                "http://textures.minecraft.net/texture/beb588b21a6f98ad1ff4e085c552dcb050efc9cab427f46048f18fc803475f7").toItemStack());
    }
     */
}
