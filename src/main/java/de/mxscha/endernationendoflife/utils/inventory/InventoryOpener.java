package de.mxscha.endernationendoflife.utils.inventory;

import de.mxscha.endernationendoflife.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.HashMap;

public class InventoryOpener {

    private static Inventory JobInventory;
    private static Inventory ShopInventory;
    private static Inventory ShopInventoryItems;
    private static Inventory ShopInventoryKeys;
    private static Inventory ShopInventoryRolls;

    private static final HashMap<Integer, Inventory> IDs = new HashMap<>();

    public static void initInventorys() {
        JobInventory = Bukkit.createInventory(null, 9*5, "§8» §a§lJobs");
        ShopInventory = Bukkit.createInventory(null, 9*5, "§8» §6§lShop");
        ShopInventoryItems = Bukkit.createInventory(null, 9*5, "§8» §a§lItem Shop");
        ShopInventoryKeys = Bukkit.createInventory(null, 9*5, "§8» §5§lKey Shop");
        ShopInventoryRolls = Bukkit.createInventory(null, 9*5, "§8» §9§lRollen Shop");

        setupInventory(JobInventory);

        IDs.put(1, JobInventory);
        IDs.put(2, ShopInventory);
        IDs.put(3, ShopInventoryItems);
        IDs.put(4, ShopInventoryKeys);
        IDs.put(5, ShopInventoryRolls);
    }

    public static void open(Player player, int id) {
        if (IDs.containsKey(id)) {
            player.openInventory(IDs.get(id));
        }
    }

    private static void setupInventory(Inventory inventory) {
        if (inventory == JobInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
            addItemToJobInventory();
        }
        if (inventory == ShopInventory) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addExitButton(inventory);
            addItemsToShopInventory();
        }
        if (inventory == ShopInventoryItems) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToItemShopInventory();
        }
        if (inventory == ShopInventoryKeys) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToKeyShopInventory();
        }
        if (inventory == ShopInventoryRolls) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToRollShopInventory();
        }
    }

    private static void addItemToJobInventory() {
        JobInventory.setItem(11, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §a§lFarmer").setLore("§8» §7Baue verschiedene Arten von Essen an/ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Diamand Hacke mit §d§oEffizienz", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(12, new ItemCreator(Material.STONE_AXE).setName("§8● §2§lHolzfäller").setLore("§8» §7Baue verschiedene Baum Arten ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Stein Axt mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(13, new ItemCreator(Material.WOODEN_SWORD).setName("§8● §d§lSchlachter").setLore("§8» §7Töte verschiedene Tiere", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Ein Holz Schwert mit §d§oSchärfe 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(14, new ItemCreator(Material.IRON_PICKAXE).setName("§8● §6§lMinenarbeiter").setLore("§8» §7Baue verschiedene Erze und Blöcke ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(15, new ItemCreator(Material.FISHING_ROD).setName("§8● §9§lFischer").setLore("§8» §7Suche nach verschiedenen Fischen im Meer", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Angel mit §d§oGlück des Meeres", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").toItemStack());
    }

    private static void addItemsToShopInventory() {
        ShopInventory.setItem(20, new ItemCreator(Material.ENDER_CHEST).setName("§8» §a§lItem Shop").toItemStack());
        ShopInventory.setItem(22, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8» §5§lKey Shop").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventory.setItem(24, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8» §9§lRollen Shop").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }

    private static void addItemsToItemShopInventory() {
        // Item Shop Mechanics
    }

    private static void addItemsToKeyShopInventory() {
        ShopInventoryKeys.setItem(4, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lKohle Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Coal")+"€", "§8» §7Nutze diesen Schlüssel um die §8§lKohle Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(7).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(11, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §c§lKupfer Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Copper")+"€", "§8» §7Nutze diesen Schlüssel um die §c§lKupfer Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(1).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(29, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §6§lGold Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Gold")+"€", "§8» §7Nutze diesen Schlüssel um die §6§lGold Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(3).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(33, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §b§lDiamand Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Diamond")+"€", "§8» §7Nutze diesen Schlüssel um die §b§lDiamand Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(4).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(40, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §a§lSmaragt Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Emerald")+"€", "§8» §7Nutze diesen Schlüssel um die §a§lSmaragt Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(2).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(15, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §d§lAmethyst Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Amethyst")+"€", "§8» §7Nutze diesen Schlüssel um die §d§lAmethyst Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(6).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryKeys.setItem(22, new ItemCreator(Material.DISC_FRAGMENT_5).setName("§8● §8§lNetherit Key").setLore("§8» §aPreis§8: §c"+InventoryPropertys.getPriceFor("Netherite")+"€", "§8» §7Nutze diesen Schlüssel um die §8§lNetherit Kiste §7zu öffnen!", "",  "§8» §7Klicke zum Kaufen!").setCustomModelData(5).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }

    private static void addItemsToRollShopInventory() {
        ShopInventoryRolls.setItem(13, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §9Fliegen").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Flying")+"€", "§8» §7Nutze diese Rolle um §9Fliegen §7zu können!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(21, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §6Schneller Abbauen").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Mining")+"€", "§8» §7Nutze diese Rolle um schnller §6Abzubauen!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(23, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §5Nacht Sicht").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Night vision")+"€", "§8» §7Nutze diese Rolle um in §5Nacht §7zu sehen!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
        ShopInventoryRolls.setItem(31, new ItemCreator(Material.FLOWER_BANNER_PATTERN).setName("§8● §2Glück").setLore("§8» §aPreis§8: §c" + InventoryPropertys.getPriceFor("Luck")+"€", "§8» §7Nutze diese Rolle um §2Glück §7zu haben!", "", "§8» §7Klicke zum Kaufen!").addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).toItemStack());
    }
}
