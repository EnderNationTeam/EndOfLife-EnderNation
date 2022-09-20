package de.mxscha.endernationendoflife.utils.inventory;

import de.mxscha.endernationendoflife.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryOpener {

    private static Inventory JobInventory;
    private static Inventory ShopInventory;
    private static Inventory ShopInventoryItems;
    private static Inventory ShopInventoryKeys;
    private static Inventory ShopInventoryRolls;
    private static Inventory JobInventoryFarmer;
    private static Inventory JobInventoryFarmerInfo;
    private static Inventory JobInventoryLumberjack;
    private static Inventory JobInventoryButcher;
    private static Inventory JobInventoryMiner;
    private static Inventory JobInventoryFisher;

    private static final HashMap<Integer, Inventory> IDs = new HashMap<>();

    public static void initInventorys() {
        JobInventory = Bukkit.createInventory(null, 9*5, "§8» §a§lJobs");
        JobInventoryFarmer = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8● §a§lFarmer");
        JobInventoryFarmerInfo = Bukkit.createInventory(null, 9*6, "§8» §a§lFarmer §8● §6Infos");
        JobInventoryLumberjack = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8● §2§lHolzfäller");
        JobInventoryButcher = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8● §d§lSchlachter");
        JobInventoryMiner = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8● §6§lMinenarbeiter");
        JobInventoryFisher = Bukkit.createInventory(null, 9*6, "§8» §a§lJobs §8● §9§lFischer");
        ShopInventory = Bukkit.createInventory(null, 9*5, "§8» §6§lShop");
        ShopInventoryItems = Bukkit.createInventory(null, 9*5, "§8» §a§lItem Shop");
        ShopInventoryKeys = Bukkit.createInventory(null, 9*5, "§8» §5§lKey Shop");
        ShopInventoryRolls = Bukkit.createInventory(null, 9*5, "§8» §9§lRollen Shop");

        setupInventory(JobInventory);
        setupInventory(ShopInventory);
        setupInventory(ShopInventoryItems);
        setupInventory(ShopInventoryKeys);
        setupInventory(ShopInventoryRolls);
        setupInventory(JobInventoryFarmer);
        setupInventory(JobInventoryLumberjack);
        setupInventory(JobInventoryButcher);
        setupInventory(JobInventoryMiner);
        setupInventory(JobInventoryFisher);
        setupInventory(JobInventoryFarmerInfo);

        IDs.put(1, JobInventory);
        IDs.put(2, ShopInventory);
        IDs.put(3, ShopInventoryItems);
        IDs.put(4, ShopInventoryKeys);
        IDs.put(5, ShopInventoryRolls);
        IDs.put(6, JobInventoryFarmer);
        IDs.put(7, JobInventoryLumberjack);
        IDs.put(8, JobInventoryButcher);
        IDs.put(9, JobInventoryMiner);
        IDs.put(10, JobInventoryFisher);
        IDs.put(11, JobInventoryFarmerInfo);
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
            addItemsToJobInventory();
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
        if (inventory == JobInventoryFarmer) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFarmerJobInventory();
        }
        if (inventory == JobInventoryLumberjack) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToLumberjackJobInventory();
        }
        if (inventory == JobInventoryButcher) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToButcherJobInventory();
        }
        if (inventory == JobInventoryMiner) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToMinerJobInventory();
        }
        if (inventory == JobInventoryFisher) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFisherJobInventory();
        }
        if (inventory == JobInventoryFarmerInfo) {
            InventoryPropertys.fillWithGlass(inventory);
            InventoryPropertys.addBackButton(inventory);
            addItemsToFarmerJobInfoInventory();
        }
    }

    private static void addItemsToJobInventory() {
        JobInventory.setItem(11, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §a§lFarmer").setLore("§8» §7Baue verschiedene Arten von Essen an/ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Diamand Hacke mit §d§oEffizienz", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(12, new ItemCreator(Material.STONE_AXE).setName("§8● §2§lHolzfäller").setLore("§8» §7Baue verschiedene Baum Arten ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Stein Axt mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(13, new ItemCreator(Material.WOODEN_SWORD).setName("§8● §d§lSchlachter").setLore("§8» §7Töte verschiedene Tiere", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Ein Holz Schwert mit §d§oSchärfe 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(14, new ItemCreator(Material.IRON_PICKAXE).setName("§8● §6§lMinenarbeiter").setLore("§8» §7Baue verschiedene Erze und Blöcke ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventory.setItem(15, new ItemCreator(Material.FISHING_ROD).setName("§8● §9§lFischer").setLore("§8» §7Suche nach verschiedenen Fischen im Meer", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Angel mit §d§oGlück des Meeres", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "", "§7Klicke für Mehr Info!").toItemStack());
        JobInventory.setItem(8, new ItemCreator(Material.BARRIER).setName("§8● §cKündigen").setLore("§8» §7Hier kannst du deinen Job §ckündigen§7!", "§8» §c§lInfo§8: §6Bitte halte dein Job Tool bereit!").toItemStack());
    }

    private static void addItemsToFarmerJobInventory() {
        JobInventoryFarmer.setItem(4, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §a§lFarmer").setLore("§8» §7Baue verschiedene Arten von Essen an/ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Diamand Hacke mit §d§oEffizienz", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryFarmer.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
        JobInventoryFarmer.setItem(32, new ItemCreator(Material.WRITABLE_BOOK).setName("§8● §6Infos").setLore("§7Klicke um Infos zu diesem Job anzusehen!").toItemStack());
    }

    private static void addItemsToFarmerJobInfoInventory() {
        JobInventoryFarmerInfo.setItem(4, new ItemCreator(Material.WRITABLE_BOOK).setName("§8● §6Infos").setLore("§8» §7Alle Infos zum Job §aFarmer§7!").toItemStack());
        JobInventoryFarmerInfo.setItem(18, new ItemCreator(Material.HOPPER_MINECART).setName("§8● §aAbgabe Ort").setLore("§8» §2X§8: §e182§8, §2Y§8: §e103§8, §2Z§8: §e-123", "§8» §6/abgabe").toItemStack());
        JobInventoryFarmerInfo.setItem(27, new ItemCreator(Material.DIAMOND_HOE).setName("§8● §aFarmer Hacke").setLore("§7Baue Folgende Dinge Mit deiner §aFarmer Hacke §7ab!", "", "§6§lInfo§8: §7Bringe die Abgebauten Dinge zum Abgabeort", "        §7um eine Bohnuszahlung zu bekommen!").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        makeGlassBorder(JobInventoryFarmerInfo);
        JobInventoryFarmerInfo.setItem(30, new ItemCreator(Material.POTATO).setName("§8» §eKartoffel").toItemStack());
        JobInventoryFarmerInfo.setItem(31, new ItemCreator(Material.CARROT).setName("§8» §6Karotte").toItemStack());
        JobInventoryFarmerInfo.setItem(32, new ItemCreator(Material.WHEAT).setName("§8» §aWeizen").toItemStack());
        JobInventoryFarmerInfo.setItem(33, new ItemCreator(Material.MELON_SLICE).setName("§8» §2Melone").toItemStack());
        JobInventoryFarmerInfo.setItem(39, new ItemCreator(Material.BAMBOO).setName("§8» §2Bambus").toItemStack());
        JobInventoryFarmerInfo.setItem(40, new ItemCreator(Material.KELP).setName("§8» §2Alge").toItemStack());
        JobInventoryFarmerInfo.setItem(41, new ItemCreator(Material.SUGAR_CANE).setName("§8» §aZuckerrohr").toItemStack());
        JobInventoryFarmerInfo.setItem(42, new ItemCreator(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        JobInventoryFarmerInfo.setItem(48, new ItemCreator(Material.PUMPKIN_SEEDS).setName("§8» §eKürbiskerne").toItemStack());
        JobInventoryFarmerInfo.setItem(49, new ItemCreator(Material.MELON_SEEDS).setName("§8» §eMelonenkerne").toItemStack());
        JobInventoryFarmerInfo.setItem(50, new ItemCreator(Material.WHEAT_SEEDS).setName("§8» §eWeizensamen").toItemStack());
        JobInventoryFarmerInfo.setItem(51, new ItemCreator(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
    }

    private static void makeGlassBorder(Inventory inventory) {
        for (int i = 20; i < 26; i++) {
            inventory.setItem(i, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
        inventory.setItem(29, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(34, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(38, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(43, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(47, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
        inventory.setItem(52, new ItemCreator(Material.LIME_STAINED_GLASS_PANE).setName(" ").toItemStack());
    }

    private static void addItemsToLumberjackJobInventory() {
        JobInventoryLumberjack.setItem(4, new ItemCreator(Material.STONE_AXE).setName("§8● §2§lHolzfäller").setLore("§8» §7Baue verschiedene Baum Arten ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Stein Axt mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryLumberjack.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToButcherJobInventory() {
        JobInventoryButcher.setItem(4, new ItemCreator(Material.WOODEN_SWORD).setName("§8● §d§lSchlachter").setLore("§8» §7Töte verschiedene Tiere", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Ein Holz Schwert mit §d§oSchärfe 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryButcher.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToMinerJobInventory() {
        JobInventoryMiner.setItem(4, new ItemCreator(Material.IRON_PICKAXE).setName("§8● §6§lMinenarbeiter").setLore("§8» §7Baue verschiedene Erze und Blöcke ab", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Eisen Spitzhacke mit §d§oEffizienz 2", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).toItemStack());
        JobInventoryMiner.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
    }

    private static void addItemsToFisherJobInventory() {
        JobInventoryFisher.setItem(4, new ItemCreator(Material.FISHING_ROD).setName("§8● §9§lFischer").setLore("§8» §7Suche nach verschiedenen Fischen im Meer", "", "§8» §6§lDu bekommst§8§l:", "§8● §7Eine Angel mit §d§oGlück des Meeres", "   §8» §8[§9Unzerstörbar§8]", "   §8» §cAufstufbar!", "§7Mindestlohn§8: §c60€/d", "").toItemStack());
        JobInventoryFisher.setItem(30, new ItemCreator(Material.LIME_DYE).setName("§8● §aAnnehmen").setLore("§7Klicke um diesen Job anzunehmen!").toItemStack());
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
